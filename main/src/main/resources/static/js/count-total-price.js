$(document).ready(function(){
    calculateTotalPrice();

    $("#count").change( function(){
        calculateTotalPrice();
    });
});

function calculateTotalPrice(){
    var count = $("#count").val();
    var price = $("#price").val();
    var totalPrice = price*count;
    $("#total-price").html(totalPrice + '원');
}
