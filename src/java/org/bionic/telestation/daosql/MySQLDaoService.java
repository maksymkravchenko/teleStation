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
import org.bionic.telestation.entity.Service;
import org.bionic.telestation.entity.ServiceType;
import org.bionic.telestation.idaoEntities.IDAOService;
import org.bionic.telestation.util.DAOFactory;
import org.bionic.telestation.util.Log;
import org.bionic.telestation.util.Source;

/**
 *
 * @author MAX
 */
public class MySQLDaoService implements IDAOService {

    private final static org.apache.log4j.Logger logger = Log.getInstance().logger;
    private Source pool = Source.getInstance();
    private Connection connect;
    private PreparedStatement prepStatement;
    private ResultSet rs;
    private List<Service> list;
    private Service service;
    private ServiceType serviceType;
    private Integer serviceTypeId;
    private MySQLDaoServiceType dao = DAOFactory.createMySQLDaoServiceType();
    
    
    //query strings
    private final static String FIND_ALL = "select * from telephonedb.service";
    private final static String FIND_BY_RECIPIENT_ID = "select * from telephonedb.service where recipient=?";
    private final static String FIND_ALL_BY_SENDER_ID = "select * from telephonedb.service where sender=?";
    private final static String FIND_BY_SERVICE_ID = "select * from telephonedb.service where id_service=?";
    private final static String ADD = "insert into telephonedb.service "
            + "(id_service, service_type_id, recipient, sender, date, duration) "
            + "values (?, ?, ?, ?, ?, ?)";
    private final static String UPDATE = "update telephonedb.service "
            + "set service_type_id=?, recipient=?, sender=?, date=?, duration=? "
            + "where id_service=?";

    @Override
    public List<Service> findAll() {
        try {
            connect = pool.getConnection();
            prepStatement = connect.prepareStatement(FIND_ALL);
            rs = prepStatement.executeQuery();
            list = new LinkedList();
            while (rs.next()) {
                serviceTypeId = rs.getInt("service_type_id");
                serviceType = dao.findById(serviceTypeId);
                service = new Service();
                service.setIdService(rs.getInt("id_service"));
                service.setServiceTypeId(serviceType);
                service.setRecipient(rs.getInt("recipient"));
                service.setSender(rs.getInt("sender"));
                service.setDate(rs.getDate("date"));
                service.setDuration(rs.getInt("duration"));
                list.add(service);
            }
        } catch (SQLException ex) {
            logger.fatal(ex);
        } finally {
            Source.cleanUp(prepStatement, connect);
        }
        return list;
    }

    @Override
    public List<Service> findAllByRecipientID(int ID) {
        try {
            connect = pool.getConnection();
            prepStatement = connect.prepareStatement(FIND_BY_RECIPIENT_ID);
            prepStatement.setInt(1, ID);
            rs = prepStatement.executeQuery();
            list = new LinkedList();
            while (rs.next()) {
                serviceTypeId = rs.getInt("service_type_id");
                serviceType = dao.findById(serviceTypeId);
                service = new Service();
                service.setIdService(rs.getInt("id_service"));
                service.setServiceTypeId(serviceType);
                service.setRecipient(rs.getInt("recipient"));
                service.setSender(rs.getInt("sender"));
                service.setDate(rs.getDate("date"));
                service.setDuration(rs.getInt("duration"));
                list.add(service);
            }
        } catch (SQLException ex) {
            logger.fatal(ex);
        } finally {
            Source.cleanUp(prepStatement, connect);
        }
        return list;
    }

    @Override
    public List<Service> findAllBySenderID(int ID) {
        try {
            connect = pool.getConnection();
            prepStatement = connect.prepareStatement(FIND_ALL_BY_SENDER_ID);
            prepStatement.setInt(1, ID);
            rs = prepStatement.executeQuery();
            list = new LinkedList();
            while (rs.next()) {
                serviceTypeId = rs.getInt("service_type_id");
                serviceType = dao.findById(serviceTypeId);
                service = new Service();
                service.setIdService(rs.getInt("id_service"));
                service.setServiceTypeId(serviceType);
                service.setRecipient(rs.getInt("recipient"));
                service.setSender(rs.getInt("sender"));
                service.setDate(rs.getDate("date"));
                service.setDuration(rs.getInt("duration"));
                list.add(service);
            }
        } catch (SQLException ex) {
            logger.fatal(ex);
        } finally {
            Source.cleanUp(prepStatement, connect);
        }
        return list;
    }

    @Override
    public Service findByServiceID(int ID) {
        try {
            connect = pool.getConnection();
            prepStatement = connect.prepareStatement(FIND_BY_SERVICE_ID);
            prepStatement.setInt(1, ID);
            rs = prepStatement.executeQuery();
            while (rs.next()) {
                serviceTypeId = rs.getInt("service_type_id");
                serviceType = dao.findById(serviceTypeId);
                service = new Service();
                service.setIdService(rs.getInt("id_service"));
                service.setServiceTypeId(serviceType);
                service.setRecipient(rs.getInt("recipient"));
                service.setSender(rs.getInt("sender"));
                service.setDate(rs.getDate("date"));
                service.setDuration(rs.getInt("duration"));
            }
        } catch (SQLException ex) {
            logger.fatal(ex);
        } finally {
            Source.cleanUp(prepStatement, connect);
        }
        return service;
    }

    @Override
    public void add(Service service) {
        try {
            connect = pool.getConnection();
            prepStatement = connect.prepareStatement(ADD);
            prepStatement.setInt(2, service.getServiceTypeId().getIdservice_type());
            prepStatement.setInt(3, service.getRecipient());
            prepStatement.setInt(4, service.getSender());
            prepStatement.setDate(5, service.getDate());
            prepStatement.setInt(6, service.getDuration());
            prepStatement.executeUpdate();
        } catch (SQLException ex) {
            logger.fatal(ex);
        } finally {
            Source.cleanUp(prepStatement, connect);
        }
    }

    @Override
    public void update(Service service) {
        try {
            connect = pool.getConnection();
            prepStatement = connect.prepareStatement(UPDATE);
            prepStatement.setInt(1, service.getServiceTypeId().getIdservice_type());
            prepStatement.setInt(2, service.getRecipient());
            prepStatement.setInt(3, service.getSender());
            prepStatement.setDate(4, service.getDate());
            prepStatement.setInt(5, service.getDuration());
            prepStatement.setInt(6, service.getIdService());
            prepStatement.executeUpdate();
        } catch (SQLException ex) {
            logger.fatal(ex);
        } finally {
            Source.cleanUp(prepStatement, connect);
        }
    }
}
