<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<table class="table table-hover table-sm">
    <thead>
    <tr>
        <th>
            <h4>Created recipes</h4>
        </th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="recipe" items="${recipes}">
        <tr>
            <td>
                <a href="/recipe/${recipe.getId()}"><h5>${recipe.getTitle()}</h5></a>
                <p>${recipe.getTime()}</p>
                <p>${recipe.getCategoryId()}</p>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<c:if test="${page_amount > 1}">
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
                    url: "created",
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
                    url: "created",
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
    });
</script>

