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
            var newCategoryRow = document.createElement('div');
            newCategoryRow.classList.add('form-row', 'category-row');

            newCategoryRow.innerHTML = `
                <div class="form-group col-sm-6">
                    <label for="mainCategory${categoryCount}">중분류</label>
                    <select id="mainCategory${categoryCount}" name="mainCategory${categoryCount}" class="form-control">
                        <option value="">중분류를 선택해주세요.</option>
                        
                    </select>
                </div>
                <div class="form-group col-sm-6">
                    <label for="subCategory${categoryCount}">소분류</label>
                    <select id="subCategory${categoryCount}" name="subCategory${categoryCount}" class="form-control">
                        <option value="">소분류를 선택해주세요.</option>
                    </select>
                </div>
            `;

      // 기존 카테고리 컨테이너에 새 행 추가
      categoryContainer.appendChild(newCategoryRow);

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

document.addEventListener('DOMContentLoaded', function() {
  document.getElementById('mainCategory').addEventListener('change', function() {
    var mainCategoryName = this.value;
    if (mainCategoryName) {
      fetch('/admin/shoes/select_subname', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({
          name: mainCategoryName,
        })
      })
        .then(response => {
          if (!response.ok) {
            throw new Error('서버에서 소분류 데이터를 불러오지 못했습니다.');
          }
          return response.json();
        })
        .then(data => {
          var subCategorySelect = document.getElementById('subCategory');
          subCategorySelect.innerHTML = '<option value="">소분류를 선택해주세요.</option>'; // 기본 옵션 설정
          data.subname_list.forEach(subCategory => {
            var option = document.createElement('option');
            option.value = subCategory.categoryno;
            option.textContent = subCategory.subname;
            subCategorySelect.appendChild(option);
          });
        })
        .catch(error => {
          console.error('소분류 데이터를 불러오는 데 실패했습니다.', error);
          alert('소분류 데이터를 불러오는 데 실패했습니다. 다시 시도해주세요.');
        });
    } else {
      var subCategorySelect = document.getElementById('subCategory');
      subCategorySelect.innerHTML = '<option value="">소분류를 선택해주세요.</option>';
    }
  });
});

