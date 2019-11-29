<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="row justify-content-center">
    <div class="col-md-8">
        <form class="form" method="POST" action="/admin">
            <div class="form-group">
                <label for="user_password">Enter Your Password</label>
                <input type="password" class="form-control" id="user_password" name="user_password"
                       placeholder="Password" required>
            </div>
            <button type="submit" class="btn btn-success float-right">Submit</button>
        </form>
    </div>
</div>
