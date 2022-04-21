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
            <form action="/findBook">
                 <div class="form-group">
                    <label for="bookName">Book name:</label><br>
                    <input type="text" class="form-control" id="bookName" placeholder="Enter book name" name="bookName"><br>
                 </div>
                     <input type="submit" value="Submit">
            </form>

                  <table class="table table-hover">
                      <thead>
                      <tr>
                          <td>Book name</td>
                          <td>Count pages</td>
                          <td>Author name</td>
                      </tr>
                      </thead>
                      <tbody>
                         <c:forEach items="${books}" var="book">
                              <tr>
                                  <td>
                                      <c:out value="${book.name}"/>
                                  </td>
                                  <td>
                                      <c:out value="${book.countPages}"/>
                                  </td>
                                  <td>
                                      <c:forEach items="${book.authors}" var="author">
                                      <a href="/findAuthor?authorId=${author.id}"> <c:out value="${author.firstName}"/> <c:out value="${author.lastName}"/> </a>
                                      </c:forEach>
                                  </td>
                              </tr>
                         </c:forEach>
                      </tbody>
                  </table>
        </div>
    </body>
</html>
