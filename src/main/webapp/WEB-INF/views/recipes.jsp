<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="/resourses/css/main.css">
    <title>Recipes</title>
</head>
<body>
<jsp:include page="includes/header.jsp"/>

<div class="container">
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
</div>

</body>
</html>
