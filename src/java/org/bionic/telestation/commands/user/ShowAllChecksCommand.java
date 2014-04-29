/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bionic.telestation.commands.user;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.bionic.telestation.commands.ICommand;
import org.bionic.telestation.daosql.MySQLDAOCheck;
import org.bionic.telestation.entity.Check;
import org.bionic.telestation.manager.Config;
import org.bionic.telestation.manager.Message;
import org.bionic.telestation.util.DAOFactory;

/**
 *
 * @author MAX
 */
public class ShowAllChecksCommand implements ICommand {

    private static final String USER_ID = "userId";
    private Integer userId;
    private MySQLDAOCheck dao = DAOFactory.createMySQLDAOCheck();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;
        HttpSession session = request.getSession(false);
        //is session valid check
        if (session != null) {
            userId = (Integer) session.getAttribute(USER_ID);
            List<Check> checkList = dao.findByUserID(userId);
            if (!checkList.isEmpty()) {
                session.setAttribute("checkList", checkList);
                page = Config.getInstance().getProperty(Config.SHOW_USER_CHECKS);
            } else {
                session.setAttribute("error", Message.getInstance().getProperty(Message.NO_CHECKS_FOUND));
                page = Config.getInstance().getProperty(Config.ERROR);
            }
        } else {
            request.setAttribute("error", Message.getInstance().getProperty(Message.NO_CHECKS_FOUND));
            page = Config.getInstance().getProperty(Config.ERROR);
        }

        return page;
    }
}
