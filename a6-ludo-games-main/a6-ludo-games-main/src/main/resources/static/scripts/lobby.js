$(document).ready(function() {

    // get data form
    var $form = $( this )

    var send_to = `/api/lobby`

    $.ajax({
        type: "get",
        url: send_to,
        dataType: 'json',
        beforeSend: function() {
            $("#loading_spinner").css("display", "block");
            $("#table_result tbody").empty()
        },
        success: function(data_returned, textStatus, jqXHR) {
            $("#loading_spinner").css("display", "none");
            $("#submit_button").prop('disabled', false);

            var trHTML = ""
            $.each(data_returned, function (i, item) {
                var lobby_id = item.id
                trHTML += `<tr><td>${i + 1}</td><td>${item.id}</td><td>${item.players.length}</td><td><form th:action="@{/api/lobby/join/lobby_id}" method="post">
                    <button name="foo" value="upvote">Join</button>
                </form></td></tr>`
            });
            $('#table_result').append(trHTML);

        },

        error: function(data_returned) {
            $("#loading_spinner").css("display", "none");
            $("#submit_button").prop("disabled", false);
        }
    });
})