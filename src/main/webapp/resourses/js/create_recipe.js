$(document).ready(function () {
    $("#create_button").click(function () {
        alert("Button clicked");
        let submit_data = $("#myForm");
            $.ajax({
                url: "create",
                type: "POST",
                data: submit_data.serialize(),
                success: function (data) {
                    submit_data[0].reset();
                    $("#response").html(data);
                }
            });
        return false;
        }
    )
});