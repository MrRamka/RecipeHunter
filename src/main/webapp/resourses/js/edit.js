$(document).ready(function () {
    let edit_button = document.getElementById("edit_button");
    edit_button.onclick = function () {
        alert();
        $.ajax({
            type: "POST",
            url: "editpass",
            data: {
                user_password: document.getElementById("user_password").value,
                user_new_password: document.getElementById("user_new_password").value
            },
            success:
                function(msg) {
                    alert("Success");
                    document.getElementById("answer").textContent = msg;
                },
            error:
                function(msg) {
                    alert("Error");
                    document.getElementById("answer").textContent = msg;
                }


        })
    };
});