document.addEventListener("DOMContentLoaded", async () => {
    try {
        const res = await fetch("http://localhost:8082/admin/loans/overdue");
        const transactions = await res.json();
        const tbody = document.querySelector("#overduedtable tbody");
        tbody.innerHTML = "";

        transactions.forEach(t => {
            const row = document.createElement("tr");
            row.innerHTML = `
                    <td>${t.book.isbn}</td>
                    <td>${t.book.title}</td>
                    <td>${t.studentId}</td>
                    <td>${t.dateBorrowed}</td>
                `;
            tbody.appendChild(row);

        });

    } catch (error) {
        console.error(error);
        alert("Failed to fetch current loans.");
    }
});

function signOut() {
    localStorage.removeItem("studentId");
    window.location.href = "login.html";
}
