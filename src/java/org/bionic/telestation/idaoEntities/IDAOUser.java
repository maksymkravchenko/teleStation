/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bionic.telestation.idaoEntities;

import java.util.List;
import org.bionic.telestation.entity.User;
import org.bionic.telestation.entity.UserType;

/**
 *
 * @author MAX
 */
public interface IDAOUser {
    
    List<User> findAll();
    List<User> findAllByUserType(UserType uType);
    List<User> findBanned();
    User findById(int ID);
    User findByName(String name);
    void add(User user);
    void update(User user);    
}
