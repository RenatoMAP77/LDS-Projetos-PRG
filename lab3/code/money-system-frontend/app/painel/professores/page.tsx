"use client"
import { Coins, List, PlusCircle, RefreshCw, Trash2 } from "lucide-react"
import { ActionCard } from "@/components/root/ActionCard";
import { ROUTES } from "@/lib/constants";
import { readProfessor } from "@/services/profileService";
import { useEffect, useState } from "react";
import { Professor } from "@/lib/types";

const actions = [
    {
        title: "Doar moedas",
        description: "Doe moedas para os seus melhores alunos",
        icon: <Coins className="h-6 w-6" />,
        href: ROUTES.PAINEL_PROFESSORES_MOEDAS
        // },
        // {
        //     title: "Visualize seus recibos",
        //     description: "Visualizar recibos",
        //     icon: <PlusCircle className="h-6 w-6" />,
        //     href: ROUTES.PAINEL_PROFESSORES_RECIBOS
        // }
    }
]


export default function HomePage() {
    const idProfessor = Number(localStorage.getItem('id'));
    const [professor, setProfessor] = useState<Professor>();

    useEffect(() => {
        readProfessor(idProfessor).then((response) => {
            setProfessor(response as Professor);
        })
    }, [idProfessor])


    
    return (
        <div className="container mx-auto px-4 py-8">
            <div className="mb-8 text-center">
                <h1 className="mb-2 text-2xl font-bold">Bem-vindo {professor?.nome}</h1>
                <p className="text-xl text-muted-foreground">Você possui: {professor?.saldoMoedas} Moedas</p>
            </div>
            <div className="grid gap-6 md:grid-cols-2 ">
                {actions.map((action) => (
                    <ActionCard key={action.title} {...action} />
                ))}
            </div>

        </div>
    )
}