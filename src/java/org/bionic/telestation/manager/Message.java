/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bionic.telestation.manager;

import java.util.ResourceBundle;

/**
 *
 * @author Maksym
 */
public class Message {
    private static Message instance;
    private ResourceBundle resourceBundle;
    
    private static final String BUNDLE_NAME = "org.bionic.telestation.manager.messages";
    public static final String LOGIN_ERROR = "LOGIN_ERROR";
    public static final String SERVLET_EXCEPTION_ERROR = "SERVLET_EXCEPTION_ERROR";
    public static final String IO_EXCEPTION_ERROR = "IO_EXCEPTION_ERROR"; 
    public static final String NO_USER_FOUND = "NO_USER_FOUND";
    public static final String NO_USERS_FOUND = "NO_USERS_FOUND";
    public static final String NO_BANNED_USERS_FOUND = "NO_BANNED_USERS_FOUND";
    public static final String NOT_REGISTRED_YET = "NOT_REGISTRED_YET";
    public static final String REGISTRATION_ERROR = "REGISTRATION_ERROR";
    public static final String PHONE_NUMBER = "PHONE_NUMBER";
    public static final String SMS_ERROR = "SMS_ERROR";
    public static final String NUMBER_FROMAT_ERROR = "NUMBER_FROMAT_ERROR";
    public static final String USER_NOT_FOUND = "USER_NOT_FOUND";
    public static final String NO_CHECKS_FOUND = "NO_CHECKS_FOUND";
    public static final String PAY_ERROR = "PAY_ERROR";
    public static final String BAN_ERROR = "BAN_ERROR";
    public static final String UNBAN_ERROR = "UNBAN_ERROR";
    public static final String ENTER_USER_ID = "ENTER_USER_ID";
    public static final String SERVICE_TYPE_NOT_FOUND_ERROR = "SERVICE_TYPE_NOT_FOUND_ERROR";
    public static final String ALREADY_PAID = "ALREADY_PAID";
    public static final String YOU_ARE_BANNED = "YOU_ARE_BANNED";
    
    public static Message getInstance(){
        if (instance == null){
            instance = new Message();
            instance.resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME);
        }
        return instance;
    }
    
    public String getProperty(String key){
        return (String)resourceBundle.getObject(key);
    }
}
