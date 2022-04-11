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

                  <table>
                      <thead>
                      <tr>
                          <td style="text-align: center">Book name</td>
                          <td style="text-align: center">Count pages</td>
                          <td style="text-align: center">Author name</td>
                      </tr>
                      </thead>
                      <tbody>
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
                      </tbody>
                  </table>
    </body>
</html>
