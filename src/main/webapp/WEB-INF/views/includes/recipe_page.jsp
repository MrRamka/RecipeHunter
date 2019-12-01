<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="container">
    <h4 id="content"></h4>
    <br>
    <h2 class="text-primary">${recipe.getTitle()}</h2>
    <h5>Category: ${recipe.getCategoryId()}</h5>
    <h5>Time: ${recipe.getTime()}</h5>
    <table class="table table-hover table-sm">
        <thead>
        <tr>
            <th scope="col">Name</th>
            <th scope="col">Amount</th>
            <th scope="col">Amount Unit</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="ingredient" items="${recipe.getIngredientList()}">
            <tr>
                <td>${ingredient.getName()}</td>
                <td>${ingredient.getAmount()}</td>
                <td>${ingredient.getAmount_unit()}</td>
            </tr>
        </c:forEach>

        </tbody>
    </table>
    <p>${recipe.getSteps()}</p>
    <button id="save" class="btn btn-success m-2 float-right">Save</button>
</div>
<script>
    $("#save").click(function () {
            $.ajax({
                url: "save_recipe",
                type: "POST",
                data: {
                    recipe_id: ${recipe.getId()}
                },
                success: function (data) {
                    $("#content").html(data);
                }
            })
            ;
        }
    );
</script>