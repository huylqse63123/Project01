/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author cwalk
 */
public class DBUtils implements Serializable{
   
   public static Connection getConnection() throws NamingException, SQLException {
      Context c = new InitialContext();
      Context tomcatCtx = (Context) c.lookup("java:comp/env");
      DataSource ds = (DataSource) tomcatCtx.lookup("SE1276");
      Connection conn = ds.getConnection();
      return conn;
   }
}
