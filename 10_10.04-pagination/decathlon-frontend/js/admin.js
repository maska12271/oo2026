async function loadAdminProducts() {
    const tbody = document.getElementById("admin-products-body");
    if (!tbody) return;

    const products = await getProducts();
    tbody.innerHTML = "";

    products.forEach(product => {
        const tr = document.createElement("tr");
        tr.innerHTML = `
            <td>${product.id}</td>
            <td>${product.name}</td>
            <td>${product.price}</td>
            <td>${product.stock}</td>
            <td>${product.active}</td>
            <td>
                <button class="danger" onclick="removeProduct(${product.id})">Delete</button>
            </td>
        `;
        tbody.appendChild(tr);
    });
}

async function removeProduct(id) {
    await deleteProduct(id);
    loadAdminProducts();
}

async function handleAddProduct(event) {
    event.preventDefault();

    const product = {
        name: document.getElementById("name").value,
        description: document.getElementById("description").value,
        price: Number(document.getElementById("price").value),
        active: document.getElementById("active").value === "true",
        stock: Number(document.getElementById("stock").value)
    };

    await addProduct(product);
    event.target.reset();
    loadAdminProducts();
}

document.addEventListener("DOMContentLoaded", () => {
    const form = document.getElementById("product-form");
    if (form) {
        form.addEventListener("submit", handleAddProduct);
    }
    loadAdminProducts();
});