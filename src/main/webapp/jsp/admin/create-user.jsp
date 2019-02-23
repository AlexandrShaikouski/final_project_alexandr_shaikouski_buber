
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="header.jsp"></c:import>
<script>
    function valid(form) {
        var email_regex = /^[-a-z0-9!#$%&'*+=?^_`{|}~]+(?:\.[-a-z0-9!#$%&'*+=?^_`{|}~]+)*@(?:[a-z0-9]([-a-z0-9]{0,61}[a-z0-9])?\.)*(?:aero|arpa|asia|biz|cat|com|coop|edu|gov|info|int|jobs|mil|mobi|museum|name|net|org|pro|tel|travel|[a-z][a-z])$/i;
        var phone_regex = /^(\+375)((25)|(29)|(33)|(44))[0-9]{7}$/;
        var login_regex = /^[a-zA-Z0-9]{2,45}$/;
        var first_name_regex = /^[a-zA-Zа-яА-Я]{2,45}$/;
        var password_regex = /^[a-zA-Z0-9!@#$%^&*]{6,45}$/;
        var login = form.login.value;
        var password = form.passwordUser.value;
        var repassword = form.repasswordUser.value;
        var email = form.email.value;
        var phone = form.phone.value;
        var firstName = form.first_name.value;
        var counter = 0;
        if (!login_regex.test(login)) {
            $('#login').addClass('alert-danger');
            counter = counter + 1;
        }else{
            $('#login').removeClass('alert-danger');
        }

        if(password != repassword){
            $('#repasswordUser').addClass('alert-danger');
            counter = counter + 1;
        }else{
            $('#repasswordUser').removeClass('alert-danger');
        }
        if(!password_regex.test(password)){
            $('#passwordUser').addClass('alert-danger');
            $('#repasswordUser').addClass('alert-danger');
            counter = counter + 1;
        }else{
            $('#passwordUser').removeClass('alert-danger');
        }

        if (!email_regex.test(email)) {
            $('#email').addClass('alert-danger');
            counter = counter + 1;
        }else{
            $('#email').removeClass('alert-danger');
        }

        if (!phone_regex.test(phone)) {
            $('#phone').addClass('alert-danger');
            counter = counter + 1;
        }else{
            $('#phone').removeClass('alert-danger');
        }
        if(!first_name_regex.test(firstName)){
            $('#first_name').addClass('alert-danger');
            counter = counter + 1;
        }else{
            $('#first_name').removeClass('alert-danger');
        }


        if(counter > 0){
            return false;
        }else {
            $('button[name="button_register"]').attr("type","submit");
            $('button[name="button_register"]').trigger('submit');
        }

    }
</script>
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
                        <input name="login" type="text" class="form-control" id="login"  placeholder="Login">
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
                        <c:if test="${error_message != 'correct'}">
                            <h2 class="display-3">${error_message}</h2>
                        </c:if>
                    </div>
                    <button name="button_register" type="button" onclick="valid(document.getElementById('form'))" class="btn btn-gradient-primary mr-2">Submit</button>
                    <a class="btn btn-light" href="${pageContext.servletContext.contextPath}/jsp/admin/admin.jsp">Cancel</a>
                </form>
            </div>
        </div>
    </div>
</div>
<c:import url="footer.jsp"></c:import>