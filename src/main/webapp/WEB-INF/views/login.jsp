<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <meta charset='UTF-8'>
    <title>Login page</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="/resourses/css/style.css" type="text/css">
</head>
<body>
<jsp:include page="includes/header.jsp"/>

<div class="container components">
    <div class="row">
        <div class="col-lg-6 col-md-12 col-sm-12">
            <img src="/resourses/img/pizza_2.png" alt="Pizza Image" style="width: 100%">
        </div>
        <div class="col-lg-6 col-md-12 col-sm-12">
            <form class="form" method="POST" action="/login">
                <div class="form-group">
                    <label for="user_email">Email address</label>
                    <input type="email" class="form-control" id="user_email" name="user_email"
                           aria-describedby="emailHelp" placeholder="Enter email" required>
                </div>
                <div class="form-group">
                    <label for="user_password">Password</label>
                    <input type="password" class="form-control" id="user_password" name="user_password"
                           placeholder="Password" required>
                </div>
                <div class="form-group">
                    <div class="custom-control custom-checkbox my-1">
                        <input type="checkbox" class="custom-control-input" id="remember_me" name="remember_me">
                        <label class="custom-control-label" for="remember_me">Remember me</label>
                    </div>
                </div>

                <button type="submit" class="btn btn-success">Submit</button>
                <a class="create-acc" href="/registration">Create an account</a>
            </form>
            <a href="http://oauth.vk.com/authorize?client_id=7229885&redirect_uri=http://localhost:8080/vklogin&response_type=code&scope=email&v=5.103"
               title="Enter with vk">Enter with vk</a>
                <c:if test="${status == false}">
                    <p class="text-danger">Incorrect email or password.</p>
                </c:if>

        </div>
    </div>
</div>

</body>
</html>
