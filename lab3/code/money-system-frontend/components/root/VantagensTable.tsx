"use client"
import { TipoUsuario, VantagemRead } from "@/lib/types";
import { Button } from "../ui/button";
import { Transaction } from "@/lib/types";
import { takeVantage } from "@/services/transacaoService";
import { useToast } from "@/hooks/use-toast";
import { useState } from "react";
import { Loader } from "lucide-react";

type VantagemTableProps = {
    data: VantagemRead[];
    tipo: TipoUsuario;
};

export default function VantagensTable({
    data,
    tipo
}: VantagemTableProps) {
    const alunoId = localStorage.getItem("id");
    const { toast } = useToast();
    const [loading, setLoading] = useState(false);

    const handleTakeVantage = async (id: number) => {
        const transaction: Transaction = {
            alunoId: Number(alunoId),
            vantagemId: id
        }


        try {
            setLoading(true);
            await takeVantage(transaction);
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
        } finally {
            setLoading(false);
        }
    }

    return (
        <table className="min-w-full ">
            <thead>
                <tr>
                    <th className="  px-4 py-2 border-b ">Custo</th>
                    <th className="  px-4 py-2 border-b">Descrição</th>
                    <th className="  px-4 py-2 border-b">Foto</th>
                    {tipo === TipoUsuario.ALUNO && <th className="  px-4 py-2 border-b">Ação</th>}
                </tr >
            </thead >
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
                        {tipo === TipoUsuario.ALUNO && <td className="  px-4 py-2  ">
                            <Button
                                className="bg-green-500 text-white px-2 py-1 rounded w-full"
                                onClick={() => { handleTakeVantage(vantagem.id) }}
                                disabled={loading}
                            >
                                {loading ? <Loader /> : "Resgatar"}
                            </Button>
                        </td>}
                    </tr>
                ))
                }
            </tbody>
        </table >
    )
}