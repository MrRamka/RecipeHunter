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
    <c:if test="${page_amount != null}">
        <c:if test="${current_page == 1}">
            <a href="/recipes?page=${current_page+1}">Next</a>
        </c:if>
        <c:if test="${current_page != 1 && current_page != page_amount}">

            <a href="/recipes?page=${current_page-1}">Prev</a>
            <a href="/recipes?page=${current_page+1}">Next</a>
        </c:if>
        <c:if test="${current_page == page_amount}">
            <a href="/recipes?page=${current_page-1}">Prev</a>
        </c:if>
    </c:if>
</div>

</body>
</html>
