<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

           <nav class="navbar navbar-inverse">
              <div class="container">
                <div class="navbar-header">
                  <a class="navbar-brand" href="/">GoIT Library</a>
                </div>
                <ul class="nav navbar-nav">
                  <li><a href="/">Home</a></li>
                  <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Books <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                      <li><a href="/books/findBookForm">Find</a></li>
                      <security:authorize access="hasRole('ROLE_ADMIN')">
                            <li><a href="/books/addBookForm">Create</a></li>
                      </security:authorize>
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
                <ul class="navbar-nav">
                  <li>
                  <a style="float: right" href="${pageContext.request.contextPath}/logout">Logout</a>
                  </li>
                </ul>
              </div>
            </nav>