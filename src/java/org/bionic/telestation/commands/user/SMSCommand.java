/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bionic.telestation.commands.user;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.bionic.telestation.commands.ICommand;
import org.bionic.telestation.daosql.MySQLDaoUser;
import org.bionic.telestation.entity.Check;
import org.bionic.telestation.entity.Service;
import org.bionic.telestation.entity.ServiceType;
import org.bionic.telestation.manager.Config;
import org.bionic.telestation.manager.Message;
import org.bionic.telestation.util.DAOFactory;
import org.bionic.telestation.util.Utility;

/**
 *
 * @author MAX
 */
public class SMSCommand implements ICommand {
    
    private static final String TEXT = "text";
    private static final String PHONE_NUMBER = "number";
    private static final long ADD_MONTH = 30L * 24L * 60L * 60L * 1000L;
    private static final Integer SERVICE_TYPE_ID = 2;
    private static final BigDecimal PRICE = DAOFactory.createMySQLDaoServiceType().findById(2).getPrice();

    private Date date = new Date(System.currentTimeMillis());
    private Date expireDate = new Date(System.currentTimeMillis() + ADD_MONTH);
    private Check check;
    private Service service;
    private ServiceType serviceType = DAOFactory.createMySQLDaoServiceType().findById(SERVICE_TYPE_ID);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;
        boolean isDigit = false;
        HttpSession session = request.getSession(false);
        String text = request.getParameter(TEXT);
        String phoneNumber = request.getParameter(PHONE_NUMBER);
        MySQLDaoUser dao = DAOFactory.createMySQLDaoUser();

        // check for serviceType existance
        if (serviceType == null) {
            session.setAttribute("error", Message.getInstance().getProperty(Message.SERVICE_TYPE_NOT_FOUND_ERROR));
            page = Config.getInstance().getProperty(Config.ERROR);
        } else if (text != null && phoneNumber != null) {
            isDigit = Utility.checkIfDigit(text);
            if (isDigit) {
                Integer number = Integer.parseInt(phoneNumber);
                if (dao.findById(number) != null) {
                    service = new Service();
                    service.setServiceTypeId(serviceType);
                    service.setSender((Integer) session.getAttribute("userId"));
                    service.setRecipient(number);
                    service.setDate(date);
                    service.setDuration(null);
                    check = new Check();
                    check.setCreationDate(date);
                    check.setExpireDate(expireDate);
                    check.setExpired(false);
                    check.setPaid(false);
                    check.setPrice(PRICE);
                    check.setServiceId(service);
                    check.setUserId(dao.findById(number));
                    //autoincrement
                    session.setAttribute("recipient", number);
                    page = Config.getInstance().getProperty(Config.SMS);
                } else {
                    session.setAttribute("error", Message.getInstance().getProperty(Message.USER_NOT_FOUND));
                    page = Config.getInstance().getProperty(Config.SMS_ERROR);
                }
            } else {
                session.setAttribute("error", Message.getInstance().getProperty(Message.PHONE_NUMBER));
                page = Config.getInstance().getProperty(Config.SMS_ERROR);
            }
        } else {
            if (phoneNumber == null) {
                session.setAttribute("error", Message.getInstance().getProperty(Message.PHONE_NUMBER));
                page = Config.getInstance().getProperty(Config.SMS_ERROR);
            } else {
                session.setAttribute("error", Message.getInstance().getProperty(Message.SMS_ERROR));
                page = Config.getInstance().getProperty(Config.SMS_ERROR);
            }
        }

        return page;
    }
}
