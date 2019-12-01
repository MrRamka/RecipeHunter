<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form class="form" id="myForm" role="form">
    <div class="form-group">
        <label for="recipe_name">Enter recipe name:</label>
        <input type="text"  required class="form-control" id="recipe_name" name="recipe_name" value="" placeholder="Enter recipe name">
    </div>

    <div class="form-group">
        <label for="recipe_type">Select recipe type:</label>
        <select class="custom-select" name="recipe_type" id="recipe_type">
            <c:forEach var="r_category" items="${r_categories}">
                <option value="${r_category.getId()}">${r_category.getName()}</option>
            </c:forEach>

        </select>
    </div>

    <div class="form-group">
        <label for="recipe_time">Enter recipe time:</label>
        <input type="text"  required class="form-control"
               name="recipe_time" id="recipe_time" value="" placeholder="Enter recipe time(dd:hh:mm)">
    </div>

    <div class="form-group">
        <label >Ingredients:</label>

        <div class="row my-1" id="row_1">

            <div class="col">
                <input type="text" required class="form-control"
                       name="recipe_ingredient_name_1" value=""
                       placeholder="Enter name">
            </div>

            <div class="col">
                <input type="text" class="form-control"
                       name="recipe_ingredient_amount_1" value=""
                       placeholder="Enter amount(1.5)">
            </div>

            <div class="col">
                <input type="text" required class="form-control"
                       name="recipe_ingredient_amount_type_1" value=""
                       placeholder="Enter amount type(ml)">
            </div>
        </div>

        <div class="row">
            <div class="col">
                <button type="button"
                        class="btn btn-primary float-right m-2"
                        id="add" name="button">Add</button>
            </div>
        </div>
    </div>

    <div class="form-group">
        <label for="recipe_steps">Enter recipe directions:</label>
        <textarea required class="form-control" name="recipe_steps" id="recipe_steps" placeholder="Enter recipe name"> </textarea>
    </div>
    <input hidden type="number" name="ingredient_amount" id="ingredient_amount" value="1">
    <button type="submit" id="create_button" class="btn btn-success float-right m-2 ">Create</button>


</form>
<h3 id="response"></h3>
<c:if test="${status == true}">
    <p class="text-success">Recipe created successfully</p>
</c:if>
<script src="/resourses/js/add.js"></script>
<script src="/resourses/js/create_recipe.js"></script>
