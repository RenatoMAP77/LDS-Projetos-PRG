import { Empresa, tipoUsuario, Aluno } from "@/lib/types";
import api from "@/api/api";

const getEndpoint = (tipo: tipoUsuario) => {
  switch (tipo) {
    case 'EMPRESA':
      return "/empresaParceira";
    case 'ALUNO':
      return "/aluno";
    default:
      throw new Error("Tipo de usuário inválido");
  }
};

export const getEntidades = async <T>(tipo: tipoUsuario): Promise<T[]> => {
  const endpoint = getEndpoint(tipo);
  const response = await api.get(endpoint);
  return response.data;
};

export const getEntidadeById = async <T>(id: string, tipo: tipoUsuario): Promise<T> => {
  const endpoint = getEndpoint(tipo);
  const response = await api.get(`${endpoint}/${id}`);
  return response.data;
};

export const createEntidade = async <T>(entidade: T, tipo: tipoUsuario): Promise<T> => {
  const endpoint = getEndpoint(tipo);
  const response = await api.post(endpoint, entidade);
  return response.data;
};

export const updateEntidade = async <T extends { id?: string }>(entidade: T, tipo: tipoUsuario): Promise<T> => {
  if (!entidade.id) throw new Error("O campo 'id' é obrigatório para atualizar a entidade.");
  
  const endpoint = getEndpoint(tipo);
  const response = await api.put(`${endpoint}/${entidade.id}`, entidade);
  return response.data;
};

export const deleteEntidade = async (id: string, tipo: tipoUsuario): Promise<void> => {
  const endpoint = getEndpoint(tipo);
  await api.delete(`${endpoint}/${id}`);
};
