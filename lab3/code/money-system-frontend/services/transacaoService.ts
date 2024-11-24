import api from "@/api/api";
import { TransactionResponse } from "@/lib/types";

const url = "/transacao/resgatar"

export const takeVantage = async <T>(data: T): Promise<TransactionResponse> => {
    const response = await api.post(url, data);
    return response.data;
}

const url2 = "/transacao/history";

export const getUserHistory = async<T>(id: number): Promise<TransactionResponse[]> => {
    const response = await api.get(`${url2}/${id}`);
    console.log(response, "resp dentro do service")
    return response.data;
}