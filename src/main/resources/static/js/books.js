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

    // Load books
    try {
        const res = await fetch("http://localhost:8082/book/");
        if (!res.ok) {
            throw new Error("Failed to fetch books");
        }

        const books = await res.json();
        const tbody = document.querySelector("#booksTable tbody");

        books.forEach(book => {
            const row = document.createElement("tr");
            row.innerHTML = `
                <td>${book.isbn}</td>
                <td>${book.title}</td>
                <td>${book.author}</td>
                <td>${book.year}</td>
                <td>${book.copies}</td>
            `;
            tbody.appendChild(row);
        });

    } catch (error) {
        console.error(error);
        alert("Error loading books.");
    }
});

function signOut() {
    localStorage.removeItem("studentId");
    window.location.href = "login.html";
}
