import { type ClassValue, clsx } from "clsx";
import { Briefcase, School, Home, Coins, HandHeartIcon, Paperclip } from "lucide-react";
import { twMerge } from "tailwind-merge";
import { ROUTES } from "./constants";

export function cn(...inputs: ClassValue[]) {
    return twMerge(clsx(inputs));
}

export const data = {
    navMain: [
        {
            title: "Alunos",
            home: ROUTES.PAINEL_ALUNOS,
            icon: School,
            items: [
                {
                    title: "Início",
                    url: ROUTES.PAINEL_ALUNOS,
                    breadcrumb: "Início",
                },
                {
                    title: "Resgatar vantagens",
                    url: ROUTES.PAINEL_ALUNOS_VANTAGENS_RESGATAR,
                    breadcrumb: "Resgatar Vantagens",
                },
                {
                    title: "Visualizar histórico",
                    url: ROUTES.PAINEL_ALUNOS_RECIBOS,
                    breadcrumb: "Visualizar histórico",
                },
                {
                    title: "Sair",
                    url: ROUTES.HOME,
                    breadcrumb: "",
                }
            ],
        },
        {
            title: "Empresas",
            home: ROUTES.PAINEL_EMPRESAS,
            icon: Briefcase,
            items: [
                {
                    title: "Início",
                    url: ROUTES.PAINEL_EMPRESAS,
                    breadcrumb: "Início",
                },
                {
                    title: "Cadastrar vantagens",
                    url: ROUTES.PAINEL_EMPRESAS_VANTAGENS_ADICIONAR,
                    breadcrumb: "Listar Vantagens",
                },
                {
                    title: "Listar vantagens",
                    url: ROUTES.PAINEL_EMPRESAS_VANTAGENS_LISTAR,
                    breadcrumb: "Listar Vantagens",
                },
                {
                    title: "Sair",
                    url: ROUTES.HOME,
                    breadcrumb: "",
                }
            ],

        },
        {
            title: "Professores",
            home: ROUTES.PAINEL_PROFESSORES,
            icon: School,
            items: [
                {
                    title: "Início",
                    url: ROUTES.PAINEL_PROFESSORES,
                    breadcrumb: "Início",
                },
                {
                    title: "Doar moedas",
                    url: ROUTES.PAINEL_PROFESSORES_MOEDAS,
                    breadcrumb: "Doar moedas",
                },
                // {
                //     title: "Visualizar recibos",
                //     url: ROUTES.PAINEL_PROFESSORES_RECIBOS,
                //     breadcrumb: "Visualizar recibos",
                // },
                {
                    title: "Sair",
                    url: ROUTES.HOME,
                    breadcrumb: "",
                }
            ],
        },
    ],
};