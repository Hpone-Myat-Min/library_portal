document.addEventListener("DOMContentLoaded", () => {
    const isbnForm = document.getElementById("isbnForm");
    const detailsForm = document.getElementById("detailsForm");

    isbnForm.addEventListener("submit", async (e) => {
        e.preventDefault();
        const isbn = document.getElementById("isbnInput").value.trim();

        if (!isbn) {
            document.getElementById("message").textContent = "Please enter an ISBN.";
            return;
        }

        try {
            const res = await fetch(`https://api2.isbndb.com/book/${isbn}`, {
                method: "GET",
                headers: {
                    "Authorization": "61040_9b1212b09eb328addd71fce1fedd3ee4"
                }
            });

            if (!res.ok) {
                throw new Error("Book not found or error fetching details.");
            }

            const data = await res.json();
            const book = data.book;
            const date = new Date(book.date_published).getFullYear();

            document.getElementById("isbn").value = isbn;
            document.getElementById("title").value = book.title || "";
            document.getElementById("author").value = book.authors ? book.authors[0] : "";
            document.getElementById("year").value = date || "";

            detailsForm.style.display = "block";
            isbnForm.style.display = "none";

        } catch (error) {
            console.error(error);
            document.getElementById("message").textContent = "Failed to fetch book details.";
        }
    });

    detailsForm.addEventListener("submit", async (e) => {
        e.preventDefault();

        const newBook = {
            isbn: document.getElementById("isbn").value.trim(),
            title: document.getElementById("title").value.trim(),
            author: document.getElementById("author").value.trim(),
            year: parseInt(document.getElementById("year").value.trim()),
            copies: parseInt(document.getElementById("copies").value.trim())
        };

        try {
            const res = await fetch("http://localhost:8082/admin/books/add", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(newBook)
            });

            if (!res.ok) {
                throw new Error("Failed to add book.");
            }

            localStorage.setItem("bookMessage", "✅ Book added successfully!");
            window.location.href = "books.html";

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
