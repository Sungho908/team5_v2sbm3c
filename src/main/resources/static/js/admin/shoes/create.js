document.addEventListener("DOMContentLoaded", function() {
    console.log("DOMContentLoaded event fired");
    var button = document.getElementById('addCategoryButton');
    var categoryContainer = document.getElementById('categoryContainer');
    var categoryCount = 1; // 초기 카운트는 1

    button.addEventListener('click', function(e) {
        e.preventDefault(); // 폼 제출 방지
        console.log("Add Category button clicked");

        if (categoryCount < 5) {
            categoryCount++;
            console.log("Adding category, count:", categoryCount);
            
            // 새로운 카테고리 폼 요소 추가
            var newCategoryRow = `
                <div class="form-row category-row">
                    <div class="form-group col-sm-6">
                        <label for="mainCategory${categoryCount}">중분류</label>
                        <select id="mainCategory${categoryCount}" name="mainCategory${categoryCount}" class="form-control">
                            <option value="">중분류를 선택해주세요.</option>
                            <!-- 중분류 옵션 추가 -->
                        </select>
                    </div>
                    <div class="form-group col-sm-6">
                        <label for="subCategory${categoryCount}">소분류</label>
                        <select id="subCategory${categoryCount}" name="subCategory${categoryCount}" class="form-control">
                            <option value="">소분류를 선택해주세요.</option>
                            <!-- 소분류 옵션 추가 -->
                        </select>
                    </div>
                </div>`;
            
            // 기존 카테고리 컨테이너에 새 행 추가
            categoryContainer.insertAdjacentHTML('afterend', newCategoryRow);

            // 새로 추가된 mainCategory와 subCategory에 이벤트 리스너 추가
            document.getElementById(`mainCategory${categoryCount}`).addEventListener('change', function() {
                fetchSubcategories(this.id, `subCategory${categoryCount}`);
            });

            if (categoryCount >= 5) {
                button.style.display = 'none';
            }
        }
    });
});

function fetchSubcategories(mainCategoryId, subCategoryId) {
  console.log("Fetching subcategories for", mainCategoryId, subCategoryId);
  var mainCategory = document.getElementById(mainCategoryId);
  var subCategory = document.getElementById(subCategoryId);
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
