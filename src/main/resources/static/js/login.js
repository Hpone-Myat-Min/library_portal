document.getElementById("loginForm").addEventListener("submit", async (e) => {
    e.preventDefault();

    const studentId = document.getElementById("studentId").value.trim();
    const pin = document.getElementById("pin").value.trim();

    if (!studentId || !pin) {
        document.getElementById("errorMsg").textContent = "Please enter both Student ID and PIN.";
        return;
    }

    try {
        let response;
        if (studentId === "admin") {
            // Admin login
            response = await fetch(`http://localhost:8082/libraryAccount/login/admin?username=admin&pin=${encodeURIComponent(pin)}`, {
                method: "POST"
            });
        } else {
            // Student login
            response = await fetch(`http://localhost:8082/libraryAccount/login/student?studentId=${encodeURIComponent(studentId)}&pin=${encodeURIComponent(pin)}`, {
                method: "POST"
            });
        }

        if (!response.ok) {
            throw new Error("Invalid credentials.");
        }

        // Save studentId into localStorage
        localStorage.setItem("studentId", studentId);

        // Redirect based on user type
        if (studentId === "admin") {
            window.location.href = "books.html";
        } else {
            if (pin === "000000") {
                window.location.href = "updatePin.html";
            } else {
                window.location.href = "books.html";
            }
        }

    } catch (error) {
        document.getElementById("errorMsg").textContent = error.message;
    }
});
