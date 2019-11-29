<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="/resourses/css/main.css">
    <title>Error Page</title>
</head>
<body>
<jsp:include page="includes/header.jsp"/>
<div class="container">
    <h2 class="text-danger">Error!!!</h2>
    <c:if test="${error != null}">
        <h4 class="text-danger">${error.getMessage()}</h4>
    </c:if>
    <h2><a href="/home">Home Page</a></h2>
</div>
</body>
</html>