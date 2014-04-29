/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bionic.telestation.util;

import org.bionic.telestation.daosql.MySQLDAOCheck;
import org.bionic.telestation.daosql.MySQLDaoService;
import org.bionic.telestation.daosql.MySQLDaoServiceType;
import org.bionic.telestation.daosql.MySQLDaoUser;
import org.bionic.telestation.daosql.MySQLDaoUserType;

/**
 *
 * @author MAX
 */
public class DAOFactory {

    public static MySQLDAOCheck createMySQLDAOCheck() {
        return new MySQLDAOCheck();
    }

    public static MySQLDaoService createMySQLDaoService() {
        return new MySQLDaoService();
    }

    public static MySQLDaoServiceType createMySQLDaoServiceType() {
        return new MySQLDaoServiceType();
    }

    public static MySQLDaoUser createMySQLDaoUser() {
        return new MySQLDaoUser();
    }

    public static MySQLDaoUserType createMySQLDaoUserType() {
        return new MySQLDaoUserType();
    }
}
