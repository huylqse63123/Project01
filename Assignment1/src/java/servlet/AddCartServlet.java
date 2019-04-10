/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dao.ShoesDAO;
import dto.CartObj;
import dto.ShoesDTO;
import java.io.IOException;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author cwalk
 */
@WebServlet(name = "AddCartServlet", urlPatterns = {"/AddCartServlet"})
public class AddCartServlet extends HttpServlet {

   /**
    * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
    * methods.
    *
    * @param request servlet request
    * @param response servlet response
    * @throws ServletException if a servlet-specific error occurs
    * @throws IOException if an I/O error occurs
    */
   protected void processRequest(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
      response.setContentType("text/html;charset=UTF-8");
      HttpSession session = request.getSession();
      CartObj cart = (CartObj) session.getAttribute("CART");
      String size = request.getParameter("sizes");
      String id = request.getParameter("id");
      String searchValue = request.getParameter("searchValue");
      float price = 0;
      if (cart == null) {
         cart = new CartObj();
         session.setAttribute("CART", cart);
      }
      ShoesDTO shoes = null;
      String sizeID = "";
      try {
         ShoesDAO dao = new ShoesDAO();
         shoes = dao.searchShoesByID(id);
         sizeID = dao.findSizeID(size);
         price = dao.findPrice(shoes.getId(), sizeID);
      } catch (NamingException e) {
         log("NamingException_AddCartServlet " + e.getMessage());
      } catch(NumberFormatException e) {
         log("NumberFormatException_AddCartServlet " + e.getMessage());
      } catch(SQLException e) {
         log("SQLException_AddCartServlet " + e.getMessage());
      } finally {
         String urlRewriting = "search.jsp?keyword=" + searchValue;
         if (shoes != null) {
            shoes.setSize(Integer.valueOf(size));
            shoes.setPrice(price);
            cart.addProduct(shoes, size, sizeID);
         }
         response.sendRedirect(urlRewriting);
      }
   }

   // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
   /**
    * Handles the HTTP <code>GET</code> method.
    *
    * @param request servlet request
    * @param response servlet response
    * @throws ServletException if a servlet-specific error occurs
    * @throws IOException if an I/O error occurs
    */
   @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
      processRequest(request, response);
   }

   /**
    * Handles the HTTP <code>POST</code> method.
    *
    * @param request servlet request
    * @param response servlet response
    * @throws ServletException if a servlet-specific error occurs
    * @throws IOException if an I/O error occurs
    */
   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
      processRequest(request, response);
   }

   /**
    * Returns a short description of the servlet.
    *
    * @return a String containing servlet description
    */
   @Override
   public String getServletInfo() {
      return "Short description";
   }// </editor-fold>

}
