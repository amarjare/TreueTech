// Get the reference to the slideshow div
const slideshow = document.getElementById('slideshow');

// Get all the images inside the slideshow div
const images = slideshow.getElementsByTagName('img');

// Initialize a variable to keep track of the current image index
let currentImageIndex = 0;

// Function to show the next image
function showNextImage() {
    images[currentImageIndex].style.display = 'none';
    currentImageIndex = (currentImageIndex + 1) % images.length;
    images[currentImageIndex].style.display = 'block';
}

// Function to start the slideshow and set the interval to change images every 5 seconds
function startSlideshow() {
    setInterval(showNextImage, 5000); // 5000 milliseconds (5 seconds)
}

// Call the startSlideshow function when the page loads
window.onload = startSlideshow;


const steps = [
    "Step 1: Browse available parking options: Start by exploring our list of parking facilities. You can check real-time availability, pricing, and location details for each parking spot.",
    "Step 2: Select your desired parking spot: Once you find the perfect spot, simply select it from the available options. You can even filter spots based on your preferences, like covered parking or proximity to entrances.",
    "Step 3: Sign up or log in to your account: To make a reservation, you'll need to create an account or log in if you already have one. Don't worry, this will ensure a seamless booking experience and easy access to your reservations.",
    "Step 4: Make a reservation for the selected spot: Specify the date, time, and duration of your parking reservation. Our system will calculate the total cost and offer secure payment options.",
    "Step 5: Complete the payment: Review your reservation details and proceed with the payment. We support various secure payment methods to make the process hassle-free.",
    "Step 6: Receive confirmation: After a successful payment, you'll receive a confirmation of your reservation via email or SMS. This confirmation will include essential details and a unique booking code.",
    "Step 7: Check-in at the parking facility: When you arrive, simply check-in at the parking facility using your booking code or a QR code provided in the confirmation email. If needed, our friendly parking attendants will be there to assist you.",
    "Step 8: Park your vehicle: Once you check-in, you can park your vehicle in the reserved spot and enjoy a stress-free parking experience.",

];

const howItWorksText = document.getElementById("how-it-works-text");
let currentStepIndex = 0;

function showNextStep() {
    howItWorksText.textContent = steps[currentStepIndex];
    currentStepIndex = (currentStepIndex + 1) % steps.length;
}

// Show the first step initially
showNextStep();

// Change the step every 20 seconds
setInterval(showNextStep, 10000); // 20000 milliseconds (20 seconds)
