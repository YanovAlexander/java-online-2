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
                    <input type="text" class="form-control" id="countPages" placeholder="Enter pages count" name="countPages"><br>
                    <label for="authorId">Select author:</label>
                      <select class="form-control" id="authorId" name="authorId">
                           <c:forEach items="${authors}" var="author">
                                   <option value="${author.id}"><c:out value="${author.firstName}"/> <c:out value="${author.lastName}"/></option>
                           </c:forEach>
                      </select>
                </div>
                    <input type="submit" value="Submit">
            </form>
        </div>
    </body>
</html>
