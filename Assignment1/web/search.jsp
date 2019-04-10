<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:if test="${empty sessionScope.MEMBER}">
   <c:redirect url="login.jsp"/>
</c:if>
<!DOCTYPE html>
<html>
   <head>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      <title>Search Page</title>
   </head>
   <style>
      .button {
         background-color: green;
         border: none;
         color: white;
         margin-left: 15px;
         margin-right: 15px;
         text-decoration: none;
         display: inline-block;
         font-size: 16px;
         cursor: pointer;
      }
   </style>
   <body>
      Welcome <b>${sessionScope.MEMBER.username}</b> !!!<br>
      <a href="logout">Logout</a>
      <h1>Search Shoes</h1>
      <form action="search.jsp">
         <table>
            <tr>
               <td>Description: </td>
               <td><input type="text" name="keyword" placeholder="Keyword..."/></td>
               <td>
                  <input type="submit" value="Search" class="button"/>
                  <input type="reset" value="Reset" class="button"/>
               </td>
            </tr>
         </table>
      </form>
      <jsp:useBean id="bean" scope="page" class="dao.ShoesDAO"/>
      <c:if test="${not empty param.keyword}">
         <c:set var="searchVal" value="${param.keyword}"/>
         <c:set var="listShoes" value="${bean.findByDescription(param.keyword)}"/>
         <c:choose>
            <c:when test="${empty listShoes}">
               <h2>Not found</h2>
            </c:when>
            <c:otherwise>
               <table border="1" style="border-collapse: collapse">
                  <thead>
                     <tr>
                        <th>No.</th>
                        <th>Description</th>
                        <th>Sizes - Price</th>
                        <th>Action</th>
                     </tr>
                  </thead>
                  <tbody>
                     <c:forEach var="m" items="${listShoes}" varStatus="index">
                     <form action="addCart">
                        <c:set var="ss" value="${bean.findSize(m.id)}"/>
                        <tr>
                           <td>${index.count}</td>
                           <td>${m.description}</td>
                           <td>
                              <select name="sizes" style="width: 100px">
                                 <c:forEach var="s" items="${ss}">
                                    <option value="${s.key}">
                                       ${s.key} - ${s.value}$
                                    </option>
                                 </c:forEach>
                              </select>
                           </td>
                           <td>
                              <input type="hidden" name="searchValue" value="${searchVal}" />
                              <input type="hidden" name="id" value="${m.id}" />
                              <button class="button">Add to Cart</button>
                           </td>
                        </tr> 
                     </form>
                     </c:forEach>
               </tbody>
            </table>
         </c:otherwise>
      </c:choose>
   </c:if>
   <a href="viewCart.jsp">View Cart</a>
</body>
</html>
