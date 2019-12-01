$(document).ready(function () {
    $("#edit_pass").click(function () {
            $.ajax({
                url: "editpass",
                success: function (data) {
                    $("#content").html(data);
                }
            });
        }
    );

    $("#create").click(function () {
            $.ajax({
                url: "create",
                success: function (data) {
                    $("#content").html(data);
                }
            });
        }
    );

    $("#saved").click(function () {
            $.ajax({
                url: "saved",
                data: {
                    page: 1
                },
                success: function (data) {
                    $("#content").html(data);
                }
            });
        }
    );

    $("#created").click(function () {
            $.ajax({
                url: "created",
                data: {
                    page: 1
                },
                success: function (data) {
                    $("#content").html(data);
                },
                error: function (data) {
                    $("#content").html(data);
                }
            });
        }
    );


});