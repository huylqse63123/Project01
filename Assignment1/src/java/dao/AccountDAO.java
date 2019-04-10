/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.AccountDTO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import utils.DBUtils;

/**
 *
 * @author cwalk
 */
public class AccountDAO implements Serializable {

   public AccountDTO checkLogin(String username, String password) throws NamingException, SQLException {
      String sql = "SELECT * FROM tbl_account WHERE username=? AND password=?";
      try (Connection conn = DBUtils.getConnection();
              PreparedStatement pstm = conn.prepareStatement(sql)) {
         pstm.setString(1, username);
         pstm.setString(2, password);
         try (ResultSet rs = pstm.executeQuery()) {
            if (rs.next()) {
               String cusID = rs.getString("username");
               String sql1 = "SELECT * FROM tbl_customer WHERE custID=?";
               try(PreparedStatement ps1 = conn.prepareStatement(sql1)) {
                  ps1.setString(1, cusID);
                  try(ResultSet rs1 = ps1.executeQuery()) {
                     if (rs1.next()) {
                        return new AccountDTO(cusID, rs1.getString("lastName"), rs1.getString("middleName"), 
                                rs1.getString("firstName"), rs1.getString("address"), rs1.getString("phone"));
                     }
                  }
               }
            }
         }
      }
      return null;
   }

   public boolean checkUsername(String username) throws NamingException, SQLException {
      String sql = "SELECT * FROM tbl_account WHERE username=?";
      try (Connection conn = DBUtils.getConnection();
              PreparedStatement pstm = conn.prepareStatement(sql)) {
         pstm.setString(1, username);
         try (ResultSet rs = pstm.executeQuery()) {
            if (rs.next()) {
               return true;
            }
         }
      }
      return false;
   }

   public void insert(String username, String password, String lastName, String middleName,
           String firstName, String address, String phone, int level) throws NamingException, SQLException {
      String sql1 = "INSERT INTO tbl_account VALUES(?,?)";
      String sql2 = "INSERT INTO tbl_customer VALUES(?,?,?,?,?,?,?)";
      try (Connection conn = DBUtils.getConnection()) {
         try (PreparedStatement ps = conn.prepareStatement(sql1)) {
            ps.setString(1, username);
            ps.setString(2, password);
            ps.executeUpdate();
         }
         try (PreparedStatement ps1 = conn.prepareStatement(sql2)) {
            ps1.setString(1, username);
            ps1.setString(2, lastName);
            ps1.setString(3, middleName);
            ps1.setString(4, firstName);
            ps1.setString(5, address);
            ps1.setString(6, phone);
            ps1.setInt(7, level);
            ps1.executeUpdate();
         }
      }
   }
}
