import api from "@/api/api";
import { LoginResponse } from "@/lib/types";

const urlProfessor = "/professor/login"
const urlAluno = "/aluno/login"
const urlEmpresa = "/empresaParceira/login"


export const loginProfessor = async <T>(data: T): Promise<LoginResponse> => {
    const response = await api.post(urlProfessor, data);
    return response.data;
}

export const loginAluno = async <T>(data: T): Promise<LoginResponse> => {
    const response = await api.post(urlAluno, data);
    return response.data
}

export const loginEmpresa = async <T>(data: T): Promise<LoginResponse> => {
    const response = await api.post(urlEmpresa, data);
    return response.data;
}