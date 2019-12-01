<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="/resourses/css/main.css">
    <title>Profile Page</title>
</head>
<body>
<jsp:include page="includes/header.jsp"/>
<div class="container">
    <c:if test="${login == 'true'}">
        <h2 class="text-primary">Hello, ${current_user.getName()}</h2>
        <h3><a href="/editpass" class=" btn btn-outline-primary">Edit password</a></h3>
        <c:if test="${current_user.getRole() == 'Admin'}">
            <h3><a href="/admin" class="btn btn-outline-danger">Admin page</a></h3>
        </c:if>

        <h3>Saved recipes</h3>
        <br>
        <table class="table table-hover">
            <tbody>
            <c:forEach var="recipe" items="${recipes}">
                <tr>
                    <td>
                        <a href="/recipe/${recipe.getId()}"><h4>${recipe.getTitle()}</h4></a>
                        <p>${recipe.getTime()}</p>
                        <p>${recipe.getCategoryId()}</p>
                    </td>
                </tr>
            </c:forEach>

            </tbody>

        </table>

    </c:if>
</div>
</body>
</html>