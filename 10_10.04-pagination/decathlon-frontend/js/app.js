let currentPage = 0;
let currentSize = 5;
let currentCategoryId = "";
let currentSortBy = "id";
let currentDirection = "asc";
let totalPages = 1;

async function loadProducts() {
    const container = document.getElementById("product-list");
    const pageInfo = document.getElementById("page-info");
    const prevBtn = document.getElementById("prev-btn");
    const nextBtn = document.getElementById("next-btn");

    let url = `${API_BASE}/products?page=${currentPage}&size=${currentSize}&sortBy=${currentSortBy}&direction=${currentDirection}`;

    if (currentCategoryId !== "") {
        url += `&categoryId=${currentCategoryId}`;
    }

    const response = await fetch(url);
    const data = await response.json();

    const products = data.content;
    totalPages = data.totalPages;

    container.innerHTML = "";

    if (!products || products.length === 0) {
        container.innerHTML = `<div class="message error">No products found</div>`;
        pageInfo.textContent = `Page 0 of 0`;
        prevBtn.disabled = true;
        nextBtn.disabled = true;
        return;
    }

    products.forEach(product => {
        const div = document.createElement("div");
        div.className = "card";
        div.innerHTML = `
            <h3>${product.name}</h3>
            <p>${product.description ?? ""}</p>
            <p><strong>Price:</strong> €${product.price}</p>
            <p><strong>Active:</strong> ${product.active}</p>
            <button onclick='addToCart(${JSON.stringify(product)})'>Add to cart</button>
            <button class="secondary" onclick="goToProduct(${product.id})">Details</button>
        `;
        container.appendChild(div);
    });

    pageInfo.textContent = `Page ${data.number + 1} of ${data.totalPages}`;

    prevBtn.disabled = currentPage === 0;
    nextBtn.disabled = currentPage >= totalPages - 1;
}

function goToProduct(id) {
    window.location.href = `product.html?id=${id}`;
}

function applyFilters() {
    const categoryValue = document.getElementById("category-select").value;
    const sortValue = document.getElementById("sort-select").value;
    const sizeValue = document.getElementById("size-select").value;

    const sortParts = sortValue.split(",");

    currentCategoryId = categoryValue;
    currentSortBy = sortParts[0];
    currentDirection = sortParts[1];
    currentSize = Number(sizeValue);
    currentPage = 0;

    loadProducts();
}

function nextPage() {
    if (currentPage < totalPages - 1) {
        currentPage++;
        loadProducts();
    }
}

function prevPage() {
    if (currentPage > 0) {
        currentPage--;
        loadProducts();
    }
}

document.addEventListener("DOMContentLoaded", () => {
    loadProducts();
});