function openSearchPopup() {
    var popup = window.open("", "Search Popup", "width=300,height=150");
    popup.document.write(`
        <html>
        <head><title>Search Popup</title></head>
        <body>
            <label for="searchQuery">Enter search query:</label>
            <input type="text" id="searchQuery" />
            <button onclick="search()">Search</button>
            <p id="result"></p>
            <script src="js/search-handler.js"></script>
        </body>
        </html>
    `);
}
function openPopup() {
    document.getElementById('popup').style.display = 'block';
}

function closePopup() {
    document.getElementById('popup').style.display = 'none';
}