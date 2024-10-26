import { type ClassValue, clsx } from "clsx";
import { Briefcase, School, Home } from "lucide-react";
import { twMerge } from "tailwind-merge";

export function cn(...inputs: ClassValue[]) {
  return twMerge(clsx(inputs));
}

export const data = {
  navMain: [
    {
      title: "Início",
      home: "/",
      icon: Home,
    },
    {
      title: "Alunos",
      home: "/alunos",
      icon: School,
      items: [
        {
          title: "Listar Alunos",
          url: "/alunos/listar",
          breadcrumb: "Listar Alunos",
        },
        {
          title: "Adicionar Alunos",
          url: "/alunos/adicionar",
          breadcrumb: "Adicionar Alunos",
        }
      ],
    },
    {
      title: "Empresas Parceiras",
      home:"/empresas",
      icon: Briefcase,
      items: [
        {
          title: "Listar Empresas",
          url: "/empresas/listar",
          breadcrumb: "Listar Empresas",
        },
        {
          title: "Adicionar Empresa",
          url: "/empresas/adicionar",
          breadcrumb: "Adicionar Empresa",
        }
      ],
    },
  ],
};