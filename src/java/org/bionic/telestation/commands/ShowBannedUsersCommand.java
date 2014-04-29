/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bionic.telestation.commands;

import java.io.IOException;
import java.util.List;
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
public class ShowBannedUsersCommand implements ICommand {

    private MySQLDaoUser dao = DAOFactory.createMySQLDaoUser();
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;
        HttpSession session = request.getSession(false);
        List<User> list = dao.findBanned();
        
        if (session != null) {
            if (!list.isEmpty()) {
                session.setAttribute("bannesList", list);
                page = Config.getInstance().getProperty(Config.ALL_BANNED_USERS);
            } else {
                session.setAttribute("error", Message.getInstance().getProperty(Message.NO_BANNED_USERS_FOUND));
                page = Config.getInstance().getProperty(Config.ERROR);
            }
        } else {
            request.setAttribute("error", Message.getInstance().getProperty(Message.NO_CHECKS_FOUND));
            page = Config.getInstance().getProperty(Config.ERROR);
        }
        return page;
    }
}
