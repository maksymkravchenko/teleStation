/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bionic.telestation.idaoEntities;

import java.util.List;
import org.bionic.telestation.entity.ServiceType;

/**
 *
 * @author MAX
 */
public interface IDAOServiceType {

    List<ServiceType> findAll();
    ServiceType findById(int id);
    void add(ServiceType serviceType);
    void update(ServiceType serviceType);
}
