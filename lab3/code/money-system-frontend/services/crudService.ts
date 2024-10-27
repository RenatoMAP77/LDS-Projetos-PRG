import { tipoUsuario } from "@/lib/types";
import api from "@/api/api";

const endpointMap: Record<tipoUsuario, string> = {
  EMPRESA: "/empresaParceira",
  ALUNO: "/aluno",
};

const getEndpoint = (tipo: tipoUsuario): string => {
  const endpoint = endpointMap[tipo];
  if (!endpoint) {
    throw new Error("Tipo de usuário inválido");
  }
  return endpoint;
};

export const readAllEntidades = async <T>(tipo: tipoUsuario): Promise<T[]> => {
  const endpoint = getEndpoint(tipo);
  const response = await api.get(endpoint);
  return response.data;
};

export const readEntidadeById = async <T>(
  id: string,
  tipo: tipoUsuario
): Promise<T> => {
  const endpoint = getEndpoint(tipo);
  const response = await api.get(`${endpoint}/${id}`);
  return response.data;
};

export const createEntidade = async <T>(
  entidade: T,
  tipo: tipoUsuario
): Promise<T> => {
  const endpoint = getEndpoint(tipo);
  const response = await api.post(endpoint, entidade);
  return response.data;
};

export const updateEntidade = async <T extends { id?: string }>(
  entidade: T,
  tipo: tipoUsuario
): Promise<T> => {
  if (!entidade.id)
    throw new Error("O campo 'id' é obrigatório para atualizar a entidade.");

  const endpoint = getEndpoint(tipo);
  const response = await api.put(`${endpoint}/${entidade.id}`, entidade);
  return response.data;
};

export const deleteEntidade = async (
  id: string,
  tipo: tipoUsuario
): Promise<void> => {
  const endpoint = getEndpoint(tipo);
  await api.delete(`${endpoint}/${id}`);
};
