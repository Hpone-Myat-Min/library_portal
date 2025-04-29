document.addEventListener("DOMContentLoaded", async () => {
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

    // Show success message if came from borrow/return page
    const returnMessage = localStorage.getItem("message");
    if (returnMessage) {
        const banner = document.createElement("div");
        banner.style.backgroundColor = "#d4edda";
        banner.style.color = "#155724";
        banner.style.padding = "1rem";
        banner.style.margin = "1rem 0";
        banner.style.border = "1px solid #c3e6cb";
        banner.style.borderRadius = "5px";
        banner.innerHTML = returnMessage;

        document.body.prepend(banner);

        localStorage.removeItem("message");
    }

    try {
        const res = await fetch(`http://localhost:8082/history/student/${studentId}`);
        if (!res.ok) {
            throw new Error("Failed to fetch loans.");
        }

        const transactions = await res.json();
        const tbody = document.querySelector("#loansTable tbody");
        tbody.innerHTML = "";

        if (transactions.length === 0) {
            document.getElementById("message").textContent = "You have no borrowed books.";
            return;
        }

        for(let i=0; i<transactions.length; i++) {
            const eachTransaction = transactions[i];
            const row = document.createElement("tr");

            const dueDate = new Date(eachTransaction.dueDate);
            const today = new Date();

            const difference = today - dueDate;
            let overdueDays = 0;

            if (today > dueDate) {
                overdueDays = Math.floor(difference / (1000 * 60 * 60 * 24));
            }

            row.innerHTML = `
                <td>${eachTransaction.book.isbn}</td>
                <td>${eachTransaction.dateBorrowed}</td>
                <td>${eachTransaction.dateReturned || ""}</td>
                <td>${overdueDays}</td>`;

            tbody.appendChild(row);
        }

    } catch (error) {
        console.error(error);
        document.getElementById("message").textContent = error.message;
    }
});

function signOut() {
    localStorage.removeItem("studentId");
    window.location.href = "login.html";
}
