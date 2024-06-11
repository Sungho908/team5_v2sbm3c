// JavaScript 코드
function decreaseQuantity() {
  var quantityElement = document.getElementById('product1_quantity');
  var quantity = parseInt(quantityElement.innerText);
  if (quantity > 1) {
    quantityElement.innerText = quantity - 1;
  }
}

function increaseQuantity() {
  var quantityElement = document.getElementById('product1_quantity');
  var quantity = parseInt(quantityElement.innerText);
  quantityElement.innerText = quantity + 1;
}


        function deleteFromBasket(basketno) {
            fetch('/basket/delete', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded',
                    'X-CSRFToken': getCookie('csrftoken')
                },
                body: new URLSearchParams({
                    'basketno': basketno
                })
            })
            .then(response => response.text())
            .then(data => {
                alert(data);
                // 항목 삭제 후 페이지를 새로고침하거나 해당 항목을 DOM에서 제거
                location.reload(); // 또는 특정 항목을 DOM에서 제거하는 로직 작성
            })
            .catch(error => console.error('Error:', error));
        }

        function getCookie(name) {
            let cookieValue = null;
            if (document.cookie && document.cookie !== '') {
                const cookies = document.cookie.split(';');
                for (let i = 0; i < cookies.length; i++) {
                    const cookie = cookies[i].trim();
                    if (cookie.substring(0, name.length + 1) === (name + '=')) {
                        cookieValue = decodeURIComponent(cookie.substring(name.length + 1));
                        break;
                    }
                }
            }
            return cookieValue;
        }
