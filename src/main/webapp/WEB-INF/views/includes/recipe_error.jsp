<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="container">
    <c:if test="${isSavedAgain == null}">
        <h2 class="text-danger">Cant find Recipe</h2>

    </c:if>
    <c:if test="${isSavedAgain == true}">
        <h2 class="text-danger">You already saved this recipe</h2>
    </c:if>
    <h2><a href="/home">Home Page</a></h2>
</div>
