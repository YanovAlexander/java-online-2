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
        <div class="container">
            <form action="/addBook" method="post">
                <div class="form-group">
                    <label for="bookName">Book name:</label><br>
                    <input type="text" class="form-control" id="bookName" placeholder="Enter book name" name="bookName"><br>
                    <label for="countPages">Count pages:</label><br>
                    <input type="number" class="form-control" id="countPages" placeholder="Enter pages count" name="countPages"><br>
                    <label for="authorId">Select author:</label><br>
                    <c:forEach items="${authors}" var="author">
                      <input type="checkbox" name="authorId" value="${author.id}"> <c:out value="${author.firstName}"/> <c:out value="${author.lastName}"/> <br>
                    </c:forEach>
                </div>
                    <input type="submit" value="Submit">
           </form>
            <c:if test="${not empty errorMessage}">
              <c:forEach items="${errorMessage.errors}" var="error">
                 <p style="color:red">${error}</p>
              </c:forEach>
            </c:if>
        </div>
    </body>
</html>
