package org.bionic.telestation.daosql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import org.bionic.telestation.entity.UserType;
import org.bionic.telestation.idaoEntities.IDAOUserType;
import org.bionic.telestation.util.Log;
import org.bionic.telestation.util.Source;

/**
 *
 * @author MAX
 */
public class MySQLDaoUserType implements IDAOUserType {

    private final static org.apache.log4j.Logger logger = Log.getInstance().logger;
    private Source pool = Source.getInstance();
    private Connection connect;
    PreparedStatement ps;
    private List<UserType> list;
    private ResultSet rs;
    private UserType userType;
    
    //QUERY STRINGS
    private final static String FIND_ALL = "select * from user_type";
    private final static String FIND_BY_ID = "select * from user_type where iduser_type =?";
    private final static String ADD = "insert into telephonedb.user_type "
            + "(iduser_type, type_name) "
            + "values (?, ?)";
    private final static String UPDATE = "update telephonedb.user_type "
            + "type_name=? "
            + "where iduser_type=?";

    @Override
    public List<UserType> findAll() {
        try {
            connect = pool.getConnection();
            ps = connect.prepareStatement(FIND_ALL);
            rs = ps.executeQuery();
            list = new LinkedList();
            while (rs.next()) {
            userType = new UserType();
            userType.setIdUser(rs.getInt("iduser_type"));
            userType.setTypeName(rs.getString("type_name"));
            list.add(userType);
            }
        } catch (SQLException ex) {
            logger.fatal(ex);
        } finally {
            Source.cleanUp(ps, connect);
        }
        return list;
    }

    @Override
    public UserType findById(int id) {
        try {
            connect = pool.getConnection();
            ps = connect.prepareStatement(FIND_BY_ID);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                userType = new UserType();
                userType.setIdUser(rs.getInt("iduser_type"));
                userType.setTypeName(rs.getString("type_name"));
            }
        } catch (SQLException ex) {
            logger.fatal(ex);
        } finally {
            Source.cleanUp(ps, connect);
        }
        return userType;
    }

    @Override
    public void add(UserType userType) {
        try {
            connect = pool.getConnection();
            ps = connect.prepareStatement(ADD);
            ps.setString(2, userType.getTypeName());
            ps.executeUpdate();
        } catch (SQLException ex) {
            logger.fatal(ex);
        } finally {
            Source.cleanUp(ps, connect);
        }
    }

    @Override
    public void update(UserType userType) {
        try {
            connect = pool.getConnection();
            ps = connect.prepareStatement(UPDATE);
            ps.setString(1, userType.getTypeName());
            ps.setInt(1, userType.getIdUser());
            ps.executeUpdate();
        } catch (SQLException ex) {
            logger.fatal(ex);
        } finally {
            Source.cleanUp(ps, connect);
        }
    }
}
