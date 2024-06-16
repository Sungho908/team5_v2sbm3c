document.addEventListener("DOMContentLoaded", function() {
  var likes = document.querySelectorAll(".likes");
  var hates = document.querySelectorAll(".hates");

  likes.forEach(function(like) {
    like.addEventListener('click', function(event) {
      event.preventDefault();

      var review = like.closest('.review');
      var reviewno = review.querySelector(".reviewno").value;
      var likes_count = review.querySelector('.likes_count');

      likes_count.classList.add('liked');
    });
  });

  hates.forEach(function(hate) {
    hate.addEventListener('click', function(event) {
      event.preventDefault();

      var review = hate.closest('.review');
      var reviewno = review.querySelector(".reviewno").value;

    });
  });
});