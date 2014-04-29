/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bionic.telestation.commands;

import java.io.IOException;
import java.util.TreeSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.bionic.telestation.entity.User;
import org.bionic.telestation.manager.Config;
import org.bionic.telestation.manager.Message;
import org.bionic.telestation.util.DAOFactory;

/**
 *
 * @author MAX
 */
public class RegisterCommand implements ICommand {

    private static final String NAME = "name";
    private static final String PASSWORD = "password";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;
        String name = request.getParameter(NAME);
        String password = request.getParameter(PASSWORD);
        if (name == null || password == null) {
            request.setAttribute("error", Message.getInstance().getProperty(Message.REGISTRATION_ERROR));
            page = Config.getInstance().getProperty(Config.ERROR);
        } else {
            //create user
            User user = new User();
            user.setIdUser(getLastId() + 1);
            user.setName(name);
            user.setPassword(password);
            user.setLoggedin(false);
            
            
            DAOFactory.createMySQLDaoUser().add(user);
            
            /// not sure if request or session
            request.setAttribute("registred_user_id", getLastId());
                    ///////////
            page = Config.getInstance().getProperty(Config.REGISTERED);
        }

        return page;
    }
    
    private Integer getLastId(){
        TreeSet<Integer> set = new TreeSet<Integer>();
        for(User a: DAOFactory.createMySQLDaoUser().findAll()) {
            set.add(a.getIdUser());
        }
        return set.last();
    }
}
