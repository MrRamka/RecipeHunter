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
    // Example starter JavaScript for disabling form submissions if there are invalid fields
    (function () {
        'use strict';
        window.addEventListener('load', function () {
            // Fetch all the forms we want to apply custom Bootstrap validation styles to
            var forms = document.getElementsByClassName('needs-validation');
            // Loop over them and prevent submission
            var validation = Array.prototype.filter.call(forms, function (form) {
                form.addEventListener('submit', function (event) {
                    if (form.checkValidity() === false) {
                        event.preventDefault();
                        event.stopPropagation();
                    }
                    form.classList.add('was-validated');
                }, false);
            });
        }, false);
    })();
</script>
</body>
</html>