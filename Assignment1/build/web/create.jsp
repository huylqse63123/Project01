<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:if test="${not empty requestScope.ERROR}">
   <c:set var="err" value="${requestScope.ERROR}"/>
</c:if>
<!DOCTYPE html>
<html>
   <head>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      <title>JSP Page</title>
   </head>
   <body>
      <form action="register" method="POST">
         <table>
            <tr>
               <td>Username: </td>
               <td>
                  <input type="text" name="username" value="${param.username}" />
               </td>
            </tr>
            <c:if test="${err.usernameLengthErr != null}">
               <tr>
                  <td colspan="2" style="color: red">${err.usernameLengthErr}</td>
               </tr>
            </c:if>
            <c:if test="${err.usernameExist != null}">
               <tr>
                  <td colspan="2" style="color: red">${err.usernameExist}</td>
               </tr>
            </c:if>
            <tr>
               <td>Password: </td>
               <td>
                  <input type="password" name="password" value="${param.password}" />
               </td>
            </tr>
            <c:if test="${err.passwordErr != null}">
               <tr>
                  <td colspan="2" style="color: red">${err.passwordErr}</td>
               </tr>
            </c:if>
            <tr>
               <td>Confirm Password: </td>
               <td>
                  <input type="password" name="confirmPassword" value="${param.confirmPassword}" />
               </td>
            </tr>
            <c:if test="${err.confirmPassword != null}">
               <tr>
                  <td colspan="2" style="color: red">${err.confirmPassword}</td>
               </tr>
            </c:if>
            <tr>
               <td>Last Name: </td>
               <td>
                  <input type="text" name="lname" value="${param.lname}" />
               </td>
            </tr>
            <c:if test="${err.lNameErr != null}">
               <tr>
                  <td colspan="2" style="color: red">${err.lNameErr}</td>
               </tr>
            </c:if>
            <tr>
               <td>Middle Name: </td>
               <td>
                  <input type="text" name="mname" value="${param.mname}" />
               </td>
            </tr>
            <c:if test="${err.mNameErr != null}">
               <tr>
                  <td colspan="2" style="color: red">${err.mNameErr}</td>
               </tr>
            </c:if>
            <tr>
               <td>First Name: </td>
               <td>
                  <input type="text" name="fname" value="${param.fname}" />
               </td>
            </tr>
            <c:if test="${err.fNameErr != null}">
               <tr>
                  <td colspan="2" style="color: red">${err.fNameErr}</td>
               </tr>
            </c:if>
            <tr>
               <td>Address: </td>
               <td>
                  <input type="text" name="address" value="${param.address}" />
               </td>
            </tr>
            <c:if test="${err.address != null}">
               <tr>
                  <td colspan="2" style="color: red">${err.address}</td>
               </tr>
            </c:if>
            <tr>
               <td>Phone: </td>
               <td>
                  <input type="text" name="phone" value="${param.phone}" />
               </td>
            </tr>
            <c:if test="${err.phone != null}">
               <tr>
                  <td colspan="2" style="color: red">${err.phone}</td>
               </tr>
            </c:if>
            <tr>
               <td>Level: </td>
               <td>
                  <select name="chkLevel" style="width: 80px">
                     <option>1</option>
                     <option>2</option>
                  </select>
               </td>
            </tr>
            <tr>
               <td colspan="2" style="text-align: center">
                  <button type="submit" style="margin-right: 20px">Register</button>
                  <button type="reset" style="margin-left: 20px">Reset</button>
               </td>
            </tr>
         </table>
      </form>
      <span style="color: red">Click <a href="login.jsp">here </a>to login page!!!</span>
   </body>
</html>
