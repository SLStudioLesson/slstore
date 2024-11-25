document.addEventListener('DOMContentLoaded', function() {
    const categorySelector = document.querySelector('.category-selector');

    categorySelector.addEventListener('change', function(e) {
        const selectedOption = e.target.selectedOptions[0];
        const categoryName = selectedOption.value === '0' ? 'すべての' : selectedOption.textContent;
        const input = document.querySelector('.input-product-name');
        input.placeholder = `${categoryName}カテゴリから検索`;
    });

    const brandDropdown = document.querySelector('.brand-dropdown');
    const brandDropbtn = document.querySelector('.brand-dropbtn');

    brandDropbtn.addEventListener('click', function() {
        brandDropdown.classList.toggle('active');
    });

    // ドロップダウン外をクリックしたら閉じる
    document.addEventListener('click', function(event) {
        if (!brandDropdown.contains(event.target)) {
            brandDropdown.classList.remove('active');
        }
    });
});