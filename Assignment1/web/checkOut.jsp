<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:if test="${empty sessionScope.MEMBER}">
   <c:redirect url="login.jsp"/>
</c:if>
<c:set var="checkOut" value="${sessionScope.CART.getABC()}" />
<jsp:useBean id="now" class="java.util.Date"/>
<fmt:formatDate var="date" value="${now}" pattern="dd/MM/yyyy"/>
<!DOCTYPE html>
<html>
   <head>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      <title>Checkout Page</title>
   </head>
   <style>
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
      <c:set var="dto" value="${sessionScope.MEMBER}"/>
      Welcome <b>${sessionScope.MEMBER.username}</b> !!!<br>
      <a href="logout">Logout</a>
      <h1>Finishing Your Order</h1><br>
      <table border="1" style="border-collapse: collapse">
         <thead>
            <tr>
               <th>No</th>
               <th>Product</th>
               <th>Quantity</th>
               <th>Price</th>
               <th>Total</th>
            </tr>
         </thead>
         <tbody>
            <c:forEach var="m" items="${checkOut}" varStatus="index">
               <tr>
                  <td>${index.count}</td>
                  <td>${m.shoes.id}</td>
                  <td>${m.quantity}</td>
                  <td>${m.shoes.price}$</td>
                  <td>${m.totalLine()}$</td>
               </tr>
            </c:forEach>
            <tr>
               <td style="text-align: right" colspan="4"><b>Total</b></td>
               <td>${sessionScope.CART.total()}$</td>
            </tr>
         </tbody>
      </table>
      <br>
      <br>
      <h2>Customer Information</h2>
      <form action="confirm">
         <table>
            <tr>
               <td>OrderID: </td>
               <td><b>${sessionScope.CART.code}</b></td>
               <td>Date: </td>
               <td>${date}</td>
            </tr>
            <tr>
               <td>Customer: </td>
               <td><b>${dto.getFullname()}</b></td>
               <td>Phone: </td>
               <td><b>${dto.phone}</b></td>
            </tr>
            <tr>
               <td>Address:</td>
               <td colspan="3"><input type="text" name="address" value="" style="width: 460px"/></td>
            </tr>
            <tr>
               <td>Receiver*: </td>
               <td><input type="text" name="receiver" value=""required /></td>
               <td>Receiver Phone*: </td>
               <td><input type="text" name="rPhone" value="" required/></td>
            </tr>
            <tr>
               <td colspan="4" style="text-align: center">
                  <button type="submit" class="button">Confirm</button>
                  <a class="button" href="viewCart.jsp" style="text-decoration: none; padding-left: 30px; padding-right: 30px">Back</a>
                  <a class="button" href="removeCart.jsp" style="text-decoration: none">Exit</a>
               </td>
            </tr>
         </table>
      </form>
   </body>
</html>
