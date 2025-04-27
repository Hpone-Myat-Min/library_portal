document.getElementById("updatePinForm").addEventListener("submit", async (e) => {
    e.preventDefault();

    const studentId = localStorage.getItem("studentId");
    const oldPin = document.getElementById("oldPin").value.trim();
    const newPin = document.getElementById("newPin").value.trim();
    const confirmPin = document.getElementById("confirmPin").value.trim();

    if (!studentId || !oldPin || !newPin || !confirmPin) {
        document.getElementById("errorMsg").textContent = "Please fill in all fields.";
        return;
    }

    if (newPin !== confirmPin) {
        document.getElementById("errorMsg").textContent = "New PIN and Confirm PIN do not match.";
        return;
    }

    const updatedAccount = {
        studentId: "admin",
        pin: document.getElementById("newPin").value.trim(),

    };

    try {
        const res = await fetch(`http://localhost:8082/libraryAccount/${studentId}`, {
            method: "PUT",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(updatedAccount)
        });

        if (!res.ok) {
            throw new Error("Failed to update PIN. Please check your old PIN.");
        }

        alert("PIN updated successfully!");
        window.location.href = "login.html";

    } catch (error) {
        document.getElementById("errorMsg").textContent = error.message;
    }
});
