
import { clsx, type ClassValue } from "clsx"
import { twMerge } from "tailwind-merge"

export function cn(...inputs: ClassValue[]) {
  return twMerge(clsx(inputs))
}

export type Cliente = {
  bairro: string
  cep: string
  cidade: string
  cpf: string
  email: string
  empregadores: string[]
  estado: string
  nome: string
  numero: string
  pais: string
  profissao: string
  rendimento: string
  rg: string
  rua: string
  senha: string
}