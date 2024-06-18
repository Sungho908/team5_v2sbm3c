document.addEventListener("DOMContentLoaded", function() {
  var inquiry = document.querySelector(".inquiry");
  var popup = document.getElementById("popup");
  var closebtn = document.querySelector(".close-btn");
  var createInquiry = document.getElementById("createInquiry");
  var inquiryTitle = document.getElementById("inquiryTitle");
  var inquiryContents = document.getElementById("inquiryContents");
  var selectType = document.getElementById("inquiryType");
  var shoesSelect = document.getElementById('shoes_select');
  var paymentSelect = document.getElementById('payment_select');
  var shoesText = document.getElementById('shoes_text');
  var paymentText = document.getElementById('payment_text');
  var selectShoes = document.getElementById('selectShoes');
  var selectPayment = document.getElementById('selectPayment');
  inquiry.addEventListener("click", function(event) {
    event.preventDefault();
    popup.style.display = "block";
  });

  closebtn.addEventListener("click", function() {
    popup.style.display = "none";
  });

  selectType.addEventListener('change', function() {
    if (this.value === 'shoes') {
      shoesSelect.style.display = 'block';
      paymentSelect.style.display = 'none';
    } else if (this.value === 'payment') {
      paymentSelect.style.display = 'block';
      shoesSelect.style.display = 'none';
    } else {
      shoesSelect.style.display = 'none ';
      paymentSelect.style.display = 'none';
    }
  });

  // 검색 결과 창 변수
  var search_popup = document.getElementById("search-popup");
  var search_closebtn = document.querySelector(".search-close-btn");
  selectShoes.addEventListener("click", function(event) {
    event.preventDefault();
    if (shoesText.value.trim() === '') {
      alert('문의하고 싶은 신발을 입력해주세요.');
    } else {
      fetch('/inquiry/select', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({
          select: selectType.value,
          word: shoesText.value
        })
      })
        .then(response => response.json())
        .then(data => {
          if (data.success) {
            search_popup.style.display = "block";
          } else {
            alert('검색 결과가 없습니다.');
          }
        })
        .catch(error => console.error('Error:', error));
    }

  });

  search_closebtn.addEventListener("click", function() {
    search_popup.style.display = "none";
  });

  createInquiry.addEventListener("click", function(event) {
    event.preventDefault();
    var typeno = selectType.options[selectType.selectedIndex].value;
    alert(typeno);
    if (typeno.trim() === '' || inquiryTitle.value.trim() === '' || inquiryContents.value.trim() === '') {
      alert("문의 유형, 제목, 내용을 입력해주세요.");
      return;
    }

  });

});