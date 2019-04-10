<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${empty sessionScope.MEMBER}">
   <c:redirect url="login.jsp"/>
</c:if>
<c:set var="cart" value="${sessionScope.CART}" />
<!DOCTYPE html>
<html>
   <head>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      <title>View Cart</title>
   </head>
   <style>
      button {
         background-color: green;
         border: none;
         color: white;
         padding: 5px 32px;
         display: inline-block;
         font-size: 16px;
         cursor: pointer;
      }
   </style>
   <body>
      Welcome <b>${sessionScope.MEMBER.username}</b> !!!<br>
      <a href="logout">Logout</a>
      <h1>Your Cart Details</h1>
      <c:choose>
         <c:when test="${empty cart.cart}">
            <h2>Cart empty! Buy something</h2>
         </c:when>
         <c:otherwise>
            <table border="1" style="border-collapse: collapse">
               <thead>
                  <tr>
                     <th>No.</th>
                     <th>Description</th>
                     <th>Quantity</th>
                     <th>Price</th>
                     <th>Sizes</th>
                     <th>Total</th>
                     <th>Action</th>
                  </tr>
               </thead>
               <tbody>
               <form action="remove">
                  <c:forEach var="entry" items="${cart.cart}" varStatus="index">
                     <tr>
                        <td>${index.count}</td>
                        <td>${entry.value.shoes.description}</td>
                        <td>${entry.value.quantity}</td>
                        <td>${entry.value.shoes.price}$</td>
                        <td>${entry.value.shoes.size}</td>
                        <td>${entry.value.totalLine()}$</td>
                        <td>
                           <input type="checkbox" name="chkItem" value="${entry.key}" />
                        </td>
                     </tr>
                  </c:forEach>
                  <tr>
                     <td colspan="5" style="text-align: right; "><b>Total</b></td>
                     <td>${cart.total()}$</td>
                     <td>
                        <button>Remove</button>
                     </td>
                  </tr>  
               </form>
            </tbody>
         </table>
      </c:otherwise>
   </c:choose>
   <c:if test="${not empty requestScope.listErr}">
      <ul>
         <c:forEach var="m" items="${requestScope.listErr}">
            <li style="color: red">${m.description} size ${m.size} has ${m.quantity} left!!!</li>
         </c:forEach>
      </ul>
   </c:if>
   <c:if test="${not empty cart.cart}">
      <a href="checkOut.jsp">Checkout</a><br>
   </c:if>
   <a href="search.jsp">Search Page</a>
</body>
</html>
