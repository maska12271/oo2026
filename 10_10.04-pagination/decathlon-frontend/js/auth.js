function handleFakeLogin(event) {
    event.preventDefault();
    alert("Login demo only");
    window.location.href = "index.html";
}

function handleFakeRegister(event) {
    event.preventDefault();
    alert("Register demo only");
    window.location.href = "login.html";
}

document.addEventListener("DOMContentLoaded", () => {
    const loginForm = document.getElementById("login-form");
    const registerForm = document.getElementById("register-form");

    if (loginForm) loginForm.addEventListener("submit", handleFakeLogin);
    if (registerForm) registerForm.addEventListener("submit", handleFakeRegister);
});