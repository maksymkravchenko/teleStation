/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bionic.telestation.commands;

import java.io.IOException;
import java.util.LinkedList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.bionic.telestation.daosql.MySQLDaoUser;
import org.bionic.telestation.entity.User;
import org.bionic.telestation.manager.Config;
import org.bionic.telestation.manager.Message;
import org.bionic.telestation.util.DAOFactory;

/**
 *
 * @author MAX
 */
public class ShowAllUsersCommand implements ICommand {

    private MySQLDaoUser dao = DAOFactory.createMySQLDaoUser();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;
        HttpSession session = request.getSession(false);
        LinkedList<User> userslist = new LinkedList<User>(dao.findAll());
        
        if (session != null) {
            if (!userslist.isEmpty()) {
                session.setAttribute("list", userslist);
                page = Config.getInstance().getProperty(Config.ALL_USERS);
            } else {
                session.setAttribute("error", Message.getInstance().getProperty(Message.NO_USERS_FOUND));
                page = Config.getInstance().getProperty(Config.ERROR);
            }
        } else {
            request.setAttribute("error", Message.getInstance().getProperty(Message.NO_CHECKS_FOUND));
            page = Config.getInstance().getProperty(Config.ERROR);
        }
        return page;
    }
}
