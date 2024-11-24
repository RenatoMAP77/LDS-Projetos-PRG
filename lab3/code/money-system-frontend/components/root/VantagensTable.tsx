"use client"
import { VantagemRead } from "@/lib/types";
import { Button } from "../ui/button";
import { Transaction, TransactionResponse } from "@/lib/types";
import { takeVantage } from "@/services/transacaoService";
import { useToast } from "@/hooks/use-toast";

type VantagemTableProps = {
    data: VantagemRead[];
};

export default function VantagensTable({
    data,
}: VantagemTableProps) {

    const alunoId = localStorage.getItem("id");
    const { toast } = useToast();

    const handleTakeVantage = async (id: number) => {
        const transaction: Transaction = {
            alunoId: Number(alunoId),
            vantagemId: id
        }


        try {
            const response = await takeVantage(transaction);
            console.log(response);
            toast({
                title: "Vantagem resgatada com sucesso",
                description: "A vantagem foi resgatada.",
            });
        } catch (error: any) {
            toast({
                title: "Erro ao resgatar vantagem",
                description: error.response.data.message,
                variant: "destructive",
            });
        }
    }




    return (
        <table className="min-w-full ">
            <thead>
                <tr>
                    <th className="  px-4 py-2 border-b ">Custo</th>
                    <th className="  px-4 py-2 border-b">Descrição</th>
                    <th className="  px-4 py-2 border-b">Foto</th>
                    <th className="  px-4 py-2 border-b">Ações</th>
                </tr>
            </thead>
            <tbody>
                {data?.map((vantagem, index) => (
                    <tr key={index}>
                        <td className="  px-4 py-2 border-b text-center">
                            {vantagem.custoEmMoedas}
                        </td>
                        <td className="  px-4 py-2 border-b text-center">
                            {vantagem.descricao}
                        </td>
                        <td className=" border-b ">
                            <img src={vantagem.foto} alt={vantagem.descricao} width={100} height={100} ></img>
                        </td>
                        <td className="  px-4 py-2  ">
                            <Button
                                className="bg-green-500 text-white px-2 py-1 rounded w-full"
                                onClick={() => { handleTakeVantage(vantagem.id) }}
                            >
                                Resgatar
                            </Button>
                        </td>
                    </tr>
                ))}
            </tbody>
        </table>
    );
}
