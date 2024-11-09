import { School, Briefcase, Coins, HandHeartIcon, Paperclip } from "lucide-react";
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
    {
      title: "Vantagens",
      description: "Gerecie as  vantagens na plataforma",
      icon: <HandHeartIcon className="h-6 w-6" />,
      href: "/vantagens",
    },
    {
      title: "Moedas",
      description: "Gerecie as moedas na plataforma",
      icon: <Coins className="h-6 w-6" />,
      href: "/moedas",
    },
    {
      title: "Extratos",
      description: "Gerecie os extratos na plataforma",
      icon: <Paperclip className="h-6 w-6" />,
      href: "/extratos",
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
