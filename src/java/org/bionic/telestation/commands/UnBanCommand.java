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
import org.bionic.telestation.daosql.MySQLDaoUserType;
import org.bionic.telestation.entity.User;
import org.bionic.telestation.manager.Config;
import org.bionic.telestation.manager.Message;
import org.bionic.telestation.util.DAOFactory;

/**
 *
 * @author MAX
 */
public class UnBanCommand implements ICommand {

    private static final String USER_ID_LIST = "idUser";
    private MySQLDaoUser dao = DAOFactory.createMySQLDaoUser();
    private User user;
    private MySQLDaoUserType userTypeDao = DAOFactory.createMySQLDaoUserType();
    private String[] selected;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;
        Integer i = null;
        HttpSession session = request.getSession(false);
        LinkedList<User> unBannedUsersList = new LinkedList<User>();
        LinkedList<User> alreadyUnBanned = new LinkedList<User>();
        LinkedList<User> wrongUserForUnBan = new LinkedList<User>();
        selected = request.getParameterValues(USER_ID_LIST);
        
        if (session != null) {
            //clean-up
                if (session.getAttribute("error") != null) {
                    session.removeAttribute("error");
                }
                if (session.getAttribute("alreadyUnBanned") != null) {
                    session.removeAttribute("alreadyUnBanned");
                }
                if (session.getAttribute("wrongUnBanUser") != null) {
                    session.removeAttribute("wrongUnBanUser");
                }
                if (session.getAttribute("unBannedUsers") != null) {
                    session.removeAttribute("unBannedUsers");
                }
                //////
            if (selected != null && selected.length != 0) {
                for (String a : selected) {
                    i = Integer.parseInt(a);
                    user = dao.findById(i);
                    if (user.getUserTypeId().equals(userTypeDao.findById(2))
                            && user.isBanned() == true) {
                        user.setBanned(Boolean.FALSE);
                        dao.update(user);
                        unBannedUsersList.add(user);
                    } else {
                        if (user.isBanned() != true) {
                            alreadyUnBanned.add(user);
                            session.setAttribute("alreadyUnBanned", alreadyUnBanned);
                        } else {
                            wrongUserForUnBan.add(user);
                            session.setAttribute("wrongUnBanUser", wrongUserForUnBan);
                        }
                    }
                }
                request.setAttribute("unBannedUsers", unBannedUsersList);
                page = Config.getInstance().getProperty(Config.UNBANNED);
                //clean-up
                if (session.getAttribute("error") != null) {
                    session.removeAttribute("error");
                }
            } else {
                session.setAttribute("error", Message.getInstance().getProperty(Message.UNBAN_ERROR));
                page = Config.getInstance().getProperty(Config.ERROR);
            }
        } else {
            request.setAttribute("error", Message.getInstance().getProperty(Message.NO_CHECKS_FOUND));
            page = Config.getInstance().getProperty(Config.ERROR);
        }
        
        return page;
    }
}
