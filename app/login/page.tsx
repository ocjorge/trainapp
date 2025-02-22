"use client"

import { useState } from "react"
import { useRouter } from "next/navigation"

export default function Login() {
  const [email, setEmail] = useState("")
  const [password, setPassword] = useState("")
  const router = useRouter()

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault()
    // Aquí implementaremos la lógica de inicio de sesión
    console.log("Inicio de sesión con:", email, password)
    // Redirigir al usuario a su dashboard después del inicio de sesión
    router.push("/dashboard")
  }

  return (
    <div className="flex min-h-screen flex-col items-center justify-center p-24">
      <h1 className="text-2xl font-bold mb-4">Iniciar Sesión</h1>
      <form onSubmit={handleSubmit} className="w-full max-w-xs">
        <input
          type="email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          placeholder="Correo electrónico"
          className="mb-4 w-full p-2 border rounded"
          required
        />
        <input
          type="password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          placeholder="Contraseña"
          className="mb-4 w-full p-2 border rounded"
          required
        />
        <button type="submit" className="w-full bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded">
          Iniciar Sesión
        </button>
      </form>
    </div>
  )
}

