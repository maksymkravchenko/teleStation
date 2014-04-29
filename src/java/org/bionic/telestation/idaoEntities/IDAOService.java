/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bionic.telestation.idaoEntities;

import java.util.List;
import org.bionic.telestation.entity.Service;

/**
 *
 * @author MAX
 */
public interface IDAOService {
    List<Service> findAll();
    List<Service> findAllByRecipientID(int ID);
    List<Service> findAllBySenderID(int ID);
    Service findByServiceID(int ID);
    void add(Service service);
    void update(Service service);
}
