/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bionic.telestation.commands;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.bionic.telestation.daosql.MySQLDAOCheck;
import org.bionic.telestation.daosql.MySQLDaoUser;
import org.bionic.telestation.entity.Check;
import org.bionic.telestation.entity.User;
import org.bionic.telestation.manager.Config;
import org.bionic.telestation.manager.Message;
import org.bionic.telestation.util.DAOFactory;

/**
 *
 * @author MAX
 */
public class ShowExpiredUsersCommand implements ICommand {

    private MySQLDaoUser daoUser = DAOFactory.createMySQLDaoUser();
    private MySQLDAOCheck daoCheck = DAOFactory.createMySQLDAOCheck();
    private Integer userId;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;
        List<User> expiredUsersList = new LinkedList<User>();
        Set<Integer> set = new TreeSet<Integer>();
        HttpSession session = request.getSession(false);
        List<Check> expiredChecksList = daoCheck.findAllExpired();

        if (session != null) {
            if (!expiredChecksList.isEmpty()) {
                for (Check a : expiredChecksList) {
                    userId = a.getUserId().getIdUser();
                    if (set.add(userId)) {
                    }
                }
                //fill expiredUsersList with unique users
                for (Integer b : set) {
                    expiredUsersList.add(daoUser.findById(b));
                }
                session.setAttribute("expiredUsersList", expiredUsersList);
                page = Config.getInstance().getProperty(Config.ALL_EXPIRED_USERS);
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
