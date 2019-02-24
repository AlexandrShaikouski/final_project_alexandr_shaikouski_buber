
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="header.jsp"></c:import>

<div class="row">
    <div class="col-12 grid-margin stretch-card">
        <div class="card">
            <div class="card-body">
                <h4 class="card-title">Create user</h4>
                <form method="POST" class="forms-sample" action="${pageContext.servletContext.contextPath}/demo" id="form">
                    <input type="hidden" name="command" value="create_user">
                    <input type="hidden" name="flag" value="admin">
                    <div class="form-group">
                        <label for="login">Login</label>
                        <input name="login" title="Login must consist of Latin characters and numbers" type="text" class="form-control" id="login"  placeholder="Login">
                    </div>
                    <div class="form-group">
                        <label for="passwordUser">Password</label>
                        <input name="passwordUser" type="password" class="form-control" id="passwordUser" placeholder="Password">
                    </div>
                    <div class="form-group">
                        <label for="repasswordUser">Re Password</label>
                        <input name="repasswordUser" type="password" class="form-control" id="repasswordUser" placeholder="Password">
                    </div>
                    <div class="form-group">
                        <label for="email">Email address</label>
                        <input name="email" type="text" class="form-control" id="email" placeholder="Email">
                    </div>
                    <div class="form-group">
                        <label for="phone">Number phone</label>
                        <input name="phone" type="text" class="form-control" id="phone" placeholder="+375XXXXXXXXX">
                    </div>
                    <div class="form-group">
                        <label for="first_name">First name</label>
                        <input name="first_name" type="text" class="form-control" id="first_name" placeholder="First name">
                    </div>
                    <div class="row form-group">
                        <div class="col-md-4 form-check">
                            <label class="form-check-label">
                                <input type="radio" class="form-check-input" name="role" id="optionsRadios1" value="admin" >
                                Admin
                                <i class="input-helper"></i></label>
                        </div>
                        <div class="col-md-4 form-check">
                            <label class="form-check-label">
                                <input type="radio" class="form-check-input" name="role" id="optionsRadios2" value="client" checked="">
                                Client
                                <i class="input-helper"></i></label>
                        </div>
                        <div class="col-md-4 form-check">
                            <label class="form-check-label">
                                <input type="radio" class="form-check-input" name="role" id="optionsRadios3" value="driver" checked="">
                                Driver
                                <i class="input-helper"></i></label>
                        </div>

                    </div>
                    <button name="button_register" type="button" onclick="valid(document.getElementById('form'))" class="btn btn-gradient-primary mr-2">Submit</button>
                    <a class="btn btn-light" href="${pageContext.servletContext.contextPath}/jsp/admin/admin.jsp">Cancel</a>
                </form>
            </div>
        </div>
    </div>
</div>
<c:import url="footer.jsp"></c:import>