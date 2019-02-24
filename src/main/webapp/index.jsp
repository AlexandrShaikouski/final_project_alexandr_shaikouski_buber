<p>Login data</p>
<a href="${pageContext.servletContext.contextPath}/jsp/admin/admin.jsp">admin = admin1</a></br>
<a href="${pageContext.servletContext.contextPath}/jsp/client/client.jsp">client = client</a></br>
<a href="${pageContext.servletContext.contextPath}/jsp/driver/driver.jsp">driver = driver</a></<br>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en"><head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Purple Admin</title>
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/vendors/iconfonts/mdi/css/materialdesignicons.min.css">
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/vendors/css/vendor.bundle.base.css">
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/style.css">
    <link rel="shortcut icon" href="${pageContext.servletContext.contextPath}/images/favicon.png">
    <style type="text/css">
    @-webkit-keyframes chartjs-render-animation{from{opacity:0.99}to{opacity:1}}@keyframes chartjs-render-animation{from{opacity:0.99}to{opacity:1}}.chartjs-render-monitor{-webkit-animation:chartjs-render-animation 0.001s;animation:chartjs-render-animation 0.001s;}</style></head>

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
                        <h4>Hello! let's get started</h4>
                        <h6 class="font-weight-light">Sign in to continue.</h6>
                        <form method="POST" class="pt-3" action="${pageContext.servletContext.contextPath}/demo">
                            <input type="hidden" name="command" value="sign_in">
                            <div class="form-group">
                                <input type="text" name="login" class="form-control form-control-lg" id="exampleInputEmail1" placeholder="Login">
                            </div>
                            <div class="form-group">
                                <input type="password" name="passwordUser" class="form-control form-control-lg" id="exampleInputPassword1" placeholder="Password">
                            </div>
                            <div class="mt-3">
                                <button class="btn btn-block btn-gradient-primary btn-lg font-weight-medium auth-form-btn" type="submit">SIGN IN</button>
                            </div>
                            <div class="my-2 d-flex justify-content-between align-items-center">
                                <div class="form-check">
                                    <label class="form-check-label text-muted">
                                        <input type="checkbox" class="form-check-input">
                                        Keep me signed in
                                        <i class="input-helper"></i></label>
                                </div>
                                <a href="#" class="auth-link text-black">Forgot password?</a>
                            </div>
                            <div class="text-center mt-4 font-weight-light">
                                Don't have an account? <a href="${pageContext.servletContext.contextPath}/pages/register.jsp" class="text-primary">Create</a>
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



</body></html>