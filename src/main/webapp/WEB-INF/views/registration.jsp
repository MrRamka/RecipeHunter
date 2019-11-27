<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset='UTF-8'>
    <title>Registration page</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
    <script src="/resourses/js/main.js"></script>
    <link rel="stylesheet" href="/resourses/css/style.css" type="text/css">
</head>
<body>

<jsp:include page="includes/header.jsp"/>

<div class="container">
    <div class="row">
        <div class="col-lg-6 col-md-6 col-sm-12">
            <img src="/resourses/img/pizza_2.png" alt="Pizza Image" style="width: 100%">
        </div>
        <div class="col-lg-6 col-md-6 col-sm-12">
            <form class="form needs-validation" method="POST" action="/registration" novalidate>
                <div class="form-group">
                    <label for="user_name">Name</label>
                    <input type="text" class="form-control" id="user_name" name="user_name"
                           aria-describedby="nameHelp" placeholder="Enter name" required>
                    <div class="valid-feedback">
                        Looks good!
                    </div>
                </div>
                <div class="form-group">
                    <label for="user_email">Email address</label>
                    <input type="email" class="form-control" id="user_email" name="user_email"
                           aria-describedby="emailHelp" placeholder="Enter email" required>
                    <div class="invalid-feedback">
                        Incorrect email address
                    </div>
                </div>
                <div class="form-group">
                    <label for="user_pass">Password</label>
                    <input type="password" class="form-control" id="user_pass" name="user_pass"
                           placeholder="Password" required>
                </div>
                <div class="form-group">
                    <label for="conf_user_pass">Confirm password</label>
                    <input type="password" class="form-control" id="conf_user_pass" name="conf_user_pass"
                           placeholder="Confirm password" required>
                </div>
                <button type="submit" class="btn btn-primary">Create</button>
                <a class="create-acc" href="/login">Login</a>
            </form>
            <c:if test="${!status.isEmpty()}">
                <c:if test="${status == 'true'}">
                    <p class="text-success">User created successfully</p>
                </c:if>
                <c:if test="${status == 'false'}">
                    <p class="text-danger">Cant create user</p>
                </c:if>
            </c:if>
        </div>
    </div>
</div>
<script>
    $(function() {

        var field = new Array("user_name", "user_email", "user_pass", "conf_user_pass");

        $("form").submit(function() {
            var error = 0;
            $("form").find(":input").each(function() {

                for (var i = 0; i < field.length; i++) {
                    if ($(this).attr("name") === field[i]) {
                        if (!$(this).val()) {
                            $(this).addClass("is-invalid");
                            error = 1;
                        } else {
                            $(this).removeClass("is-invalid").addClass("is-valid");
                        }
                    }
                }
            });
            var email = $("#user_email");
            if (!isValidEmailAddress(email.val())) {
                error = 2;
                email.removeClass("is-valid").addClass("is-invalid");
            } else {
                email.removeClass("is-invalid").addClass("is-valid");
            }

            function isValidEmailAddress(emailAddress) {
                var pattern = new RegExp(
                    /^(("[\w-\s]+")|([\w-]+(?:\.[\w-]+)*)|("[\w-\s]+")([\w-]+(?:\.[\w-]+)*))(@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$)|(@\[?((25[0-5]\.|2[0-4][0-9]\.|1[0-9]{2}\.|[0-9]{1,2}\.))((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\.){2}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\]?$)/i
                );
                return pattern.test(emailAddress);
            };


            var user_pass = $("user_pass");
            var conf_user_pass = $("conf_user_pass");

            if (user_pass.val() != conf_user_pass.val()) {
                error = 3;
                conf_user_pass.removeClass("is-valid").addClass("is-invalid");
            } else {
                conf_user_pass.removeClass("is-invalid").addClass("is-valid");
            }
            if (error == 0) {
                return true;
            } else {
                return false;
            }
        })
    });
</script>
</body>
</html>