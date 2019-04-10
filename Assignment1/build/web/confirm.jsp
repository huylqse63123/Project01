<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${empty sessionScope.MEMBER}">
   <c:redirect url="login.jsp"/>
</c:if>
<c:if test="${not empty sessionScope.MEMBER}">
   <c:set var="dto" value="${sessionScope.MEMBER}"/>
</c:if>
<!DOCTYPE html>
<html>
   <head>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      <title>Confirm Page</title>
   </head>
   <style>
      table td{
         width: 100px;
      }
      .field {
         width: 120px;
      }
      .button {
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
      .button:hover {
         padding: 7px 34px;
         background-color: #4e714e;
      }
   </style>
   <body>
      Welcome <b>${dto.username}</b> !!!<br>
      <a href="logout">Logout</a>
      <c:if test="${not empty sessionScope.CART}">
         <h1>Your information</h1>
         <form action="pay" method="POST">
            <table>
               <tr>
                  <td class="field">CustomerID:</td>
                  <td><b>${dto.username}</b></td>
                  <td class="field">OrderID: </td>
                  <td><b>${sessionScope.CART.code}</b></td>
               </tr>
               <tr>
                  <td class="field">Customer:</td>
                  <td><b>${dto.getFullname()}</b></td>
                  <td class="field">Phone:</td>
                  <td><b>${dto.phone}</b></td>
               </tr>
               <tr>
                  <td class="field">Address:</td>
                  <td colspan="3"><b>${param.address}</b></td>
               </tr>
               <tr>
                  <td class="field">Receiver:</td>
                  <td><b>${param.receiver}</b></td>
                  <td class="field">Receiver's Phone:</td>
                  <td><b>${param.rPhone}</b></td>
               </tr>
               <tr>
                  <td colspan="4" style="text-align: center">
                     <button type="submit" class="button">OK</button>
                     <a href="checkOut.jsp" class="button">Cancel</a>
                  </td>
               </tr>
            </table>
            <br>
         </form>
      </c:if>
      <c:if test="${not empty requestScope.SUCCESS}">
         <h2 style="color: red">${requestScope.SUCCESS}</h2><br>
         <a href="search.jsp" class="button">Continue shopping</a>
      </c:if>
   </body>
</html>
