/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bionic.telestation.commands;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.bionic.telestation.daosql.MySQLDaoUserType;
import org.bionic.telestation.entity.User;
import org.bionic.telestation.manager.Config;
import org.bionic.telestation.manager.Message;
import org.bionic.telestation.util.DAOFactory;
import org.bionic.telestation.util.Utility;

/**
 *
 * @author MAX
 */
public class LoginCommand implements ICommand {

    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private User user;
    private MySQLDaoUserType dao = DAOFactory.createMySQLDaoUserType();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;
        HttpSession session = request.getSession(true);
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);
        if (login != null && password != null) {
            if (Utility.checkIfDigit(login)) {
                Integer id = Integer.parseInt(login);
                user = DAOFactory.createMySQLDaoUser().findById(id);
                if (password.equals(user.getPassword())) {
                    if (!user.isBanned()) {
                    session.setAttribute("name", user.getName());
                    session.setAttribute("userId", user.getIdUser());

                    if (user.isBanned() == true) {
                        page = Config.getInstance().getProperty(Config.BANNED);
                    } else if (user.getUserTypeId().equals(dao.findById(1))) {
                        page = Config.getInstance().getProperty(Config.ADMIN);
                    } else if (user.getUserTypeId().equals(dao.findById(2))) {
                        page = Config.getInstance().getProperty(Config.USER);
                    } else if (user.getUserTypeId().equals(dao.findById(3))) {
                        session.setAttribute("error", Message.getInstance().getProperty(Message.NOT_REGISTRED_YET));
                        page = Config.getInstance().getProperty(Config.ERROR);
                    }
//            //TODO HERE if admin, if user, if reg
//            page = Config.getInstance().getProperty(Config.MAIN);
                    } else {
                    session.setAttribute("error", Message.getInstance().getProperty(Message.YOU_ARE_BANNED));
                    page = Config.getInstance().getProperty(Config.ERROR);
                }
                    } else {
                    session.setAttribute("error", Message.getInstance().getProperty(Message.LOGIN_ERROR));
                    page = Config.getInstance().getProperty(Config.ERROR);
                }
            } else {
                session.setAttribute("error", Message.getInstance().getProperty(Message.NUMBER_FROMAT_ERROR));
                page = Config.getInstance().getProperty(Config.ERROR);
            }
        } else {
            session.setAttribute("error", Message.getInstance().getProperty(Message.LOGIN_ERROR));
            page = Config.getInstance().getProperty(Config.ERROR);
        }

        return page;
    }
}
