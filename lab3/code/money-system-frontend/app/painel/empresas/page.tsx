"use client"
import { List, PlusCircle, RefreshCw, Trash2 } from "lucide-react"
import { ActionCard } from "@/components/root/ActionCard";
import { Empresa, Aluno } from "@/lib/types";
import { readEmpresa } from "@/services/profileService";
import { useState, useEffect } from "react";

const actions = [
    {
        title: "Listar",
        description: "Visualize todos empresas cadastrados",
        icon: <List className="h-6 w-6" />,
        href: "/painel/empresas/vantagens/listar"
    },
    {
        title: "Adicionar",
        description: "Registre uma novo empresa",
        icon: <PlusCircle className="h-6 w-6" />,
        href: "/painel/empresas/vantagens/adicionar"
    }
]


export default function HomePage() {

    const idEmpresa = Number(localStorage.getItem('id'));
    const [empresa, setEmpresa] = useState<Empresa>();

    useEffect(() => {
        readEmpresa(idEmpresa).then((response) => {
            setEmpresa(response as Empresa);
        })
    }, [idEmpresa])

    return (
        <div className="container mx-auto px-4 py-8">
            <div className="mb-8 text-center">
                <h1 className="mb-2 text-4xl font-bold">Bem-vindo {empresa?.nome}</h1>
                <p className="text-xl text-muted-foreground">Gerencie aqui suas vantagens</p>
            </div>
            <div className="grid gap-6 md:grid-cols-2 ">
                {actions.map((action) => (
                    <ActionCard key={action.title} {...action} />
                ))}
            </div>

        </div>
    )
}