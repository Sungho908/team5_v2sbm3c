document.addEventListener("DOMContentLoaded", function() {
  var review_delete = document.querySelectorAll(".review_delete");
  var myno = document.getElementById("memberno").getAttribute("data-mymemberno");
  var shoesno = document.getElementById("shoesno").getAttribute("data-shoesno");
  review_delete.forEach(function(link) {
    link.addEventListener("click", function(event) {
      event.preventDefault();
      var review = link.closest('.review');

      if (confirm("후기를 삭제하시겠습니까?")) {
        var reviewno = review.querySelector(".reviewno").value;

        fetch('/review/delete', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify({
            reviewno: reviewno
          })
        })
          .then(response => response.json())
          .then(data => {
            if (data.success) {
              alert("후기가 삭제되었습니다.");
              var url = new URLSearchParams(window.location.search);
              var categoryno = url.get('categoryno');
              window.location.href = "/shoes/" + shoesno + "?categoryno=" + categoryno;
            } else {
              alert("후기 삭제에 실패하였습니다.");
            }
          })
          .catch(error => console.error('Error:', error));
      }

    });
  });

});