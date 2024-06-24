document.addEventListener("DOMContentLoaded", function() {
  var paymentinquiry = document.querySelectorAll(".paymentinquiry");
  var popup = document.getElementById("paymentpopup");
  var closebtn = document.querySelector(".payment-close-btn");

  paymentinquiry.forEach(function(inquiry) {
    inquiry.addEventListener("click", function(event) {
      event.preventDefault(); // 기본 링크 동작 방지

      var shoestitle = inquiry.closest("tbody").querySelector(".shoestitle"); // 상품명 요소
      var payment_details_no = inquiry.closest("tbody").querySelector(".payment_details_no"); // 상품 번호 요소

      popup.style.display = "block"; // 팝업 표시
    });
  });

  closebtn.addEventListener("click", function() {
    popup.style.display = "none";
  });
});