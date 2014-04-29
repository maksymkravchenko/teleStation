/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bionic.telestation.idaoEntities;

import java.util.List;
import org.bionic.telestation.entity.Check;

/**
 *
 * @author MAX
 */
public interface IDAOCheck {
    List<Check> findAll();
    List<Check> findAllExpired();
    List<Check> findAllNotPaid();
    Check findByCheckID(int ID);
    List<Check> findByUserID(int ID);
    void add(Check check);
    void update(Check check);
}
