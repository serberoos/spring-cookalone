function cancelOrder(orderId) {
    var url = "/api/product/order/" + orderId + "/cancel";
    var paramData = {
        orderId: orderId,
    };
    var param = JSON.stringify(paramData);
    $.ajax({
        url: url,
        type: "POST",
        contentType: "application/json",
        data: param,
        dataType: "json",
        cache: false,
        success: function (result, status) {
            alert("주문이 취소 되었습니다.");
            location.href = '/product/orders/';
        },
        error: function (jqXHR, status, error) {
            if (jqXHR.status == '401') {
                alert('로그인 후 이용해주세요.');
                location.href = '/auth/login-form';
            } else {
                alert(jqXHR.responseText);
            }
        }
    });
}