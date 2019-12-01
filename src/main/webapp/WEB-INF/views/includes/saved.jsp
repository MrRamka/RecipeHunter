<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<table class="table table-hover table-sm">
    <thead>
    <tr>
        <th>
            <h4>Saved recipes</h4>
        </th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="recipe" items="${recipes}">
        <tr id="row_${recipe.getId()}">
            <td>
                <a href="/recipe/${recipe.getId()}"><h5>${recipe.getTitle()}</h5></a>
                <p>${recipe.getTime()}</p>
                <p>${recipe.getCategoryId()}</p>
            </td>
            <td>
                <button id="rm" class="btn btn-outline-danger mb-4 btn-sm float-right">Remove</button>
                <input hidden type="number" id="rm_id" value="${recipe.getId()}">
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<h3 id="response"></h3>
<c:if test="${page_amount >  1}">
    <c:if test="${current_page == 1}">
        <button class="next btn btn-outline-success mb-4">Next</button>
    </c:if>
    <c:if test="${current_page != 1 && current_page != page_amount}">

        <button class="prev btn btn-outline-success mb-4">Prev</button>
        <button class="next btn btn-outline-success mb-4">Next</button>
    </c:if>
    <c:if test="${current_page == page_amount}">
        <button class="prev btn btn-outline-success mb-4">Prev</button>
    </c:if>
</c:if>
<script>
    $(document).ready(function () {
        $(".next").click(function () {
                $.ajax({
                    url: "saved",
                    data: {
                        page: ${current_page + 1}
                    },
                    success: function (data) {
                        $("#content").html(data);
                    }
                })
                ;
            }
        );
        $(".prev").click(function () {
                $.ajax({
                    url: "saved",
                    data: {
                        page: ${current_page - 1}
                    },
                    success: function (data) {
                        $("#content").html(data);
                    }
                })
                ;
            }
        );
        $("#rm").click(function () {
                $.ajax({
                    url: "remove",
                    data: {
                        recipe_id: $("#rm_id").val()
                    },
                    success: function () {
                        let row_number = "#row_" + $("#rm_id").val();
                        $(row_number).remove();
                        $.ajax({
                            url: "saved",
                            data: {
                                page: ${current_page}
                            },
                            success: function (data) {
                                $("#content").html(data);
                            }
                        });
                    }
                })
                ;
            }
        );
    });
</script>