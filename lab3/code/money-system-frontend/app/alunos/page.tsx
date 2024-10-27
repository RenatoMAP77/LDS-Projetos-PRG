import {  List, PlusCircle, RefreshCw, Trash2 } from "lucide-react"
import { ActionCard } from "@/components/root/ActionCard";

const actions = [
  {
    title: "Listar",
    description: "Visualize todos alunos cadastrados",
    icon: <List className="h-6 w-6" />,
    href: "/alunos/listar"
  },
  {
    title: "Adicionar",
    description: "Registre um novo aluno",
    icon: <PlusCircle className="h-6 w-6" />,
    href: "/alunos/adicionar"
  }
]


export default function HomePage() {

  return (
    <div className="container mx-auto px-4 py-8">
      <div className="mb-8 text-center">
        <h1 className="mb-2 text-4xl font-bold">Alunos</h1>
        <p className="text-xl text-muted-foreground">Gerencie os alunos</p>
      </div>
      <div className="grid gap-6 md:grid-cols-2 ">
        {actions.map((action) => (
          <ActionCard key={action.title} {...action} />
        ))}
      </div>
      
    </div>
  )
}