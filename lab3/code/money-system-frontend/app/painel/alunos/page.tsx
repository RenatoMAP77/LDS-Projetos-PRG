"use client"
import { List, PlusCircle, RefreshCw, Trash2 } from "lucide-react"
import { ActionCard } from "@/components/root/ActionCard";
import { ROUTES } from "@/lib/constants";
import { useEffect, useState } from "react";
import { Aluno } from "@/lib/types";
import { readAluno } from "@/services/profileService";

const actions = [
    {
        title: "Resgatar vantagens",
        description: "Resgate vantagens",
        icon: <List className="h-6 w-6" />,
        href: ROUTES.PAINEL_ALUNOS_VANTAGENS_RESGATAR
        // },
        // {
        //     title: "Visualize seus recibos",
        //     description: "Visualizar recibos",
        //     icon: <PlusCircle className="h-6 w-6" />,
        //     href: ROUTES.PAINEL_ALUNOS_RECIBOS
        // }
    }
]


export default function HomePage() {

    const idAluno = Number(localStorage.getItem('id'));
    const [aluno, setAluno] = useState<Aluno>();

    useEffect(() => {
        readAluno(idAluno).then((response) => {
            setAluno(response as Aluno);
        })
    }, [idAluno])

    return (
        <div className="container mx-auto px-4 py-8">
            <div className="mb-8 text-center">
                <h1 className="mb-2 text-4xl font-bold">Bem-vindo {aluno?.nome}</h1>
                <p className="text-xl text-muted-foreground">Você possui: {aluno?.saldoMoedas} Moedas</p>
            </div>
            <div className="grid gap-6 md:grid-cols-2 ">
                {actions.map((action) => (
                    <ActionCard key={action.title} {...action} />
                ))}
            </div>

        </div>
    )
}