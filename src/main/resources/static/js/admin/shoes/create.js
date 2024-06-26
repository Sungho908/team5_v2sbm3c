document.addEventListener("DOMContentLoaded", function() {
  console.log("DOMContentLoaded event fired");

  var addbutton = document.getElementById('addCategoryButton');
  var categoryContainer = document.getElementById('categoryContainer');
  var categoryCount = 1; // 초기 카운트는 1

  var arraylist = [];
  addbutton.addEventListener('click', function(e) {
    e.preventDefault(); // 폼 제출 방지

    var categoryno = document.getElementById('mainCategory').value;
    var subcategoryno = document.getElementById('subCategory').value;
    
    arraylist.push(parseInt(categoryno,10));
    fetch('/admin/shoes/addcategory', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(arraylist)
    })
      .then(response => response.json())
      .then(response => {
        response.name_list.forEach(category => {
          alert(category.name);
          // 여기서 각 CategoryVO 객체의 속성을 원하는 대로 처리할 수 있습니다.
        });
      })
      .catch(error => console.error('Error:', error));

    if (categoryCount < 4) {
      categoryCount++;
    } else {
      addbutton.style.display = 'none';
    }



  });

  // 동적으로 옵션을 생성하는 함수
  document.getElementById('mainCategory').addEventListener('change', function() {
    var mainCategoryName = this.value;
    if (mainCategoryName) {
      fetch('/admin/shoes/select_subname', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({
          categoryno: parseInt(mainCategoryName, 10)
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
