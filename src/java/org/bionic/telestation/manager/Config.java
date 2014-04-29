/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bionic.telestation.manager;

import java.util.ResourceBundle;

/**
 *
 * @author Max
 */
public class Config {
    private static Config instance;
    private ResourceBundle resourceBundle;
    
    private static final String BUNDLE_NAME = "org.bionic.telestation.manager.config";
    public static final String BANNED = "BANNED";
    public static final String UNBANNED = "UNBANNED";
    public static final String ERROR = "ERROR";
    public static final String MAIN = "MAIN";
    public static final String LOGIN = "LOGIN";
    public static final String USER = "USER";
    public static final String ADMIN = "ADMIN";
    public static final String USER_ID = "USER_ID";
    public static final String REGISTER = "REGISTER";
    public static final String REGISTERED = "REGISTERED";
    public static final String PAY = "PAY";
    public static final String PAY_CONFIRM = "PAY_CONFIRM";
    public static final String ALL_USERS = "ALL_USERS";
    public static final String ALL_BANNED_USERS = "ALL_BANNED_USERS";
    public static final String ALL_EXPIRED_USERS = "ALL_EXPIRED_USERS";
    public static final String ALL_USERS_FOR_REGISTRATION = "ALL_USERS_FOR_REGISTRATION";
    public static final String SMS = "SMS";
    public static final String SMS_ERROR = "SMS_ERROR";
    public static final String SHOW_USER_CHECKS = "SHOW_USER_CHECKS";
    public static final String CONFIRMATION = "CONFIRMATION";
    
    
    public static Config getInstance(){
        if (instance == null){
            instance = new Config();
            instance.resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME);
        }
        return instance;
    }
    
    public String getProperty(String key){
        return (String)resourceBundle.getObject(key);
    }
}
