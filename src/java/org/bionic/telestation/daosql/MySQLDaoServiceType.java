/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bionic.telestation.daosql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import org.apache.log4j.Logger;
import org.bionic.telestation.entity.ServiceType;
import org.bionic.telestation.idaoEntities.IDAOServiceType;
import org.bionic.telestation.util.Log;
import org.bionic.telestation.util.Source;

/**
 *
 * @author MAX
 */
public class MySQLDaoServiceType implements IDAOServiceType {

    private final static Logger logger = Log.getInstance().logger;
    private Source pool = Source.getInstance();
    private Connection connect;
    private PreparedStatement ps;
    private ResultSet rs;
    private List<ServiceType> list;
    private ServiceType serviceType;
    
    //query strings
    private final static String FIND_ALL = "select * from service_type";
    private final static String FIND_BY_ID = "select * from service_type where idservice_type =?";
    
    //??? maybe i should delete first value?
    private final static String ADD = "insert into telephonedb.service "
            + "(idservice_type, type_name, price) "
            + "values (?, ?, ?)";
    private final static String UPDATE = "update telephonedb.service "
            + "type_name=?, price=? "
            + "where idservice_type=?";
    
    @Override
    public List<ServiceType> findAll() {
        try {
            connect = pool.getConnection();
            ps = connect.prepareStatement(FIND_ALL);
            rs = ps.executeQuery();
            list = new LinkedList();
            while (rs.next()) {
                serviceType = new ServiceType();
                serviceType.setIdservice_type(rs.getInt("idservice_type"));
                serviceType.setTypeName(rs.getString("type_name"));
                serviceType.setPrice(rs.getBigDecimal("price"));
                list.add(serviceType);
            }
        } catch (SQLException ex) {
            logger.fatal(ex);
        } finally {
            Source.cleanUp(ps, connect);
        }
        return list;
    }

    @Override
    public ServiceType findById(int id) {
        try {
            connect = pool.getConnection();
            ps = connect.prepareStatement(FIND_BY_ID);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                serviceType = new ServiceType();
                serviceType.setIdservice_type(rs.getInt("idservice_type"));
                serviceType.setTypeName(rs.getString("type_name"));
                serviceType.setPrice(rs.getBigDecimal("price"));
            }
        } catch (SQLException ex) {
            logger.fatal(ex);
        } finally {
            Source.cleanUp(ps, connect);
        }
        return serviceType;
    }

    @Override
    public void add(ServiceType serviceType) {
        try {
            connect = pool.getConnection();
            ps = connect.prepareStatement(ADD);
            ps.setString(2, serviceType.getTypeName());
            ps.setBigDecimal(3, serviceType.getPrice());
            ps.executeUpdate();
        } catch (SQLException ex) {
            logger.fatal(ex);
        } finally {
            Source.cleanUp(ps, connect);
        }
    }

    @Override
    public void update(ServiceType serviceType) {
        try {
            connect = pool.getConnection();
            ps = connect.prepareStatement(ADD);
            ps.setString(1, serviceType.getTypeName());
            ps.setBigDecimal(2, serviceType.getPrice());
            ps.setInt(3, serviceType.getIdservice_type());
            ps.executeUpdate();
        } catch (SQLException ex) {
            logger.fatal(ex);
        } finally {
            Source.cleanUp(ps, connect);
        }
    }
}
