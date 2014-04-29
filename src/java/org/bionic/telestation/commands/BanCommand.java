/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bionic.telestation.commands;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.bionic.telestation.daosql.MySQLDaoUser;
import org.bionic.telestation.daosql.MySQLDaoUserType;
import org.bionic.telestation.entity.User;
import org.bionic.telestation.manager.Config;
import org.bionic.telestation.manager.Message;
import org.bionic.telestation.util.DAOFactory;

/**
 *
 * @author MAX
 */
public class BanCommand implements ICommand {

    private static final String USER_ID_LIST = "idUser";
    private MySQLDaoUser dao = DAOFactory.createMySQLDaoUser();
    private User user;
    private MySQLDaoUserType userTypeDao = DAOFactory.createMySQLDaoUserType();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;
        Integer i = null;
        String[] selected = null;
        HttpSession session = request.getSession(false);
        List<User> bannedUsersList = new LinkedList<User>();
        List<User> alreadyBanned = new LinkedList<User>();
        List<User> wrongUserForBan = new LinkedList<User>();

        selected = request.getParameterValues(USER_ID_LIST);
        if (session != null) {
            //clean-up
                if (session.getAttribute("error") != null) {
                    session.removeAttribute("error");
                }
                if (session.getAttribute("alreadyBanned") != null) {
                    session.removeAttribute("alreadyBanned");
                }
                if (session.getAttribute("wrongUser") != null) {
                    session.removeAttribute("wrongUser");
                }
                if (session.getAttribute("bannedUsers") != null) {
                    session.removeAttribute("bannedUsers");
                }
                //////
            if (selected != null && selected.length != 0) {
                for (String a : selected) {
                    i = Integer.parseInt(a);
                    user = dao.findById(i);
                    if (user.getUserTypeId().equals(userTypeDao.findById(2))
                            && user.isBanned() != true) {
                        user.setBanned(Boolean.TRUE);
                        dao.update(user);
                        bannedUsersList.add(user);
                    } else {
                        if (user.isBanned() == true) {
                            alreadyBanned.add(user);
                            session.setAttribute("alreadyBanned", alreadyBanned);
                        } else {
                            wrongUserForBan.add(user);
                            session.setAttribute("wrongUser", wrongUserForBan);
                        }
                    }
                }
                request.setAttribute("bannedUsers", bannedUsersList);
                page = Config.getInstance().getProperty(Config.BANNED);
            } else {
                session.setAttribute("error", Message.getInstance().getProperty(Message.BAN_ERROR));
                page = Config.getInstance().getProperty(Config.ERROR);
            }
        } else {
            request.setAttribute("error", Message.getInstance().getProperty(Message.NO_CHECKS_FOUND));
            page = Config.getInstance().getProperty(Config.ERROR);
        }
        return page;
    }
}
