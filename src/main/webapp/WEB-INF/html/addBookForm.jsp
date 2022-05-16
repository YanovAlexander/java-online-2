<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
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
                    <label for="bookName">Book name:</label><br>
                    <form:input type="text" path="name" class="form-control" id="bookName" name="bookName"/><form:errors path="name" cssClass="error"/> <br>
                    <label for="countPages">Count pages:</label><br>
                    <form:input type="number" path="countPages" class="form-control" id="countPages" name="countPages"/> <form:errors path="countPages" cssClass="error"/> <br>
                    <label for="authorId">Select author:</label><br>
                    <form:checkboxes path="authors" items="${authors}" itemLabel="firstName"/> <form:errors path="authors" cssClass="error"/> <br>
                </div>
                    <input type="submit" value="Submit">
           </form:form>
            <c:if test="${not empty errorMessage}">
              <c:forEach items="${errorMessage.errors}" var="error">
                 <p style="color:red">${error}</p>
              </c:forEach>
            </c:if>
        </div>
    </body>
</html>
