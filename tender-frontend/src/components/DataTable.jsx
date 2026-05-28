export default function DataTable({ columns, rows }) {
    return (
        <div className="overflow-hidden rounded-2xl border border-slate-200 bg-white dark:border-slate-800 dark:bg-slate-900">
            <div className="overflow-x-auto">
                <table className="min-w-full text-sm">
                    <thead className="bg-slate-50 dark:bg-slate-800/70">
                    <tr>
                        {columns.map((column) => (
                            <th
                                key={column.key}
                                className="whitespace-nowrap px-4 py-3 text-left font-semibold text-slate-600 dark:text-slate-300"
                            >
                                {column.label}
                            </th>
                        ))}
                    </tr>
                    </thead>
                    <tbody>
                    {rows.length === 0 ? (
                        <tr>
                            <td colSpan={columns.length} className="px-4 py-12 text-center text-slate-500 dark:text-slate-400">
                                No data found.
                            </td>
                        </tr>
                    ) : (
                        rows.map((row, index) => (
                            <tr key={row.id ?? index} className="border-t border-slate-200 dark:border-slate-800">
                                {columns.map((column) => (
                                    <td key={column.key} className="px-4 py-3 align-top text-slate-700 dark:text-slate-200">
                                        {column.render ? column.render(row) : row[column.key]}
                                    </td>
                                ))}
                            </tr>
                        ))
                    )}
                    </tbody>
                </table>
            </div>
        </div>
    )
}