/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dao.AccountDAO;
import utils.Validation;
import java.io.IOException;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author cwalk
 */
@WebServlet(name = "RegisterServlet", urlPatterns = {"/RegisterServlet"})
public class RegisterServlet extends HttpServlet {

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
      String username = request.getParameter("username");
      String password = request.getParameter("password");
      String confirm = request.getParameter("confirmPassword");
      String lastName = request.getParameter("lname");
      String middleName = request.getParameter("mname");
      String firstName = request.getParameter("fname");
      String address = request.getParameter("address");
      String phone = request.getParameter("phone");
      String level = request.getParameter("chkLevel");
      Validation v = new Validation();
      String url = "create.jsp";
      boolean error = false;
      try {
         AccountDAO dao = new AccountDAO();
         boolean checkUsername = dao.checkUsername(username);
         if (checkUsername) {
            error = true;
            v.setUsernameExist("Username is exist");
         }
         if (username.trim().length() > 20 || username.isEmpty()) {
            error = true;
            v.setUsernameLengthErr("Username is required smaller than 20 and not null");
         }
         if (password.trim().length() > 30 || password.isEmpty()) {
            error = true;
            v.setPasswordErr("Password is required smaller than 30 and not null");
         }
         if (!confirm.equals(password)) {
            error = true;
            v.setConfirmPassword("Confirm password");
         }
         if (lastName.trim().length() > 15 || lastName.isEmpty()) {
            error = true;
            v.setlNameErr("Last name is required smaller than 15 and not null");
         }
         if (middleName.trim().length() > 30 || middleName.isEmpty()) {
            error = true;
            v.setmNameErr("Middle name is required smaller than 30 and not null");
         }
         if (firstName.trim().length() > 15 || firstName.isEmpty()) {
            error = true;
            v.setfNameErr("First name is required smaller than 15 and not null");
         }
         if (address.trim().length() > 250 || address.isEmpty()) {
            error = true;
            v.setAddress("Address is required smaller than 250 and not null");
         }
         if (!phone.matches("\\d{9}")) {
            error = true;
            v.setPhone("Phone is required smaller than 9 and is number");
         }
         if (error) {
            request.setAttribute("ERROR", v);
         } else {
            dao.insert(username, password, lastName, middleName, firstName, address, phone, Integer.valueOf(level));
            url = "login.jsp";
         }
      } catch (NumberFormatException e) {
         log("NumberFormatException_RegisterServlet " + e.getMessage());
      } catch (SQLException e) {
         log("SQLException_RegisterServlet " + e.getMessage());
      } catch (NamingException e) {
         log("NamingException_RegisterServlet " + e.getMessage());
      } finally {
         RequestDispatcher rd = request.getRequestDispatcher(url);
         rd.forward(request, response);
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
