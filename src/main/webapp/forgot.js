function closePopup() {
    document.getElementById("popup").style.display = "none";
}

document.getElementById("forgotPasswordForm").addEventListener("submit", function (event) {
    event.preventDefault();
    const email = document.getElementById("email").value;
    // You can implement your logic here to send the reset link to the email address.
    // For this example, we will just show the popup.
    document.getElementById("popup").style.display = "block";
});
