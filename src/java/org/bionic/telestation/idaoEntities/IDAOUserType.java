/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bionic.telestation.idaoEntities;

import java.util.List;
import org.bionic.telestation.entity.UserType;

/**
 *
 * @author MAX
 */
public interface IDAOUserType {
    List<UserType> findAll();
    UserType findById(int id);
    void add(UserType userType);
    void update(UserType userType);
}
