document.addEventListener('DOMContentLoaded', function() {
    const modal = document.getElementById('update-modal');
    modal.addEventListener('click', function(event) {
        if (event.target === modal) {
            modal.style.display = 'none';
        }
    });

    const cartItems = document.querySelectorAll('.cart-item');

    for (let cartItem of cartItems) {
        const minusButton = cartItem.querySelector('.minus');
        const input = cartItem.querySelector('.quantity-input');

        minusButton.addEventListener('click', function () {
            if (input.value > 1) {
                input.value = parseInt(input.value) - 1;
            }
        });

        const plusButton = cartItem.querySelector('.plus');
        plusButton.addEventListener('click', function () {
            const currentValue = parseInt(input.value);
            input.value = parseInt(currentValue) + 1;
        });
    }
});