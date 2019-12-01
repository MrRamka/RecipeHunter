$(document).ready(function () {
    $("#edit_button").click(function () {
            $.ajax({
                url: "editpass",
                type: "POST",
                data: {
                    user_password: $("#user_password").val(),
                    user_new_password: $("#user_new_password").val()
                },
                success: function (data) {
                    $("#user_password").val("");
                    $("#user_new_password").val("");
                    $("#response").html(data);
                }
            });
            return false;
        }
    )
});