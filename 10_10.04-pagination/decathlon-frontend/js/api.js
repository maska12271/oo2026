const API_BASE = "https://decathlon-backend-8sfg.onrender.com";

async function getProducts(page = 0, size = 5, categoryId = "", sort = "id,asc") {
    let url = `${API_BASE}/products?page=${page}&size=${size}&sort=${sort}`;

    if (categoryId) {
        url += `&categoryId=${categoryId}`;
    }

    const response = await fetch(url);
    return await response.json();
}

async function getProductById(id) {
    const products = await getProducts();
    return products.find(p => String(p.id) === String(id));
}

async function addProduct(product) {
    const response = await fetch(`${API_BASE}/products`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(product)
    });
    return await response.json();
}

async function deleteProduct(id) {
    const response = await fetch(`${API_BASE}/products/${id}`, {
        method: "DELETE"
    });
    return await response.json();
}