document.addEventListener("DOMContentLoaded", async () => {
    const studentId = localStorage.getItem("studentId");

    if (studentId !== "admin") {
        alert("Access denied.");
        window.location.href = "login.html";
        return;
    }

    try {
        const res = await fetch("http://localhost:8082/admin/transactions");

        if (!res.ok) {
            throw new Error("Failed to fetch students overview.");
        }

        const studentMap = await res.json();
        const tbody = document.querySelector("#studentsTable tbody");
        tbody.innerHTML = "";

        if (studentMap.length === 0) {
            document.getElementById("message").textContent = "No students found.";
            return;
        }

        for (const studentId in studentMap) {
            const stats = studentMap[studentId];
            const row = document.createElement("tr");
            row.innerHTML = `
                <td>${studentId}</td>
                <td>${stats.totalLoans}</td>
                <td>${stats.totalOverdues}</td>
            `;
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
