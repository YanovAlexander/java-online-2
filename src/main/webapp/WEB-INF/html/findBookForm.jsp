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

            <form action="/findBook">
              <label for="bookName">Book name:</label><br>
              <input type="text" id="bookName" name="bookName"><br>
              <input type="submit" value="Submit">
            </form>
    </body>
</html>
