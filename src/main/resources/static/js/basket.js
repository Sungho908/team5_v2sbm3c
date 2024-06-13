// JavaScript 코드
function decreaseQuantity(button) {
  var quantityElement = button.nextElementSibling; // 다음 형제 요소인 span.quantity를 찾습니다.
  var quantity = parseInt(quantityElement.innerText);
  if (quantity > 1) {
    quantityElement.innerText = quantity - 1;
    var basketno = parseInt(button.closest('.basket').querySelector('p:first-child span').innerText.trim());
    update(basketno, quantity - 1); // 수량 감소 후 서버에 업데이트 요청
  }
}

function increaseQuantity(button) {
  var quantityElement = button.previousElementSibling; // 이전 형제 요소인 span.quantity를 찾습니다.
  var quantity = parseInt(quantityElement.innerText);
  quantityElement.innerText = quantity + 1;
  var basketno = parseInt(button.closest('.basket').querySelector('p:first-child span').innerText.trim());
  update(basketno, quantity + 1); // 수량 증가 후 서버에 업데이트 요청
}

// 서버에 POST 요청을 보내어 amount 값을 업데이트하는 함수
function update(basketno, amount) {
  fetch('/basket/update', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify({
      memberno: 1,
      basketno: basketno,
      amount: amount
    })
  })
    .then(response => response.json())
    .then(response => {
      if (response.success) {
        alert('수량을 변경했습니다.');
      } else {
        alert('수량 변경에 실패했습니다.');
        console.error('수량 변경에 실패했습니다.');
      }
    })
    .catch(error => console.error('Error:', error));
}

document.addEventListener('DOMContentLoaded', function() {
  var cartButton = document.querySelector('.detail_btnl');

    cartButton.addEventListener('click', function() {
      var size = document.getElementById('sizes').value; // 수정: 'sizes'로 변경
      var color = document.getElementById('color').value;

      if (size === '' || color === '') {
        alert('사이즈와 색상을 선택해주세요.');
        return;
      }

      fetch('/basket/create', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({
          memberno: 1,
          sizes: size, // 수정: 'sizes'로 변경
          color: color
        })
      })
        .then(response => response.json())
        .then(response => {
          if (response.success) {
            alert('장바구니에 담았습니다.');
          } else {
            alert('장바구니에 제품을 추가하는데 실패했습니다.');
          }
        })
        .catch(error => console.error('Error:', error));
    });
  });
  
document.addEventListener('DOMContentLoaded', function() {
  var cancelButtons = document.querySelectorAll('.basket_btn');

  cancelButtons.forEach(function(button) {
    button.addEventListener('click', function() {
      var basketElement = button.closest('.basket');
      var basketno = basketElement.querySelector('p:first-child span').innerText;

      cancelBasket(basketno); // 장바구니 상품 삭제 요청

      // 화면에서 해당 장바구니 아이템 제거 (선택적으로 추가할 수 있음)
      basketElement.remove();
    });
  });

  function cancelBasket(basketno) {
    fetch('/basket/delete', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({
        memberno: 1, // 멤버 번호 설정 (session에서 가져오거나 하드코딩 가능)
        basketno: basketno // 삭제할 바스켓 번호 전달
      })
    })
      .then(response => response.json())
      .then(response => {
        if (response.success) {
          alert('장바구니에서 제품을 삭제했습니다.');
          console.log('장바구니에서 제품을 성공적으로 삭제했습니다.');
        } else {
          console.error('장바구니 제품 삭제에 실패했습니다.');
        }
      })
      .catch(error => console.error('Error:', error));
  }

 
});