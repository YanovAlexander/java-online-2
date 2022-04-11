<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
    <head>
         <c:import url="${contextPath}/WEB-INF/html/header.jsp"/>
    </head>

    <body>
         <c:import url="${contextPath}/WEB-INF/html/navibar.jsp"/>
                  <table>
                      <thead>
                      <tr>
                          <td style="text-align: center">First name</td>
                          <td style="text-align: center">Last name</td>
                          <td style="text-align: center">Email</td>
                      </tr>
                      </thead>
                      <tbody>
                              <tr>
                                  <td>
                                      <c:out value="${author.firstName}"/>
                                  </td>
                                  <td>
                                      <c:out value="${author.lastName}"/>
                                  </td>
                                  <td>
                                      <c:out value="${author.email}"/>
                                  </td>
                              </tr>
                      </tbody>
                  </table>
    </body>
</html>
