$(document).ready(function () {
    $('.todoCheckbox').on('change', function () {
        console.log("ajax 테스트!!!!!!!!!!!!!!!!!!!!!!ㅌ");
        var checkbox = $(this);
        var id = checkbox.data('id');
        var done = checkbox.is(':checked');
        console.log("id: " + id);
        console.log("done: " + done);
        // ajax를 통해 서버로 업데이트 요청
        updateTodoStatus(id, done);
    });
    $('.sentFriendRequestAccept').click(function () {
            console.log("ajax 테스트!!!!!!!!!!!!!!!!!!!!!!ㅌ");
            var request = $(this);
            var requestId = request.data('requestId');
            var status = "accept";
            console.log("Accept -> requestId: " + requestId);

            // ajax를 통해 서버로 업데이트 요청
            updateFriendRequest(requestId,status);
        });
    $('.sentFriendRequestReject').click( function () {
        console.log("ajax 테스트!!!!!!!!!!!!!!!!!!!!!!ㅌ");
        var request = $(this);
        var requestId = request.data('requestId');
        var status = "reject";
        console.log("Reject -> requestId: " + requestId);

        // ajax를 통해 서버로 업데이트 요청
        updateFriendRequest(requestId,status);
    });
});

function updateFriendRequest(requestId, requestStatus) {
    $.ajax({
        url: '/api/v1/friend/request/' + requestId,
        type: 'PATCH',
        contentType: 'application/json',
        data: JSON.stringify({"requestStatus": requestStatus}),
        success: function () {
            console.log('status 업데이트 성공');
        },
        error: function () {
            console.log('status 업데이트 실패');
        },
        }

    );
}
//서버로 업데이트 요청 전송
function updateTodoStatus(id, done) {
    $.ajax({
        url: '/api/v1/todo/checkUpdate/'+id,
        type: 'PATCH',
        contentType: 'application/json',
        data: JSON.stringify({"id":id, "context":null, "done":done}),
        success: function () {
            console.log('업데이트 성공');
        },
        error: function () {
            console.log('업데이트 실패');
        },
    });
}