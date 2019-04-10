<%-- 
    Document   : removeCart
    Created on : Oct 31, 2018, 9:43:11 PM
    Author     : cwalk
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <head>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      <title>Confirm Page</title>
   </head>
   <style>
      a {
         background-color: green;
         border: none;
         color: white;
         padding: 5px 32px;
         margin-left: 15px;
         margin-right: 15px;
         text-decoration: none;
         display: inline-block;
         font-size: 16px;
         cursor: pointer;
      }
   </style>
   <body>
      <h1>All your cart will removed. Are you sure!!!</h1>
      <a href="removeCart" style="text-decoration: none">OK</a>
      <a href="checkOut.jsp" style="text-decoration: none">Cancel</a>
   </body>
</html>
