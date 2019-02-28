<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="header.jsp"></c:import>
<c:choose>
    <c:when test="${not empty listUsers}">
        <div class="row">
            <div class="col-lg-12">
                <div class="card">
                    <div class="card-body">
                        <table class="table table-dark">
                            <thead>
                            <tr>
                                <th>ID</th>
                                <th>Login</th>
                                <th>Email</th>
                                <th>Phone</th>
                                <th>Info</th>
                                <th>Delete user</th>
                            </tr>
                            </thead>

                            <tbody>

                            <c:forEach items="${listUsers}" var="user" varStatus="status">
                                <tr>
                                    <td>${user.id}</td>
                                    <td>${user.login}</td>
                                    <td>${user.email}</td>
                                    <td>${user.phone}</td>

                                    <td>
                                        <form method="post" action="${pageContext.servletContext.contextPath}/demo">
                                            <input name="role" type="hidden" value="user"/>
                                            <input type="hidden" name="command" value="info_user">
                                            <input type="hidden" name="id" value="${user.id}">
                                            <button class="btn-link" type="submit">Info</button>
                                        </form>
                                    </td>
                                    <td>
                                        <form method="post"
                                              action="${pageContext.servletContext.contextPath}/demo">
                                            <input name="role" type="hidden" value="user"/>
                                            <input name="command" type="hidden" value="delete_user"/>
                                            <input name="id" type="hidden" value="${user.id}"/>
                                            <button class="btn-link" type="submit">Delete</button>
                                        </form>
                                    </td>
                                </tr>

                            </c:forEach>

                            </tbody>

                        </table>
                    </div>
                </div>
            </div>
        </div>
    </c:when>
    <c:otherwise>
        <h1 class="display1">No results</h1>
    </c:otherwise>
</c:choose>
<c:import url="footer.jsp"></c:import>