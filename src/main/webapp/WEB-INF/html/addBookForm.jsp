<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
    <head>
         <c:import url="${contextPath}/WEB-INF/html/header.jsp"/>
    </head>

    <body>
        <c:import url="${contextPath}/WEB-INF/html/navibar.jsp"/>
        <div class="container">
            <form:form action="/books/addBook" method="post" modelAttribute="bookDto">
                <div class="form-group">
                    <form:label path="name">Book name:</form:label><br>
                    <form:input type="text" class="form-control" id="bookName" placeholder="Enter book name" name="bookName" path="name"/><br>
                    <form:label path="countPages">Count pages:</form:label><br>
                    <form:input type="number" class="form-control" id="countPages" placeholder="Enter pages count" name="countPages" path="countPages"/><br>
                    <form:label path="authors">Select author:</form:label><br>
                     <c:forEach items="${authors}" var="author">
                        <form:checkbox path="authors" value="${author}"/> <c:out value="${author.firstName}"/> <c:out value="${author.lastName}"/> <br>
                     </c:forEach>
                </div>
                    <input type="submit" value="Submit"/>
           </form:form>
            <c:if test="${not empty errorMessage}">
              <c:forEach items="${errorMessage.errors}" var="error">
                 <p style="color:red">${error}</p>
              </c:forEach>
            </c:if>
        </div>
    </body>
</html>
