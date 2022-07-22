$(document).ready(function(){
    calculateTotalPrice();

    $("#orderCount").change( function(){
        calculateTotalPrice();
    });
});

function calculateTotalPrice(){
    var count = $("#orderCount").val();
    var price = $("#price").val();
    var totalPrice = price*count;
    $("#total-price").html(totalPrice + '원');
}

function order() {
    /* csrf disable */
    // var token = $("meta[name='_csrf']").attr("content");
    // var header = $("meta[name='_csrf_header']").attr("content");

    var url = "/api/order";
    var paramData = {
        productId: $("#productId").val(),
        orderCount: $("#orderCount").val()
    };

    var param = JSON.stringify(paramData);

    $.ajax({
        url: url,
        type: "POST",
        contentType: "application/json",
        data: param,
        // beforeSend: function (xhr) {
        //     /* 데이터를 전송하기 전에 헤더에 csrf 값을 설정 : disable */
        //     xhr.setRequestHeader(header, token);
        // },
        dataType: "json",
        cache: false,
        success: function (result, status) {
            alert("주문이 완료 되었습니다.");
            location.href = '/';
        },
        error: function (jqXHR, status, error) {
            if (jqXHR.status == '401') {
                alert('로그인 후 이용해주세요');
                location.href = '/auth/login-form';
            } else {
                alert(jqXHR.responseText);
            }
        }
    });
}