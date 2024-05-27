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
