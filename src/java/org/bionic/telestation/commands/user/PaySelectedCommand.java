/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bionic.telestation.commands.user;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.LinkedList;
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
public class PaySelectedCommand implements ICommand {

    private static final String CHECKBOX_INFO = "checkid";
//    private LinkedList<Check> checksToPayList; 
//    private LinkedList<Integer> paidChecksIdList;
    private MySQLDAOCheck dao = DAOFactory.createMySQLDAOCheck();
    private Integer i;
    private Check check;
//    private BigDecimal price = BigDecimal.valueOf(0L);
    private HttpSession session;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;
        BigDecimal price = BigDecimal.valueOf(0L);
        LinkedList<Check> checksToPayList = new LinkedList<Check>();
        LinkedList<Integer> paidChecksIdList = new LinkedList<Integer>();
        session = request.getSession(false);
        String[] selected = request.getParameterValues(CHECKBOX_INFO);
        if (session != null) {
            //////clean-up
            if (session.getAttribute("price") != null) {
                session.removeAttribute("price");
            }
            if (session.getAttribute("checksToPay") != null) {
                session.removeAttribute("checksToPay");
            }
            if (session.getAttribute("paidChecksIdList") != null) {
                session.removeAttribute("paidChecksIdList");
            }
            ////////
            if (selected != null && selected.length != 0) {
                for (String a : selected) {
                    i = Integer.parseInt(a);
                    check = dao.findByCheckID(i);
                    //check if paid
                    if (check.getPaid() != true) {
                        price = price.add(check.getPrice());
                        checksToPayList.add(dao.findByCheckID(i));
                    } else {
                        paidChecksIdList.add(i);
                        session.setAttribute("paidChecksIdList", paidChecksIdList);
                    }
                }

                session.setAttribute("price", price);
                session.setAttribute("checksToPay", checksToPayList);
                page = Config.getInstance().getProperty(Config.PAY);

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
