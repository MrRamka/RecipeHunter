<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" type="text/css" href="/resourses/css/main.css">
    <title>Edit password</title>
</head>
<body>
<jsp:include page="includes/header.jsp"/>
<div class="row justify-content-center">
    <div class="col-md-8">
        <form class="form" id="form" method="post">
            <div class="form-group">
                <label for="user_password">Enter Your Password</label>
                <input type="password" class="form-control" id="user_password" name="user_password"
                       placeholder="Password" required>
                <label for="user_new_password">Enter Your New Password</label>
                <input type="password" class="form-control" id="user_new_password" name="user_new_password"
                       placeholder="New Password" required>
            </div>
            <button type="submit" id="edit_button" class="btn btn-success float-right">Edit</button>
        </form>
        <%--<input type="button" id="edit_button" class="btn btn-success float-right" value="EDIT">--%>
        <%--<h3 id="answer"></h3>--%>
        <c:if test="${status == true}">
            <p class="text-success">Password updated successfully</p>
        </c:if>
        <c:if test="${status == false}">
            <p class="text-danger">Old password is incorrect</p>
        </c:if>
    </div>
</div>

<%--<script type="application/javascript" src="/resourses/js/edit.js"></script>--%>
<%--<script>
    $(document).ready(function () {
        // let edit_button = $("#edit_button");
        // edit_button.onclick = function () {
        //     alert(document.getElementById("user_password").value);
        //     $.ajax({
        //         type: "POST",
        //         url: "editpass",
        //         data: {
        //             user_password: document.getElementById("user_password").value,
        //             user_new_password: document.getElementById("user_new_password").value
        //         },
        //         success: function(msg) {
        //             document.getElementById("answer").textContent = msg;
        //         },
        //         error: function(msg) {
        //             document.getElementById("answer").textContent = msg;
        //         },
        //
        //     });
        // }
        $("#form").submit(function (event) {
            event.preventDefault();

            alert('Нажата submit-кнопка');

            let post = $.post("/editpass",
                {
                    user_password: $("#user_password").val(),
                    user_new_password: $("#user_new_password").val()
                }
            );
            alert(post);
            post.success(function (data) {
                alert(data);
            });


            alert("Ajax");
            // $.ajax
            // ({
            //     type: "POST",
            //     url: "editpass",
            //     data: ddata,
            //     // data: {
            //     //     user_password: $("#user_password").val(),
            //     //     user_new_password: $("#user_new_password").val()
            //     // },
            //     success: function(msg) {
            //         alert(1);
            //         document.getElementById("answer").textContent = msg;
            //     },
            //     error: function(msg) {
            //         document.getElementById("answer").textContent = msg;
            //     },
            //
            // });
            return false;
        });

        /*edit_button.onsubmit = function () {
            $.ajax({
                type: "POST",
                url: "editpass",
                data: {
                    user_password: document.getElementById("user_password").value,
                    user_new_password: document.getElementById("user_new_password").value
                },
                success: function(msg) {
                    document.getElementById("answer").textContent = msg;
                },
                error: function(msg) {
                    document.getElementById("answer").textContent = msg;
                },
                complete: function(msg) {
                    document.getElementById("answer").textContent = msg;
                }

            });
            return false;
        };*/
    })
    ;
</script>--%>
</body>
</html>
