document.addEventListener("DOMContentLoaded", () => {
    const studentId = localStorage.getItem("studentId");

    if (!studentId) {
        alert("Please log in first.");
        window.location.href = "login.html";
        return;
    }

    if (studentId === "admin") {
        document.getElementById("adminNav").style.display = "inline";
    } else {
        document.getElementById("studentNav").style.display = "inline";
    }

    document.getElementById("borrowForm").addEventListener("submit", async (e) => {
        e.preventDefault();

        const isbn = document.getElementById("bookIsbn").value.trim();
        if (!isbn) {
            document.getElementById("message").textContent = "Please enter an ISBN.";
            return;
        }

        try {
            const res = await fetch(`http://localhost:8082/history/${studentId}/borrow/${isbn}`, {
                method: "PUT"
            });

            if (!res.ok) {
                throw new Error("Failed to borrow book. Book may not exist or no copies available.");
            }

            const transaction = await res.json();

            const message = `You have borrowed ${transaction.book.title} until ${transaction.dueDate}`;
            localStorage.setItem("message", message);

            window.location.href = "myaccount.html"

        } catch (error) {
            console.error(error);
            document.getElementById("message").textContent = error.message;
        }
    });
});

function signOut() {
    localStorage.removeItem("studentId");
    window.location.href = "login.html";
}
