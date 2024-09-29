import api from "@/lib/api"
import { Cliente } from "@/lib/utils"

// função de login
export async function login(email: string, senha: string) {
  const response = await api.post("/clientes/login", {
    email,
    senha,
  })

  return response.data
}


// cadastrar um novo cliente
export async function cadastrarCliente(cliente: Cliente) {
  const response = await api.post("/clientes", cliente)

  return response.data
}