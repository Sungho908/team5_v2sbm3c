document.addEventListener("DOMContentLoaded", function() {
  var moreButton = document.getElementById("moreButton");
  var moreMenu = document.querySelector(".moreMenu");

  moreButton.addEventListener("click", function() {
    if (moreMenu.style.display === "none") {
      moreMenu.style.display = "block";
      moreButton.textContent = "접기";
    } else {
      moreMenu.style.display = "none";
      moreButton.textContent = "더보기+";
    }
     contentBodyBottom.scrollIntoView({ behavior: 'smooth', block: 'end' });
  });
});
