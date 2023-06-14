$(document).ready(function () {
    $('.todoCheckbox').on('change', function () {
        console.log("ajax 테스트!!!!!!!!!!!!!!!!!!!!!!ㅌ")
        var checkbox = $(this);
        var id = checkbox.data('id');
        var done = checkbox.is(':checked');
        console.log("id: " + id);
        console.log("done: " + done);
        // ajax를 통해 서버로 업데이트 요청
        updateTodoStatus(id, done);
    });
});

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