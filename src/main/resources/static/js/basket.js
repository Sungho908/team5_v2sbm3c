// 수량 감소 함수
function decreaseQuantity() {
  var quantity = parseInt(document.querySelector('.quantity').innerText);
  var basketno = parseInt(document.querySelector('.basketno').value);
  var price = parseInt(document.querySelector('.price').value);
  if (quantity > 1) {
    update(basketno, quantity - 1, price);
  }
}

// 수량 증가 함수
function increaseQuantity() {
  var quantity = parseInt(document.querySelector('.quantity').innerText);
  var basketno = parseInt(document.querySelector('.basketno').value);
  var price = parseInt(document.querySelector('.price').value);
  update(basketno, quantity + 1, price);
}


// 서버에 POST 요청을 보내어 amount 값을 업데이트하는 함수
function update(basketno, amount, price) {
  fetch('/basket/update', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify({
      basketno: parseInt(basketno),
      amount: parseInt(amount)
    })
  })
    .then(response => response.json())
    .then(response => {
      if (response.success) {
        alert('수량을 변경했습니다.');
        document.querySelector('.quantity').innerText = amount;
        document.querySelector('.total').innerText = price * amount;
      } else {
        alert('수량 변경에 실패했습니다.');
        console.error('수량 변경에 실패했습니다.');
      }
    })
    .catch(error => console.error('Error:', error));
}

document.addEventListener('DOMContentLoaded', function() {
  // 장바구니 담기 버튼 클릭 시
  var cartButton = document.querySelector('.detail_btnl');

  cartButton.addEventListener('click', function() {
    var size = document.getElementById('sizes').value;
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
        sizes: size,
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

  // 장바구니 취소 버튼 클릭 시
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

  // 장바구니 상품 삭제 요청 함수
  function cancelBasket(basketno) {
    fetch('/basket/delete', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({
        memberno: 1,
        basketno: basketno
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

