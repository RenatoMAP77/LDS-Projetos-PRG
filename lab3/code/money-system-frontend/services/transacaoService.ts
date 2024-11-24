import api from "@/api/api";
import { TransactionResponse } from "@/lib/types";

const url = "/transacao/resgatar"

export const takeVantage = async <T>(data: T): Promise<TransactionResponse> => {
    const response = await api.post(url, data);
    return response.data;
}