document.addEventListener("DOMContentLoaded", function() {
  $.noConflict();
  var zoom = document.querySelectorAll('.zoom');

  zoom.forEach(function(button) {
    button.addEventListener('click', function() {
      var tr = this.closest('tr'); // 현재 클릭된 버튼의 가장 가까운 tr 요소를 찾습니다.
      var noticeno = tr.querySelector('input[id="noticeno"]').value;
      var content = tr.nextElementSibling; // tr 요소의 다음 형제 요소를 가져옵니다.
      $.ajax({
        type: 'POST',
        url: '/notice/increased_views',
        contentType: 'application/json',
        data: JSON.stringify({ noticeno: noticeno, zoom: button.innerText }),
        success: function(response) {
          tr.querySelector('#views').textContent = response.views;
          if (response.status == 'increased') {
            button.innerText = "-";
          } else if (response.status == 'close') {
            button.innerText = "+";
          }
          if (content) {
            content.classList.toggle('contents');
          }
        }
      });
    });
  });

  var frm_search = document.querySelector('form[name="frm_search"]');
  frm_search.addEventListener('submit', function(event) {
    event.preventDefault(); // 기본 동작 중단
    var word = document.getElementById('word').value;
    if (word.trim() === '') {
      alert('검색어를 입력해주세요.');
      return;
    }
    $.ajax({
      type: 'POST',
      url: '/notice/search', // 서버에 요청을 보낼 URL
      contentType: 'application/json',
      data: JSON.stringify({ word: word }),
      success: function(size) {
        if (parseInt(size) == null || parseInt(size) === 0) {
          alert('검색 결과가 없습니다.');
        } else {
          frm_search.submit();
        }
      },
    });
  });

});