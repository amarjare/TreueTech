function selectSpot(spotNumber) {
    const spot = document.querySelector('.parking-grid').children[spotNumber - 1];

    // Check if the spot is available before reserving it
    if (spot.classList.contains('available')) {
        spot.classList.remove('available');
        spot.classList.add('selected');
        const confirmation = confirm('Spot ' + spotNumber + ' selected. Proceed to reservation?');
        if (confirmation) {
            // Redirect to login.html
            window.location.href = 'login.html';
        }
    } else {
        alert('Spot ' + spotNumber + ' is not available.');
    }
    
    if (divElement.style.backgroundColor === 'green') {
                divElement.style.backgroundColor = 'red';
            } else {
                divElement.style.backgroundColor = 'green';
            }
}
