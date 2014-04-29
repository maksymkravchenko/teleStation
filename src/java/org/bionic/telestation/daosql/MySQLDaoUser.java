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
import org.bionic.telestation.entity.User;
import org.bionic.telestation.entity.UserType;
import org.bionic.telestation.idaoEntities.IDAOUser;
import org.bionic.telestation.util.DAOFactory;
import org.bionic.telestation.util.Log;
import org.bionic.telestation.util.Source;

/**
 *
 * @author MAX
 */
public class MySQLDaoUser implements IDAOUser{
    
    private final static org.apache.log4j.Logger logger = Log.getInstance().logger;
    private Source pool = Source.getInstance();
    private Connection connect;
    private PreparedStatement ps;
    private List<User> list;
    private UserType userType;
    private ResultSet rs;
    private User user;
    private MySQLDaoUserType dao = DAOFactory.createMySQLDaoUserType();
    private Integer userTypeId;
    //query strings
    private final static String FIND = "select * from user where id_user = ? and password = ?";
    private final static String FIND_ALL = "select * from telephonedb.user";
    private final static String FIND_BANNED = "select * from telephonedb.user where banned = 1";
    private final static String FIND_ALL_BY_USER_TYPE = "select * from telephonedb.user where user_type_id = ?";
    private final static String FIND_BY_ID = "select * from telephonedb.user where id_user=?";
    private final static String FIND_BY_NAME = "select * from telephonedb.user where name=?";
    private final static String ADD =         
            "insert into telephonedb.user "
            + "(id_user, user_type_id, name, password, loggedin, banned) "
            + "values(?, ?, ?, ?, ?, ?)";
    private final static String UPDATE = "update telephonedb.user "
            + "set user_type_id=?, name=?, password=?, loggedin=?, banned=? "
            + "where id_user = ?";
  
    @Override
    public List<User> findAll() {
        try {
            connect = pool.getConnection();    
            ps = connect.prepareStatement(FIND_ALL);
            rs = ps.executeQuery();
            list = new LinkedList();
            while (rs.next()) {
                userTypeId = rs.getInt("user_type_id");
                userType = dao.findById(userTypeId);
                user = new User();
                user.setIdUser(rs.getInt("id_user"));
                user.setUserTypeId(userType);
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                user.setLoggedin(rs.getBoolean("loggedin"));
                user.setBanned(rs.getBoolean("banned"));
                list.add(user);
            }
        } catch (SQLException ex) {
            logger.fatal(ex);
        } finally {
            Source.cleanUp(ps, connect);
        }
        return list;
    }
    
    @Override
    public List<User> findBanned() {
        try {
            connect = pool.getConnection();  
            ps = connect.prepareStatement(FIND_BANNED);
            rs = ps.executeQuery();
            list = new LinkedList();
            while (rs.next()) {
                userTypeId = rs.getInt("user_type_id");
                userType = dao.findById(userTypeId);
                user = new User();
                user.setIdUser(rs.getInt("id_user"));
                user.setUserTypeId(userType);
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                user.setLoggedin(rs.getBoolean("loggedin"));
                user.setBanned(rs.getBoolean("banned"));
                list.add(user);
            }
        } catch (SQLException ex) {
            logger.fatal(ex);
        } finally {
            Source.cleanUp(ps, connect);
        }
        return list;
    }

    @Override
    public List<User> findAllByUserType(UserType uType) {
        try {
            connect = pool.getConnection();  
            ps = connect.prepareStatement(FIND_ALL_BY_USER_TYPE);
            ps.setObject(1, uType);
            rs = ps.executeQuery();
            list = new LinkedList();
            while (rs.next()) {
                userTypeId = rs.getInt("user_type_id");
                userType = dao.findById(userTypeId);
                user = new User();
                user.setIdUser(rs.getInt("id_user"));
                user.setUserTypeId(userType);
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                user.setLoggedin(rs.getBoolean("loggedin"));
                user.setBanned(rs.getBoolean("banned"));
            }
            list.add(user);
        } catch (SQLException ex) {
            logger.fatal(ex);
        } finally {
            Source.cleanUp(ps, connect);
        }
        return list;
    }

    @Override
    public User findById(int ID) {
        try {
            connect = pool.getConnection();  
            ps = connect.prepareStatement(FIND_BY_ID);
            ps.setInt(1, ID);
            rs = ps.executeQuery();
            while (rs.next()) {
                userTypeId = rs.getInt("user_type_id");
                userType = dao.findById(userTypeId);
                user = new User();
                user.setIdUser(rs.getInt("id_user"));
                user.setUserTypeId(userType);
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                user.setLoggedin(rs.getBoolean("loggedin"));
                user.setBanned(rs.getBoolean("banned"));
            }
        } catch (SQLException ex) {
            logger.fatal(ex);
        } finally {
            Source.cleanUp(ps, connect);
        }
        return user;
    }

    @Override
    public User findByName(String name) {
        try {
            connect = pool.getConnection();
            ps = connect.prepareStatement(FIND_BY_NAME);
            ps.setString(1, name);
            rs = ps.executeQuery();
            while (rs.next()) {
                userTypeId = rs.getInt("user_type_id");
                userType = dao.findById(userTypeId);
                user = new User();
                user.setIdUser(rs.getInt("id_user"));
                user.setUserTypeId(userType);
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                user.setLoggedin(rs.getBoolean("loggedin"));
                user.setBanned(rs.getBoolean("banned"));
            }
            list.add(user);
        } catch (SQLException ex) {
            logger.fatal(ex);
        } finally {
            Source.cleanUp(ps, connect);
        }
        return user;
    }
    
    @Override
    public void add(User user) {
        try {
            connect = pool.getConnection();
            ps = connect.prepareStatement(ADD);
            ps.setInt(1, user.getIdUser());
            ps.setInt(2, user.getUserTypeId().getIdUser());
            ps.setString(3, user.getName());
            ps.setString(4, user.getPassword());
            ps.setBoolean(5, user.isLoggedin());
            ps.setBoolean(6, user.isBanned());
            ps.executeUpdate();
        } catch (SQLException ex) {
            logger.fatal(ex);
        } finally {
            Source.cleanUp(ps, connect);
        }
    }

    @Override
    public void update(User user) {
        try {
            connect = pool.getConnection();
            ps = connect.prepareStatement(UPDATE);
            ps.setInt(1, user.getUserTypeId().getIdUser());
            ps.setString(2, user.getName());
            ps.setString(3, user.getPassword());
            ps.setBoolean(4, user.isLoggedin());
            ps.setBoolean(5, user.isBanned());
            ps.setInt(6, user.getIdUser());
//            //test
//            boolean result = ps.execute();
            ps.executeUpdate();
        } catch (SQLException ex) {
            logger.fatal(ex);
        } finally {
            Source.cleanUp(ps, connect);
        }
    }
}
