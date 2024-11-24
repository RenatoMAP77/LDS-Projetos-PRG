"use client"
import { useEffect, useState } from "react";
import { getUserHistory } from "@/services/transacaoService";
import { TransactionResponse } from "@/lib/types";
import { Card, CardHeader, CardTitle, CardDescription } from "@/components/ui/card";

export default function HomePage() {

    const idAluno = Number(localStorage.getItem('id'));
    const [data, setData] = useState<TransactionResponse[]>();


    useEffect(() => {
        getUserHistory(idAluno).then((response) => {
            setData(response);
        })
    }, [])

    if (data?.length === null) return <div>Você não fez nenhum resgate ainda!</div>

    console.log(data)
    return (
        <div className="container mx-auto px-4 py-8">
            <div className="mb-8 text-center">
                <h1 className="mb-2 text-4xl font-bold">Seu histórico de resgates</h1>
                <p className="text-xl text-muted-foreground">Você possui {data?.length} resgates feitos</p>
            </div>
            <div className="grid gap-6 md:grid-cols-2 ">
                {data?.map((transaction) => (
                    <Card className="transition-all hover:shadow-md hover:-translate-y-1" key={transaction.id}>
                        <CardHeader>
                            <CardTitle className="flex items-center gap-2">
                                <span>Id: {transaction.id} | Custo: {transaction.quantidade}</span>
                            </CardTitle>
                            <CardDescription>{transaction.descricao}</CardDescription>
                        </CardHeader>
                    </Card>
                ))}
            </div>

        </div>
    )
}