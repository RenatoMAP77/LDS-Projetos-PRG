"use client"
import { useEffect, useState } from "react";
import { getProfessorHistory } from "@/services/transacaoService";
import { TransactionResponse } from "@/lib/types";
import { Card, CardHeader, CardTitle, CardDescription } from "@/components/ui/card";

export default function HomePage() {

    const idAluno = Number(localStorage.getItem('id'));
    const [data, setData] = useState<TransactionResponse[]>();


    useEffect(() => {
        getProfessorHistory(idAluno).then((response) => {
            setData(response);
        })
    }, [])

    if (data?.length === null) return <div>Você não fez nenhuma transação ainda!</div>

    return (
        <div className="container mx-auto px-4 py-8">
            <div className="mb-8 text-center">
                <h1 className="mb-2 text-4xl font-bold">Seu histórico de transações</h1>
                <p className="text-xl text-muted-foreground">Você possui {data?.length} transações realizadas</p>
            </div>
            <div className="grid gap-6 md:grid-cols-2 ">
                {data?.map((transaction) => (
                    <Card className="transition-all hover:shadow-md hover:-translate-y-1" key={transaction.id}>
                        <CardHeader>
                            <CardTitle className="flex items-center gap-2">
                                <span>Para aluno: {transaction.aluno.nome} | Quantidade de moedas: {transaction.quantidade}</span>
                            </CardTitle>
                            <CardDescription>Descrição: {transaction.descricao}</CardDescription>
                        </CardHeader>
                    </Card>
                ))}
            </div>

        </div>
    )
}