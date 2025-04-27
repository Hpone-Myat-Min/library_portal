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

    document.getElementById("returnForm").addEventListener("submit", async (e) => {
        e.preventDefault();

        const isbn = document.getElementById("bookIsbn").value.trim();
        if (!isbn) {
            document.getElementById("message").textContent = "Please enter an ISBN.";
            return;
        }

        try {
            const res = await fetch(`http://localhost:8082/history/${studentId}/return/${isbn}`, {
                method: "PUT"
            });

            if (!res.ok) {
                throw new Error("Failed to return book.");
            }

            const invoice = await res.json();

            if(invoice!=null){
                const message = `You have returned the book successfully. You have been fined ${invoice.amount}. Please pay at the payment portal using the reference: ${invoice.reference}`;
                localStorage.setItem("returnMessage", message);

            }
            else{
                const message = `You have returned the book successfully.`;
                localStorage.setItem("returnMessage", message);
            }

            window.location.href = "myaccount.html";

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
