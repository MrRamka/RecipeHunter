<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form class="form" id="form" method="post" action="/editpass">
    <div class="form-group">
        <label for="user_password">Enter Your Password</label>
        <input type="password" class="form-control" id="user_password" name="user_password"
               placeholder="Password" required>
        <label for="user_new_password">Enter Your New Password</label>
        <input type="password" class="form-control" id="user_new_password" name="user_new_password"
               placeholder="New Password" required>
    </div>
    <button type="submit" id="edit_button" class="btn btn-success float-right">Edit</button>
</form>
<h3 id="response"></h3>
<c:if test="${status == true}">
    <p class="text-success">Password updated successfully</p>
</c:if>
<c:if test="${status == false}">
    <p class="text-danger">Old password is incorrect</p>
</c:if>
<script src="/resourses/js/edit.js"></script>
