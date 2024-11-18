import api from "@/api/api";

const url = "/transacao/premiar"

export const createDonate = async <T>(data: T): Promise<void> => {
    await api.post(url, data);
};
