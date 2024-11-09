import {  List, PlusCircle, RefreshCw, Trash2 } from "lucide-react"
import { ActionCard } from "@/components/root/ActionCard";

const actions = [
  {
    title: "Listar",
    description: "Visualize todos vantagens cadastradas",
    icon: <List className="h-6 w-6" />,
    href: "/vantagens/listar"
  },
  {
    title: "Adicionar",
    description: "Registre uma nova vantagem",
    icon: <PlusCircle className="h-6 w-6" />,
    href: "/vantagens/adicionar"
  }, 
  {
    title: "Resgatar",
    description: "Resgatar uma vantagem",
    icon: <PlusCircle className="h-6 w-6" />,
    href: "/vantagens/resgatar"

  }
]


export default function HomePage() {

  return (
    <div className="container mx-auto px-4 py-8">
      <div className="mb-8 text-center">
        <h1 className="mb-2 text-4xl font-bold">Vantagens</h1>
        <p className="text-xl text-muted-foreground">Gerencie as vantagens</p>
      </div>
      <div className="grid gap-6 md:grid-cols-2 ">
        {actions.map((action) => (
          <ActionCard key={action.title} {...action} />
        ))}
      </div>
      
    </div>
  )
}