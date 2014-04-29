/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bionic.telestation.tags;

import java.io.IOException;
import javax.servlet.jsp.JspWriter;
import static javax.servlet.jsp.tagext.Tag.SKIP_BODY;
import javax.servlet.jsp.tagext.TagSupport;
import org.bionic.telestation.daosql.MySQLDaoUser;
import org.bionic.telestation.util.DAOFactory;
import org.bionic.telestation.util.Log;

/**
 *
 * @author MAX
 */
public class CountBannedTag extends TagSupport {

    private final static org.apache.log4j.Logger logger = Log.getInstance().logger;
    
    
    @Override
    public int doStartTag() {
        //        //test with session attributes
//        String result = null;
//        List<User> list = (List) pageContext.getAttribute("bannesList", PageContext.SESSION_SCOPE);
//        
//        if (list != null) {
//            result = " " + list.size() + ".";
//        } else {
//            result = " 0.";
//        }
        MySQLDaoUser dao = DAOFactory.createMySQLDaoUser();
        int size = dao.findBanned().size();
        String str = "Number of banned users = " + size + ".";
        
        try {
            JspWriter out = pageContext.getOut();
            out.write(str);
        } catch (IOException ex) {
            logger.error(ex);
        }
        return SKIP_BODY;
    }
}
