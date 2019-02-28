<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="header.jsp"></c:import>
<c:choose>
    <c:when test="${not empty listDrivers}">

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
                                <th>Delete driver</th>
                            </tr>
                            </thead>

                            <tbody>
                            <c:forEach items="${listDrivers}" var="driver" varStatus="status">
                                <tr>
                                    <td>${driver.id}</td>
                                    <td>${driver.login}</td>
                                    <td>${driver.email}</td>
                                    <td>${driver.phone}</td>
                                    <td>
                                        <form method="post" action="${pageContext.servletContext.contextPath}/demo">
                                            <input name="role" type="hidden" value="driver"/>
                                            <input type="hidden" name="command" value="info_user">
                                            <input type="hidden" name="id" value="${driver.id}">
                                            <button class="btn-link" type="submit">Info</button>
                                        </form>
                                    </td>
                                    <td>
                                        <form method="post" action="${pageContext.servletContext.contextPath}/demo">
                                            <input name="role" type="hidden" value="driver"/>
                                            <input name="command" type="hidden" value="delete_user"/>
                                            <input name="id" type="hidden" value="${driver.id}"/>
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