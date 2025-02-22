import Link from "next/link"

export default function Dashboard() {
  return (
    <div className="flex min-h-screen flex-col items-center justify-center p-24">
      <h1 className="text-2xl font-bold mb-4">Dashboard del Usuario</h1>
      <div className="flex gap-4">
        <Link
          href="/dashboard/profile"
          className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded"
        >
          Perfil
        </Link>
        <Link
          href="/dashboard/tickets"
          className="bg-green-500 hover:bg-green-700 text-white font-bold py-2 px-4 rounded"
        >
          Mis Tickets
        </Link>
        <Link
          href="/dashboard/history"
          className="bg-purple-500 hover:bg-purple-700 text-white font-bold py-2 px-4 rounded"
        >
          Historial de Viajes
        </Link>
      </div>
    </div>
  )
}

