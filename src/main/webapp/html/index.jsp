<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
    <head>
        <title>Library</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    </head>

    <body>
            <nav class="navbar navbar-inverse">
              <div class="container-fluid">
                <div class="navbar-header">
                  <a class="navbar-brand" href="/">GoIT Library</a>
                </div>
                <ul class="nav navbar-nav">
                  <li><a href="/">Home</a></li>
                  <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Books <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                      <li><a href="/findBookForm">Find</a></li>
                      <li><a href="#">Create</a></li>
                    </ul>
                  </li>
                  <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Journals <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                      <li><a href="#">Find</a></li>
                      <li><a href="#">Create</a></li>
                    </ul>
                  </li>
                  <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Authors <span class="caret"></span></a>
                   <ul class="dropdown-menu">
                     <li><a href="#">Find</a></li>
                     <li><a href="#">Create</a></li>
                   </ul>
                  </li>
                </ul>
              </div>
            </nav>
    </body>
</html>
