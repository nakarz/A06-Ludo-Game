var username = "";

function renderLoggedInUser() {
    if (username === "") {
        $("#logged-out").css("display", "initial");
        $("#logged-in").css("display", "none");
    } else {
        $("#logged-out").css("display", "none");
        $("#logged-in").css("display", "initial");
        $("#username").text(username);
    }
}

$(document).ready(function () {
    $.ajax("/api/user")
        .done(function (data) {
            console.log("logged in");
            username = data.username;
        })
        .fail(function () {
            console.log("not logged in");
        })
        .always(function () {
            renderLoggedInUser();
        });
});