/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.AccountDTO;
import dto.CartObj;
import dto.Item;
import dto.ShoesDTO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.TreeMap;
import java.util.function.Consumer;
import javax.naming.NamingException;
import utils.DBUtils;

/**
 *
 * @author cwalk
 */
public class ShoesDAO implements Serializable {

   public List<ShoesDTO> findByDescription(String keyword) throws SQLException, NamingException {
      List<ShoesDTO> list = new ArrayList<>();
      String sql = "SELECT * FROM tbl_shoes WHERE description LIKE ?";
      try (Connection conn = DBUtils.getConnection();
              PreparedStatement pstm = conn.prepareStatement(sql)) {
         pstm.setString(1, "%" + keyword + "%");
         try (ResultSet rs = pstm.executeQuery()) {
            while (rs.next()) {
               list.add(new ShoesDTO(rs.getString("shoesID"), rs.getString("description"), rs.getInt("quantity")));
            }
         }
      }
      return list;
   }

   //Hỗ trợ show select box với Size-Price
   public TreeMap<String, Float> findSize(String id) throws NamingException, SQLException {
      List<String> list = new ArrayList<>();
      String sql = "SELECT sizes FROM dbo.tbl_sizes WHERE id IN "
              + "(SELECT sizeID FROM tbl_shoesDetail WHERE shoesID=?)";
      String sql1 = "SELECT price FROM tbl_shoesDetail WHERE shoesID = ? AND sizeID = ?";
      TreeMap<String, Float> size_price = new TreeMap<>();
      try (Connection conn = DBUtils.getConnection();
              PreparedStatement pstm = conn.prepareStatement(sql)) {
         pstm.setString(1, id);
         try (ResultSet rs = pstm.executeQuery()) {
            while (rs.next()) {
               String sizeID = findSizeID(rs.getString("sizes"));
               try (PreparedStatement ps1 = conn.prepareStatement(sql1)) {
                  ps1.setString(1, id);
                  ps1.setString(2, sizeID);
                  try (ResultSet rs1 = ps1.executeQuery()) {
                     if (rs1.next()) {
                        size_price.put(rs.getString("sizes"), rs1.getFloat("price"));
                     }
                  }
               }
            }
         }
      }
      return size_price;
   }

   public float findPrice(String shoesID, String sizeID) throws NamingException, SQLException {
      String sql = "SELECT price FROM tbl_shoesDetail WHERE shoesID = ? AND sizeID = ?";
      try (Connection conn = DBUtils.getConnection();
              PreparedStatement pstm = conn.prepareStatement(sql)) {
         pstm.setString(1, shoesID);
         pstm.setString(2, sizeID);
         try (ResultSet rs = pstm.executeQuery()) {
            if (rs.next()) {
               return rs.getFloat("price");
            }
         }
      }
      return -1;
   }

   public String findSizeID(String size) throws NamingException, SQLException {
      String sql = "SELECT id FROM tbl_sizes WHERE sizes=?";
      try (Connection conn = DBUtils.getConnection();
              PreparedStatement pstm = conn.prepareStatement(sql)) {
         pstm.setInt(1, Integer.valueOf(size));
         try (ResultSet rs = pstm.executeQuery()) {
            if (rs.next()) {
               return rs.getString("id");
            }
         }
      }
      return null;
   }

   public ShoesDTO searchShoesByID(String id) throws NamingException, SQLException {
      String sql = "SELECT * FROM tbl_shoes WHERE shoesID=?";
      try (Connection conn = DBUtils.getConnection();
              PreparedStatement pstm = conn.prepareStatement(sql)) {
         pstm.setString(1, id);
         try (ResultSet rs = pstm.executeQuery()) {
            if (rs.next()) {
               return new ShoesDTO(rs.getString("shoesID"), rs.getString("description"), rs.getInt("quantity"));
            }
         }
      }
      return null;
   }

   //Kiểm tra số lượng giày, size để thanh toán
   public List<ShoesDTO> checkShoes(CartObj cart) throws NamingException, SQLException {
      String sql = "SELECT * FROM tbl_shoesDetail WHERE shoesID=? AND sizeID=? AND tbl_shoesDetail.quantity - ? < 0";
      String sql1 = "SELECT description FROM tbl_shoes WHERE shoesID=?";
      List<ShoesDTO> list = new ArrayList<>();
      try (Connection conn = DBUtils.getConnection();
              PreparedStatement ps1 = conn.prepareStatement(sql)) {
         for (Item i : cart.getCart().values()) {
            ps1.setString(1, i.getShoes().getId());
            ps1.setString(2, i.getSizeID());
            ps1.setInt(3, i.getQuantity());
            try (ResultSet rs1 = ps1.executeQuery()) {
               if (rs1.next()) {
                  try (PreparedStatement ps2 = conn.prepareStatement(sql1)) {
                     ps2.setString(1, i.getShoes().getId());
                     try (ResultSet rs2 = ps2.executeQuery()) {
                        if (rs2.next()) {
                           int size = i.getShoes().getSize();
                           list.add(new ShoesDTO(rs1.getString("shoesID"), rs2.getString("description"), rs1.getFloat("price"), rs1.getInt("quantity"), size));
                        }
                     }
                  }
               }
            }
         }
      }
      return list;
   }

   public boolean checkCart(CartObj cart) throws NamingException, SQLException {
      String sql = "UPDATE tbl_shoesDetail SET quantity = quantity - ? "
              + "WHERE shoesID = ? AND sizeID = ? AND tbl_shoesDetail.quantity - ? >= 0";
      Connection conn = null;
      PreparedStatement ps = null;
      try {
         conn = DBUtils.getConnection();
         if (conn != null) {
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql);
            for (Item i : cart.getCart().values()) {
               ps.setInt(1, i.getQuantity());
               ps.setString(2, i.getShoes().getId());
               ps.setString(3, i.getSizeID());
               ps.setInt(4, i.getQuantity());
               int n = ps.executeUpdate();
               if (n <= 0) {
                  return false;
               }
            }
         }
      } catch (SQLException | NamingException e) {
         e.printStackTrace();
      } finally {
         conn.rollback();
         if (ps != null) {
            ps.close();
         }
         if (conn != null) {
            conn.close();
         }
      }
      return true;
   }

   public void saveToDB(CartObj cart, AccountDTO user) throws SQLException, NamingException {
      Connection con = null;
      try {
         con = DBUtils.getConnection();
         if (con != null) {
            con.setAutoCommit(false);
            String sql1 = "UPDATE tbl_shoesDetail SET quantity = quantity - ? WHERE shoesID = ? AND sizeID = ?";
            String sql2 = "UPDATE tbl_shoes SET quantity = quantity - ? WHERE shoesID = ?";
            String sql3 = "INSERT INTO tbl_orderDetail VALUES(?,?,?,?,?)";
            String sql4 = "INSERT INTO tbl_order VALUES(?,?,?,?)";
            try (PreparedStatement ps1 = con.prepareStatement(sql1)) {
               for (Item i : cart.getCart().values()) {
                  ps1.setInt(1, i.getQuantity());
                  ps1.setString(2, i.getShoes().getId());
                  ps1.setString(3, i.getSizeID());
                  ps1.executeUpdate();
               }
            }
            try (PreparedStatement ps2 = con.prepareStatement(sql2)) {
               for (Item i : cart.getCart().values()) {
                  ps2.setInt(1, i.getQuantity());
                  ps2.setString(2, i.getShoes().getId());
                  ps2.executeUpdate();
               }
            }
            try (PreparedStatement ps3 = con.prepareStatement(sql4)) {
               Calendar cal = Calendar.getInstance();
               Timestamp timestamp = new Timestamp(cal.getTimeInMillis());
               ps3.setString(1, cart.getCode());
               ps3.setTimestamp(2, timestamp);
               ps3.setString(3, user.getUsername());
               ps3.setFloat(4, cart.total());
               int r = ps3.executeUpdate();
               if (r > 0) {
                  try (PreparedStatement ps4 = con.prepareStatement(sql3)) {
                     for (Item i : cart.getCart().values()) {
                        ps4.setString(1, i.getShoes().getId());
                        ps4.setInt(2, i.getQuantity());
                        ps4.setFloat(3, i.getShoes().getPrice());
                        ps4.setFloat(4, i.totalLine());
                        ps4.setString(5, cart.getCode());
                        ps4.addBatch();
                     }
                     ps4.executeBatch();
                  }
               }
            }
            con.commit();
         }
      } catch (SQLException e) {
         con.rollback();
         e.printStackTrace();
      } finally {
         if (con != null) {
            con.close();
         }
      }
   }
}
