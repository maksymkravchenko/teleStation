/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bionic.telestation.controller;

import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import org.bionic.telestation.commands.BanCommand;
import org.bionic.telestation.commands.ConfirmRegistrationCommand;
import org.bionic.telestation.commands.FindUserCommand;
import org.bionic.telestation.commands.ICommand;
import org.bionic.telestation.commands.LoginCommand;
import org.bionic.telestation.commands.NoCommand;
import org.bionic.telestation.commands.RegisterCommand;
import org.bionic.telestation.commands.ShowAllUsersCommand;
import org.bionic.telestation.commands.ShowBannedUsersCommand;
import org.bionic.telestation.commands.ShowExpiredUsersCommand;
import org.bionic.telestation.commands.UnBanCommand;
import org.bionic.telestation.commands.user.AddServiceCommand;
import org.bionic.telestation.commands.user.CallCommand;
import org.bionic.telestation.commands.user.MMSCommand;
import org.bionic.telestation.commands.user.PayCommand;
import org.bionic.telestation.commands.user.PayConfirmCommand;
import org.bionic.telestation.commands.user.PaySelectedCommand;
import org.bionic.telestation.commands.user.SMSCommand;
import org.bionic.telestation.commands.user.ShowAllChecksCommand;

/**
 *
 * @author MAX
 */
public class RequestHelper {
    
    private static RequestHelper instance = null;
    HashMap<String,ICommand> commands = new HashMap();

    //add commands
    private RequestHelper() {
        commands.put("login", new LoginCommand());
        commands.put("register", new RegisterCommand());
        //admin commands
        commands.put("find_user", new FindUserCommand());
        commands.put("show_all_users", new ShowAllUsersCommand());
        commands.put("show_banned_users", new ShowBannedUsersCommand());
        commands.put("show_expired_users", new ShowExpiredUsersCommand());
        commands.put("confirm_registration", new ConfirmRegistrationCommand());
        commands.put("ban", new BanCommand());
        commands.put("unban", new UnBanCommand());
        //user commands
        commands.put("add_service", new AddServiceCommand());
        commands.put("call", new CallCommand());
        commands.put("mms", new MMSCommand());
        commands.put("pay", new PayCommand());
        commands.put("sms", new SMSCommand());
        commands.put("show_all_checks", new ShowAllChecksCommand());
        commands.put("pay_selected_checks", new PaySelectedCommand());
        commands.put("payConfirm", new PayConfirmCommand());

    }
    
    public ICommand getCommand(HttpServletRequest request){
        String action = request.getParameter("command");
        ICommand command = commands.get(action);
        if (command == null){
            command = new NoCommand();
        }
        return command;
    }
    
    public static RequestHelper getInstance(){
        if (instance == null){
            instance = new RequestHelper();
        }
        return instance;
    }
}
