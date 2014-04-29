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
import org.bionic.telestation.entity.Check;
import org.bionic.telestation.entity.Service;
import org.bionic.telestation.entity.User;
import org.bionic.telestation.idaoEntities.IDAOCheck;
import org.bionic.telestation.util.DAOFactory;
import org.bionic.telestation.util.Log;
import org.bionic.telestation.util.Source;

/**
 *
 * @author MAX
 */
public class MySQLDAOCheck implements IDAOCheck {

    private final static org.apache.log4j.Logger logger = Log.getInstance().logger;
    private Source pool = Source.getInstance();
    private Connection connect;
    private PreparedStatement prepStatement;
    private ResultSet rs;
    private List<Check> list;
    private Check check;
    private User user;
    private Service service;
    private Integer userId;
    private Integer serviceId;
    private MySQLDaoUser userDao = DAOFactory.createMySQLDaoUser();
    private MySQLDaoService serviceDao = DAOFactory.createMySQLDaoService();
    
    //query strings
    private final static String FIND_ALL = "select * from telephonedb.check";
    private final static String FIND_BY_CHECK_ID = "select * from telephonedb.check where check_id=?";
    private final static String FIND_BY_USER_ID = "select * from telephonedb.check where user_id=?";
    private final static String FIND_ALL_EXPIRED = "select * from telephonedb.check where expired=1 and paid=0";
    private final static String FIND_ALL_BANNED = "select * from telephonedb.check where paid=0";
    private final static String ADD = "insert into telephonedb.check "
            + "(check_id, user_id, service_id, price, expired, paid, creation_date, expire_date) "
            + "values (?, ?, ?, ?, ?, ?, ?, ?)";
    private final static String UPDATE = "update telephonedb.check "
            + "set user_id=?, service_id=?, price=?, expired=?, paid=?, creation_date=?, expire_date=? "
            + "where check_id=?";

    @Override
    //TODO rewrite method
    public List<Check> findAll() {
        try {
            connect = pool.getConnection();
            prepStatement = connect.prepareStatement(FIND_ALL);
            rs = prepStatement.executeQuery();
            list = new LinkedList();
            while (rs.next()) {
                userId = rs.getInt("user_id");
                serviceId = rs.getInt("service_id");
                user = userDao.findById(userId);
                service = serviceDao.findByServiceID(serviceId);
                check = new Check();
                check.setCheckId(rs.getInt("check_id"));
                check.setUserId(user);
                check.setServiceId(service);
                check.setPrice(rs.getBigDecimal("price"));
                check.setExpired(rs.getBoolean("expired"));
                check.setPaid(rs.getBoolean("paid"));
                check.setCreationDate(rs.getDate("creation_date"));
                check.setExpireDate(rs.getDate("expire_date"));
                list.add(check);
            }
        } catch (SQLException ex) {
            logger.fatal(ex);
        } finally {
            Source.cleanUp(prepStatement, connect);
        }
        return list;
    }

    @Override
    public List<Check> findAllExpired() {
        try {
            connect = pool.getConnection();
            prepStatement = connect.prepareStatement(FIND_ALL_EXPIRED);
            rs = prepStatement.executeQuery();
            list = new LinkedList();
            while (rs.next()) {
                userId = rs.getInt("user_id");
                serviceId = rs.getInt("service_id");
                user = userDao.findById(userId);
                service = serviceDao.findByServiceID(serviceId);
                check = new Check();
                check.setCheckId(rs.getInt("check_id"));
                check.setUserId(user);
                check.setServiceId(service);
                check.setPrice(rs.getBigDecimal("price"));
                check.setExpired(rs.getBoolean("expired"));
                check.setPaid(rs.getBoolean("paid"));
                check.setCreationDate(rs.getDate("creation_date"));
                check.setExpireDate(rs.getDate("expire_date"));
                list.add(check);
            }
        } catch (SQLException ex) {
            logger.fatal(ex);
        } finally {
            Source.cleanUp(prepStatement, connect);
        }
        return list;
    }

    @Override
    public List<Check> findAllNotPaid() {
        try {
            connect = pool.getConnection();
            prepStatement = connect.prepareStatement(FIND_ALL_BANNED);
            rs = prepStatement.executeQuery();
            list = new LinkedList();
            while (rs.next()) {
                userId = rs.getInt("user_id");
                serviceId = rs.getInt("service_id");
                user = userDao.findById(userId);
                service = serviceDao.findByServiceID(serviceId);
                check = new Check();
                check.setCheckId(rs.getInt("check_id"));
                check.setUserId(user);
                check.setServiceId(service);
                check.setPrice(rs.getBigDecimal("price"));
                check.setExpired(rs.getBoolean("expired"));
                check.setPaid(rs.getBoolean("paid"));
                check.setCreationDate(rs.getDate("creation_date"));
                check.setExpireDate(rs.getDate("expire_date"));
                list.add(check);
            }
        } catch (SQLException ex) {
            logger.fatal(ex);
        } finally {
            Source.cleanUp(prepStatement, connect);
        }
        return list;
    }

    @Override
    public Check findByCheckID(int ID) {
        try {
            connect = pool.getConnection();
            prepStatement = connect.prepareStatement(FIND_BY_CHECK_ID);
            prepStatement.setInt(1, ID);
            rs = prepStatement.executeQuery();
            while (rs.next()) {
                userId = rs.getInt("user_id");
                serviceId = rs.getInt("service_id");
                user = userDao.findById(userId);
                service = serviceDao.findByServiceID(serviceId);
                check = new Check();
                
                check.setCheckId(rs.getInt("check_id"));
                check.setUserId(user);
                check.setServiceId(service);
                check.setPrice(rs.getBigDecimal("price"));
                check.setExpired(rs.getBoolean("expired"));
                check.setPaid(rs.getBoolean("paid"));
                check.setCreationDate(rs.getDate("creation_date"));
                check.setExpireDate(rs.getDate("expire_date"));
            }
        } catch (SQLException ex) {
            logger.fatal(ex);
        } finally {
            Source.cleanUp(prepStatement, connect);
        }
        return check;
    }

    @Override
    public List<Check> findByUserID(int ID) {
        try {
            connect = pool.getConnection();
            prepStatement = connect.prepareStatement(FIND_BY_USER_ID);
            prepStatement.setInt(1, ID);
            rs = prepStatement.executeQuery();
            list = new LinkedList();
            while (rs.next()) {
                userId = rs.getInt("user_id");
                serviceId = rs.getInt("service_id");
                user = userDao.findById(userId);
                service = serviceDao.findByServiceID(serviceId);
                check = new Check();
                
                check.setCheckId(rs.getInt("check_id"));
                check.setUserId(user);
                check.setServiceId(service);
                check.setPrice(rs.getBigDecimal("price"));
                check.setExpired(rs.getBoolean("expired"));
                check.setPaid(rs.getBoolean("paid"));
                check.setCreationDate(rs.getDate("creation_date"));
                check.setExpireDate(rs.getDate("expire_date"));
                list.add(check);
            }
        } catch (SQLException ex) {
            logger.fatal(ex);
        } finally {
            Source.cleanUp(prepStatement, connect);
        }
        return list;
    }

    
    @Override
    public void add(Check check) {
        try {
            connect = pool.getConnection();
            prepStatement = connect.prepareStatement(ADD);
            prepStatement.setInt(1, check.getCheckId());
            prepStatement.setInt(2, check.getUserId().getIdUser());
            prepStatement.setInt(3, check.getServiceId().getIdService());
            prepStatement.setBigDecimal(4, check.getPrice());
            prepStatement.setBoolean(5, check.getExpired());
            prepStatement.setBoolean(6, check.getPaid());
            prepStatement.setDate(7, check.getCreationDate());
            prepStatement.setDate(8, check.getExpireDate());
            prepStatement.executeUpdate();
        } catch (SQLException ex) {
            logger.fatal(ex);
        } finally {
            Source.cleanUp(prepStatement, connect);
        }
    }

    @Override
    public void update(Check check) {
        try {
            connect = pool.getConnection();
            prepStatement = connect.prepareStatement(UPDATE);
            prepStatement.setInt(1, check.getUserId().getIdUser());
            prepStatement.setInt(2, check.getServiceId().getIdService());
            prepStatement.setBigDecimal(3, check.getPrice());
            prepStatement.setBoolean(4, check.getExpired());
            prepStatement.setBoolean(5, check.getPaid());
            prepStatement.setDate(6, check.getCreationDate());
            prepStatement.setDate(7, check.getExpireDate());
            prepStatement.setInt (8, check.getCheckId());
            prepStatement.executeUpdate();
        } catch (SQLException ex) {
            logger.fatal(ex);
        } finally {
            Source.cleanUp(prepStatement, connect);
        }
    }
}
