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

$(document).ready(function() {
    $('#addCategoryButton').click(function(e) {
        e.preventDefault(); // 폼 제출 방지
        var newCategoryRow = `
        <div class="row justify-content-center category-row">
            <div class="col-sm-4">
                <div class="form-group">
                    <label for="mainCategory">중분류</label>
                    <select id="mainCategory" name="mainCategory" class="form-control form-control-sm">
                        <option value="">중분류를 선택해주세요.</option>
                    </select>
                </div>
            </div>
            <div class="col-sm-4">
                <div class="form-group">
                    <label for="subCategory">소분류</label>
                    <select id="subCategory" name="subCategory" class="form-control form-control-sm">
                        <option value="">소분류를 선택해주세요.</option>
                    </select>
                </div>
            </div>
        </div>`;
        $('#categoryContainer').append(newCategoryRow);
        $('#addCategoryButton').hide(); // 추가 버튼 숨기기
    });
});
