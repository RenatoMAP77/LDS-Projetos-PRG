import api from "@/api/api";

const urlProfessor = "/professor"
const urlAluno = "/aluno"
const urlEmpresa = "/empresaParceira"

export const readProfessor = async <T>(id: number): Promise<T> => {
    const response = await api.get(`${urlProfessor}/${id}`);
    return response.data;
}

export const readAluno = async <T>(id: number): Promise<T> => {
    const response = await api.get(`${urlAluno}/${id}`);
    return response.data;
}

export const readEmpresa = async <T>(id: number): Promise<T> => {
    const response = await api.get(`${urlEmpresa}/${id}`);
    return response.data;
}
