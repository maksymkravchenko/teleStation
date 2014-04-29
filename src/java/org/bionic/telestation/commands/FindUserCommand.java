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
import org.bionic.telestation.daosql.MySQLDAOCheck;
import org.bionic.telestation.daosql.MySQLDaoUser;
import org.bionic.telestation.entity.Check;
import org.bionic.telestation.entity.User;
import org.bionic.telestation.manager.Config;
import org.bionic.telestation.manager.Message;
import org.bionic.telestation.util.DAOFactory;
import org.bionic.telestation.util.Utility;

/**
 *
 * @author MAX
 */
public class FindUserCommand implements ICommand {

    private static final String USER = "user_id";
    private Integer i;
    private User user;
    private String ID;
    private MySQLDaoUser userDao = DAOFactory.createMySQLDaoUser();
    private MySQLDAOCheck checkDao = DAOFactory.createMySQLDAOCheck();
    private List<Check> checkList;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;
        HttpSession session = request.getSession(false);
        ID = request.getParameter(USER);
        
        if (session != null) {
            if (ID != null) {
                if (Utility.checkIfDigit(ID)) {
                    i = Integer.parseInt(ID);
                    user = userDao.findById(i);
                    if (user != null) {
                        checkList = checkDao.findByUserID(i);
                        session.setAttribute("user", user);
                        session.setAttribute("userCheckList", checkList);
                        page = Config.getInstance().getProperty(Config.USER_ID);
                    } else {
                        session.setAttribute("error", Message.getInstance().getProperty(Message.NO_USER_FOUND));
                        page = Config.getInstance().getProperty(Config.ERROR);
                    }
                } else {
                    session.setAttribute("error", Message.getInstance().getProperty(Message.NUMBER_FROMAT_ERROR));
                    page = Config.getInstance().getProperty(Config.ERROR);
                }
            } else {
                session.setAttribute("error", Message.getInstance().getProperty(Message.ENTER_USER_ID));
                page = Config.getInstance().getProperty(Config.ERROR);
            }
        } else {
            request.setAttribute("error", Message.getInstance().getProperty(Message.NO_CHECKS_FOUND));
            page = Config.getInstance().getProperty(Config.ERROR);
        }
        return page;
    }
}
