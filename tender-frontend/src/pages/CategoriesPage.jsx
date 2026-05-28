import { useEffect, useMemo, useState } from 'react'
import { apiDelete, apiGet, apiPost, apiPut } from '../api/client'
import PageHeader from '../components/PageHeader'
import SearchFilters from '../components/SearchFilters'
import DataTable from '../components/DataTable'
import Modal from '../components/Modal'
import { FormField, TextareaField } from "../components/FormField";
import ConfirmModal from '../components/ConfirmModal'
import { useModal } from '../hooks/useModal'
import { safeArray } from '../utils/format'

const emptyForm = {
    name: '',
    description: '',
    active: true,
}

export default function CategoriesPage() {
    const formModal = useModal()
    const deleteModal = useModal()

    const [rows, setRows] = useState([])
    const [form, setForm] = useState(emptyForm)
    const [editingId, setEditingId] = useState(null)
    const [deletingItem, setDeletingItem] = useState(null)
    const [search, setSearch] = useState('')
    const [statusFilter, setStatusFilter] = useState('')
    const [loading, setLoading] = useState(false)

    useEffect(() => {
        loadData()
    }, [])

    const loadData = async () => {
        const response = await apiGet('/categories?page=0&size=500&sortBy=id&sortDir=desc')
        setRows(safeArray(response))
    }

    const filteredRows = useMemo(() => {
        return rows.filter((row) => {
            const q = search.toLowerCase()
            const matchesSearch =
                !search ||
                row.name?.toLowerCase().includes(q) ||
                row.description?.toLowerCase().includes(q)

            const matchesStatus =
                !statusFilter ||
                (statusFilter === 'active' && row.active) ||
                (statusFilter === 'inactive' && !row.active)

            return matchesSearch && matchesStatus
        })
    }, [rows, search, statusFilter])

    const openCreate = () => {
        setEditingId(null)
        setForm(emptyForm)
        formModal.open()
    }

    const openEdit = (item) => {
        setEditingId(item.id)
        setForm({
            name: item.name || '',
            description: item.description || '',
            active: !!item.active,
        })
        formModal.open()
    }

    const openDelete = (item) => {
        setDeletingItem(item)
        deleteModal.open()
    }

    const handleChange = (e) => {
        const { name, value, type, checked } = e.target
        setForm((prev) => ({ ...prev, [name]: type === 'checkbox' ? checked : value }))
    }

    const handleSubmit = async (e) => {
        e.preventDefault()
        setLoading(true)
        try {
            if (editingId) {
                await apiPut(`/categories/${editingId}`, form)
            } else {
                await apiPost('/categories', form)
            }
            formModal.close()
            setEditingId(null)
            setForm(emptyForm)
            await loadData()
        } finally {
            setLoading(false)
        }
    }

    const handleDelete = async () => {
        if (!deletingItem) return
        setLoading(true)
        try {
            await apiDelete(`/categories/${deletingItem.id}`)
            deleteModal.close()
            setDeletingItem(null)
            await loadData()
        } finally {
            setLoading(false)
        }
    }

    const columns = [
        { key: 'name', label: 'Name' },
        { key: 'description', label: 'Description' },
        {
            key: 'active',
            label: 'Status',
            render: (row) => (
                <span className={`rounded-full px-3 py-1 text-xs font-semibold ${row.active ? 'bg-emerald-100 text-emerald-700' : 'bg-slate-200 text-slate-700 dark:bg-slate-800 dark:text-slate-300'}`}>
          {row.active ? 'Active' : 'Inactive'}
        </span>
            ),
        },
        {
            key: 'actions',
            label: 'Actions',
            render: (row) => (
                <div className="flex gap-2">
                    <button onClick={() => openEdit(row)} className="rounded-lg bg-blue-600 px-3 py-1.5 text-xs font-medium text-white">Edit</button>
                    <button onClick={() => openDelete(row)} className="rounded-lg bg-red-600 px-3 py-1.5 text-xs font-medium text-white">Delete</button>
                </div>
            ),
        },
    ]

    return (
        <div className="space-y-6">
            <PageHeader
                title="Categories"
                description="Manage product categories."
                action={
                    <button onClick={openCreate} className="rounded-xl bg-teal-600 px-4 py-2.5 text-sm font-medium text-white hover:bg-teal-700">
                        Add category
                    </button>
                }
            />

            <SearchFilters
                search={search}
                onSearchChange={setSearch}
                filters={[
                    {
                        key: 'status',
                        value: statusFilter,
                        onChange: setStatusFilter,
                        options: [
                            { value: '', label: 'All statuses' },
                            { value: 'active', label: 'Active' },
                            { value: 'inactive', label: 'Inactive' },
                        ],
                    },
                ]}
            />

            <DataTable columns={columns} rows={filteredRows} />

            <Modal isOpen={formModal.isOpen} title={editingId ? 'Edit category' : 'Add category'} onClose={formModal.close} width="max-w-2xl">
                <form onSubmit={handleSubmit} className="grid gap-4">
                    <FormField
                        id="category-name"
                        label="Name"
                        name="name"
                        value={form.name}
                        onChange={handleChange}
                        required
                        placeholder="Name"
                        className="md:col-span-2"
                    />

                    <TextareaField
                        id="category-description"
                        label="Description"
                        name="description"
                        value={form.description}
                        onChange={handleChange}
                        placeholder="Description"
                        className="md:col-span-2"
                    />
                    <label className="md:col-span-2 inline-flex items-center gap-3 rounded-xl border border-slate-200 px-4 py-3 text-sm dark:border-slate-800">
                        <input
                            type="checkbox"
                            name="active"
                            checked={form.active}
                            onChange={handleChange}
                            className="h-4 w-4 rounded border-slate-300 text-teal-600 focus:ring-teal-500 dark:border-slate-700"
                        />
                        <span className="font-medium text-slate-700 dark:text-slate-200">Active</span>
                    </label>

                    <div className="flex col-span-2 justify-end gap-3">
                        <button type="button" onClick={formModal.close} className="rounded-xl border border-slate-300 px-4 py-2.5 dark:border-slate-700">Cancel</button>
                        <button type="submit" disabled={loading} className="rounded-xl bg-teal-600 px-4 py-2.5 font-medium text-white hover:bg-teal-700 disabled:opacity-60">
                            {loading ? 'Saving...' : editingId ? 'Save changes' : 'Create category'}
                        </button>
                    </div>
                </form>
            </Modal>

            <ConfirmModal
                isOpen={deleteModal.isOpen}
                title="Delete category"
                message={`Delete "${deletingItem?.name || ''}"?`}
                onClose={deleteModal.close}
                onConfirm={handleDelete}
                loading={loading}
            />
        </div>
    )
}