<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Reset password</title>

    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script>
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/vendors/iconfonts/mdi/css/materialdesignicons.min.css">
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/vendors/css/vendor.bundle.base.css">
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/style.css">
    <link rel="shortcut icon" href="${pageContext.servletContext.contextPath}/images/favicon.png">

</head>

<body>
<div class="container-scroller">
    <div class="container-fluid page-body-wrapper full-page-wrapper">
        <div class="content-wrapper d-flex align-items-center auth">
            <div class="row w-100">
                <div class="col-lg-4 mx-auto">
                    <div class="auth-form-light text-left p-5">
                        <div class="brand-logo">
                            <img src="${pageContext.servletContext.contextPath}/images/logo.svg">
                        </div>
                        <input type="hidden" style="background: none">
                        <c:choose>
                        <c:when test="${reset == null}">
                            <h4>Enter your email</h4>
                            <form method="POST" class="pt-3" action="${pageContext.servletContext.contextPath}/demo">
                                <input type="hidden" name="command" value="reset_password">
                                <input type="hidden" name="reset" value="send_key">
                                <div class="form-group">
                                    <input name="email" type="text" class="form-control" id="email" placeholder="Email">
                                </div>
                                <div class="mt-3">
                                    <button class="btn btn-block btn-gradient-primary btn-lg font-weight-medium auth-form-btn"
                                            type="submit">Enter
                                    </button>
                                </div>
                                <div class="text-center mt-4 font-weight-light">
                                    Don't have an account? <a
                                        href="${pageContext.servletContext.contextPath}/pages/register.jsp"
                                        class="text-primary">Create</a>
                                </div>
                            </form>
                        </c:when>
                        <c:when test="${reset == 'send_key'}">
                            <h4>Enter key</h4>
                            <form method="POST" class="pt-3" action="${pageContext.servletContext.contextPath}/demo">
                                <input type="hidden" name="command" value="reset_password">
                                <input type="hidden" name="reset" value="reset_password">
                                <input type="hidden" name="role" value="${role}">
                                <input type="hidden" name="email" value="${email}">
                                <div class="form-group">
                                    <input name="key" type="text" class="form-control" id="key" placeholder="Key">
                                </div>
                                <div class="mt-3">
                                    <button class="btn btn-block btn-gradient-primary btn-lg font-weight-medium auth-form-btn"
                                            type="submit">Enter</button>
                                </div>
                            </form>
                        </c:when>
                        <c:when test="${reset == 'reset_password'}">
                            <h4>New password</h4>
                            <form method="POST" class="pt-3" action="${pageContext.servletContext.contextPath}/demo" id="reset_password">
                                <input type="hidden" name="command" value="reset_password">
                                <input type="hidden" name="role" value="${role}">
                                <input type="hidden" name="email" value="${email}">
                                <input type="hidden" name="reset" value="finish">
                                <div class="form-group">
                                    <input name="passwordUser" type="password" class="form-control" id="passwordUser" placeholder="Password">
                                </div>
                                <div class="form-group">
                                    <input name="repasswordUser" type="password" class="form-control" id="repasswordUser" placeholder="Repeat password">
                                </div>
                                <div class="mt-3">
                                    <button name="button_reset_password" type="button" class="btn btn-block btn-gradient-primary btn-lg font-weight-medium auth-form-btn" onclick="validrepassword(document.getElementById('reset_password'))">Enter</button>
                                </div>
                            </form>
                        </c:when>
                        </c:choose>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="${pageContext.servletContext.contextPath}/vendors/js/vendor.bundle.base.js"></script>
<script src="${pageContext.servletContext.contextPath}/vendors/js/vendor.bundle.addons.js"></script>
<script src="${pageContext.servletContext.contextPath}/js/off-canvas.js"></script>
<script src="${pageContext.servletContext.contextPath}/js/misc.js"></script>
<script src="${pageContext.servletContext.contextPath}/js/script.js"></script>

</body>
</html>