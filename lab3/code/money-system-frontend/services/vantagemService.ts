import api from "@/api/api";

const url = "/vantagem"

export const readAllVantagens = async <T>(): Promise<T> => {
  const response = await api.get(url);
  return response.data;
};

export const createVantagem = async <T>(data: T): Promise<void> => {
  await api.post(url, data);
};
