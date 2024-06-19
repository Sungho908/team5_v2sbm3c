function fetchSubcategories() {
    var mainCategory = document.getElementById("mainCategory");
    var subCategory = document.getElementById("subCategory");
    var selectedMainCategory = mainCategory.value;

    // 소분류 select 요소 초기화
    subCategory.innerHTML = '<option value="">소분류를 선택해주세요.</option>';

    if (selectedMainCategory) {
        fetch('/admin/shoes/create', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                name: selectedMainCategory  // 수정: 'parentCategoryNo'가 아니라 'name'으로 변경
            })
        })
        .then(response => response.json())
        .then(subcategories => {
            subcategories.forEach(subcategory => {
                var option = document.createElement('option');
                option.value = subcategory.categoryno;
                option.text = subcategory.subname;
                subCategory.appendChild(option);
            });
        })
        .catch(error => console.error('Error:', error));
    }
}
