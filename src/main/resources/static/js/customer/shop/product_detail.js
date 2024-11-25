document.addEventListener('DOMContentLoaded', function() {

    // 数量入力の制御を行う処理
    const input = document.querySelector('.quantity-input');
    const minusBtn = document.querySelector('.minus');
    const plusBtn = document.querySelector('.plus');

    if (!(input && minusBtn && plusBtn)) {
        return;
    }

    minusBtn.addEventListener('click', function() {
        if (input.value > 1) {
            input.value = parseInt(input.value) - 1;
        }
    });

    plusBtn.addEventListener('click', function () {
        const currentValue = parseInt(input.value);
        input.value = currentValue + 1;
    });
});