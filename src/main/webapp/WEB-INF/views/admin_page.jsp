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
    <c:if test="${users_amount != null && recipes_amount != null}">
        <h3>User amount: <span class="text-primary">${users_amount}</span></h3>
        <h3>Recipe amount: <span class="text-primary">${recipes_amount}</span></h3>
    </c:if>


</div>
</body>
</html>