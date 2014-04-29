package org.bionic.telestation.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.apache.log4j.Logger;

/**
 *
 * @author MAX
 */
public class Source {

    private final static Logger logger = Log.getInstance().logger;
    private static DataSource pool;
    private static Source instance;

    private Source() {
        initPool();
    }

    public static Source getInstance() {
        if (instance == null) {
            instance = new Source();
        }
        return instance;
    }
    
    private void initPool() {
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            pool = (DataSource) envContext.lookup("tele");
//            initContext = new InitialContext();
//            pool = (DataSource) initContext.lookup("java:/comp/env/jdbc/tele");
        } catch (NamingException ex) {
            logger.fatal(ex + "\n Source initPool exception");
        }
    }
    
    
    //not for pool connection
    Connection con;
    //
    public Connection getConnection() throws SQLException {
        return pool.getConnection();
      
//            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/telephonedb?user=root&password=root");
//       
//        return con;
    }

    public static void cleanUp(PreparedStatement preparedStatment, Connection connection) {
        if (preparedStatment != null) {
            try {
                preparedStatment.close();
            } catch (SQLException ex) {
                logger.error(ex);
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ex) {
                logger.error(ex);
            }
        }
    }
}
