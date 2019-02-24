<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Purple Admin</title>
    <link rel="stylesheet"
          href="${pageContext.servletContext.contextPath}/vendors/iconfonts/mdi/css/materialdesignicons.min.css">
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/vendors/css/vendor.bundle.base.css">
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/style.css">
    <link rel="shortcut icon" href="${pageContext.servletContext.contextPath}/images/favicon.png"/>

    <style type="text/css">
        @-webkit-keyframes chartjs-render-animation {
            from {
                opacity: 0.99
            }
            to {
                opacity: 1
            }
        }

        @keyframes chartjs-render-animation {
            from {
                opacity: 0.99
            }
            to {
                opacity: 1
            }
        }

        .chartjs-render-monitor {
            -webkit-animation: chartjs-render-animation 0.001s;
            animation: chartjs-render-animation 0.001s;
        }</style>
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
                        <h4>New here?</h4>
                        <h6 class="font-weight-light">Signing up is easy. It only takes a few steps</h6>
                        <form method="POST" class="forms-sample" action="${pageContext.servletContext.contextPath}/demo"
                              id="form_register">

                            <input type="hidden" name="command" value="create_user">
                            <input type="hidden" name="flag" value="user">
                            <div class="form-group">
                                <label for="login">Login</label>
                                <input name="login" type="text" class="form-control" id="login" placeholder="Login">
                            </div>
                            <div class="form-group">
                                <label for="passwordUser">Password</label>
                                <input name="passwordUser" type="password" class="form-control" id="passwordUser"
                                       placeholder="Password">
                            </div>
                            <div class="form-group">
                                <label for="repasswordUser">Re Password</label>
                                <input name="repasswordUser" type="password" class="form-control" id="repasswordUser"
                                       placeholder="Password">
                            </div>
                            <div class="form-group">
                                <label for="email">Email address</label>
                                <input name="email" type="text" class="form-control" id="email" placeholder="Email">
                            </div>
                            <div class="form-group">
                                <label for="phone">Number phone</label>
                                <input name="phone" type="text" class="form-control" id="phone"
                                       placeholder="+375XXXXXXXXX">
                            </div>
                            <div class="form-group">
                                <label for="first_name">First name</label>
                                <input name="first_name" type="text" class="form-control" id="first_name"
                                       placeholder="First name">
                            </div>
                            <div class="mb-4">
                                <div class="form-check">
                                    <label class="form-check-label text-muted">
                                        <input type="checkbox" class="form-check-input">
                                        I agree to all Terms &amp; Conditions
                                        <i class="input-helper"></i></label>
                                </div>
                            </div>
                            <c:if test="${error_message != 'correct'}">
                            <h2 class="display-3">${error_message}</h2>
                            </c:if>
                            <div class="mt-3">
                                <button name="button_register" type="button"
                                        onclick="valid(document.getElementById('form_register'))"
                                        class="btn btn-block btn-gradient-primary btn-lg font-weight-medium auth-form-btn">
                                    SignUp
                                </button>
                            </div>
                            <div class="text-center mt-4 font-weight-light">
                                Already have an account? <a href="${pageContext.servletContext.contextPath}"
                                                            class="text-primary">Login</a>
                            </div>
                    </div>

                    </form>
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
<script src="${pageContext.servletContext.contextPath}/js/jquery-3.3.1.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/js/script.js"></script>


</body>
</html>