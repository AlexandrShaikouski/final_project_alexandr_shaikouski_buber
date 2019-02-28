<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Buber</title>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script>
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/vendors/iconfonts/mdi/css/materialdesignicons.min.css">
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/vendors/css/vendor.bundle.base.css">
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/style.css">
    <link rel="shortcut icon" href="${pageContext.servletContext.contextPath}/images/favicon.png"/>
</head>
<body>

<div class="container-scroller">
    <nav class="navbar default-layout-navbar col-lg-12 col-12 p-0 fixed-top d-flex flex-row">
        <div class="text-center navbar-brand-wrapper d-flex align-items-center justify-content-center">
            <a class="navbar-brand brand-logo" href="${pageContext.servletContext.contextPath}/jsp/admin/admin.jsp"><img src="${pageContext.servletContext.contextPath}/images/logo.svg" alt="logo"/></a>
            <a class="navbar-brand brand-logo-mini" href="${pageContext.servletContext.contextPath}/jsp/admin/admin.jsp"><img src="${pageContext.servletContext.contextPath}/images/logo-mini.svg" alt="logo"/></a>
        </div>
        <button class="navbar-toggler navbar-toggler-right d-lg-none align-self-center" type="button" data-toggle="offcanvas">
            <span class="mdi mdi-menu"></span>
        </button>
    </nav>

    <div class="container-fluid page-body-wrapper">

        <nav class="sidebar sidebar-offcanvas" id="sidebar">
            <ul class="nav">
                <li class="nav-item active">
                    <a class="nav-link" href="${pageContext.servletContext.contextPath}/jsp/admin/admin.jsp">
                        <span class="menu-title">Buber</span>
                        <i class="mdi mdi-home menu-icon"></i>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" data-toggle="collapse" href="#ui-basic" aria-expanded="false"
                       aria-controls="ui-basic">
                        <span class="menu-title">CRUD</span>
                        <i class="menu-arrow"></i>
                        <i class="mdi mdi-crosshairs-gps menu-icon"></i>
                    </a>
                    <div class="collapse" id="ui-basic">
                        <ul class="nav flex-column sub-menu">
                            <li class="nav-item">
                                <form style="margin: 0" action="${pageContext.servletContext.contextPath}/demo">
                                    <input type="hidden" name="command" value="list_users">
                                    <button class="btn btn-link btn-sm" type="submit">List users</button>
                                </form>
                            </li>
                            <li class="nav-item">
                                <form style="margin: 0" action="${pageContext.servletContext.contextPath}/demo">
                                    <input type="hidden" name="command" value="list_drivers">
                                    <button class="btn btn-link btn-sm" type="submit">List drivers</button>
                                </form>
                            </li>
                            <li class="nav-item">
                                <form style="margin: 0" action="${pageContext.servletContext.contextPath}/demo">
                                    <input type="hidden" name="command" value="list_orders">
                                    <button class="btn btn-link btn-sm" type="submit">List orders</button>
                                </form>
                            </li>
                            <li class="nav-item">
                                <form style="margin: 0">
                                    <input type="hidden" name="command" vavlue="list_drivers">
                                    <button class="btn btn-link btn-sm" type="button"><a href="${pageContext.servletContext.contextPath}/jsp/admin/create-user.jsp"> Create user</a></button>
                                </form>
                            </li>
                        </ul>
                    </div>
                </li>
            </ul>
        </nav>
        <div class="main-panel">
            <div class="content-wrapper">