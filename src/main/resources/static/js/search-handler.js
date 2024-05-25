function search() {
    var query = document.getElementById('searchQuery').value;
    fetch('/search?query=' + query)
        .then(response => response.text())
        .then(data => {
            document.getElementById('result').innerText = data;
        });
}
