/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bionic.telestation.commands;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.bionic.telestation.daosql.MySQLDaoUserType;
import org.bionic.telestation.entity.User;
import org.bionic.telestation.manager.Config;
import org.bionic.telestation.manager.Message;
import org.bionic.telestation.util.DAOFactory;

/**
 *
 * @author MAX
 */
public class ShowUsersForRegCommand implements ICommand {

    private MySQLDaoUserType dao = DAOFactory.createMySQLDaoUserType();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;
        List<User> list = new ArrayList<User>();
        HttpSession session = request.getSession(false);

        List<User> temp = DAOFactory.createMySQLDaoUser().findAll();
        if (!temp.isEmpty()) {
            for (User a : temp) {
                if (a.getUserTypeId().equals(dao.findById(3))) {
                    list.add(a);
                }
                if (!list.isEmpty()) {
                    session.setAttribute("list", list);
                    page = Config.getInstance().getProperty(Config.ALL_USERS_FOR_REGISTRATION);
                } else {
                    session.setAttribute("error", Message.getInstance().getProperty(Message.NO_USERS_FOUND));
                    page = Config.getInstance().getProperty(Config.ERROR);
                }
            }
        } else {
            session.setAttribute("error", Message.getInstance().getProperty(Message.NO_USERS_FOUND));
            page = Config.getInstance().getProperty(Config.ERROR);
        }

        return page;
    }
}
