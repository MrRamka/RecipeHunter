<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<header>
    <div class="d-flex flex-column flex-md-row align-items-center p-2 px-md-3 mb-2 bg-white border-bottom shadow-sm">
        <img src="/resourses/img/pizza_2.png" alt="Logo Image"/>
        <h2 class="my-0 mr-md-auto font-weight-normal">Recipe Hunter</h2>
        <nav class="my-2 my-md-0 mr-md-3">
            <a class="p-2 text-dark" href="/home">Home</a>
            <a class="p-2 text-dark" href="#">About</a>

                <c:if test="${login == 'true'}">
                    <a class = "text-primary">Hello, ${current_user.getName()}</a>
                    <a class="p-2 text-dark" href="/logout">Log out</a>
                </c:if>
        </nav>

        <c:if test="${login == 'false' || login == null}">
            <a class="btn btn-outline-success m-1" href="/login">Sign in</a>
            <a class="btn btn-outline-primary" href="/registration">Sign up</a>
        </c:if>
    </div>
</header>

