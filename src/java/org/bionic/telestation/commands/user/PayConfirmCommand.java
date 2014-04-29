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
public class PayConfirmCommand implements ICommand {

    private MySQLDAOCheck dao = DAOFactory.createMySQLDAOCheck();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;
        HttpSession session = request.getSession(false);
        List<Check> list = (List) session.getAttribute("checksToPay");
        if (session != null) {
            if (!list.isEmpty()) {
                for (Check check : list) {
                    check.setPaid(Boolean.TRUE);
                    dao.update(check);
                }
                page = Config.getInstance().getProperty(Config.PAY_CONFIRM);
            } else {
                session.setAttribute("error", Message.getInstance().getProperty(Message.PAY_ERROR));
                page = Config.getInstance().getProperty(Config.ERROR);
            }
        } else {
            request.setAttribute("error", Message.getInstance().getProperty(Message.NO_CHECKS_FOUND));
            page = Config.getInstance().getProperty(Config.ERROR);
        }

        return page;
    }
}
