import { School, Briefcase } from "lucide-react";
import { ActionCard } from "@/components/root/ActionCard";


export default function HomePage() {
  const actions = [
    {
      title: "Alunos",
      description: "Gerencie os alunos na plataforma",
      icon: <School className="h-6 w-6" />,
      href: "/alunos",
    },
    {
      title: "Empresas Parceiras",
      description: "Gerecie as empresas parceiras na plataforma",
      icon: <Briefcase className="h-6 w-6" />,
      href: "/empresas",
    },
    
  ];

  return (
    <div className="container mx-auto px-4 py-8">
      <div className="mb-8 text-center">
        <h1 className="mb-2 text-4xl font-bold">
          Sistema de Moedas Estudantil
        </h1>
        <p className="text-xl text-muted-foreground">
          Gerencie os alunos e empresas parceiras
        </p>
      </div>
      <div className="grid gap-6 md:grid-cols-2">
        {actions.map((action) => (
          <ActionCard key={action.title} {...action} />
        ))}
      </div>
    </div>
  );
}
