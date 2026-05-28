const API_BASE_URL = 'http://localhost:8080/api'

async function request(path, options = {}) {
    const response = await fetch(`${API_BASE_URL}${path}`, {
        headers: {
            'Content-Type': 'application/json',
            ...(options.headers || {}),
        },
        ...options,
    })

    if (!response.ok) {
        const errorText = await response.text()
        throw new Error(errorText || `Request failed: ${response.status}`)
    }

    if (response.status === 204) {
        return null
    }

    const contentType = response.headers.get('content-type') || ''
    const text = await response.text()

    if (!text) {
        return null
    }

    if (contentType.includes('application/json')) {
        return JSON.parse(text)
    }

    return text
}

export function apiGet(path) {
    return request(path)
}

export function apiPost(path, body) {
    return request(path, {
        method: 'POST',
        body: JSON.stringify(body),
    })
}

export function apiPut(path, body) {
    return request(path, {
        method: 'PUT',
        body: JSON.stringify(body),
    })
}

export function apiDelete(path) {
    return request(path, {
        method: 'DELETE',
    })
}