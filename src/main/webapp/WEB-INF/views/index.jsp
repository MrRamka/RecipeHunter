<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="/resourses/css/main.css">
    <title>Main Page</title>
</head>
<body>
<jsp:include page="includes/header.jsp"/>

<form class="form" method="POST" action="/recipes">
    <div class="container container-select">
        <jsp:include page="includes/meat_container.jsp"/>
        <jsp:include page="includes/fruits_container.jsp"/>
        <jsp:include page="includes/vegetables_container.jsp"/>
        <jsp:include page="includes/daily_container.jsp"/>

        <button type="submit" class="btn btn-success mt-2 float-right">Find</button>
    </div>
</form>

</body>
</html>