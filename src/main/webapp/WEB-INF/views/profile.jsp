<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css" href="/resourses/css/main.css">
    <title>Profile Page</title>
</head>
<body>
<jsp:include page="includes/header.jsp"/>
<div class="container">
    <c:if test="${login == 'true'}">
        <%--<h2 class="text-primary">Hello, ${current_user.getName()}</h2>--%>

        <div class="row mt-4">

            <div class="col">
                <h3><button id="edit_pass" class=" btn btn-outline-primary btn-lg">Edit password</button></h3>
            </div>

            <div class="col">
                <h3><button id="create" class=" btn btn-outline-success btn-lg">Create recipe</button></h3>
            </div>

            <div class="col">
                <h3><button id="created" class=" btn btn-outline-warning btn-lg">Your recipes</button></h3>
            </div>

            <div class="col">
                <h3><button id="saved" class=" btn btn-outline-dark btn-lg">Saved recipes</button></h3>
            </div>

            <c:if test="${current_user.getRole() == 'Admin'}">
                <div class="col">
                    <h3><a href="/admin" class="btn btn-outline-danger btn-lg">Admin page</a></h3>
                </div>
            </c:if>
        </div>
    </c:if>
</div>


<div class="container mt-3">
    <div class="row justify-content-center">
        <div class="col-md-10" id="content">
            <c:if test="${edit_password_response == true}">
                <jsp:include page="includes/edit_pass.jsp"/>
            </c:if>
            <c:if test="${create == true}">
                <jsp:include page="includes/create.jsp"/>
            </c:if>
        </div>
    </div>
</div>



<script src="/resourses/js/profile.js"></script>

</body>
</html>