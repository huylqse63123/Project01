<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <head>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      <title>Login Page</title>
   </head>
   <body>
      <h1>Login Page</h1>
      <form action="login" method="POST">
         <table>
            <tr>
               <td>Username: </td>
               <td><input type="text" name="username" value="" /></td>
            </tr>
            <tr>
               <td>Password: </td>
               <td><input type="password" name="password" value="" /></td>
            </tr>
            <tr>
               <td><input type="submit" value="Login"/></td>
               <td><input type="reset" value="Reset" /></td>
            </tr>
         </table>
      </form>
   </body>
</html>
