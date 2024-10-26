import AlunosTable from "@/components/root/AlunosTable";
import AlunosFormulary from "@/components/root/AlunosFormulary";
import { Aluno } from "@/lib/types";
import { getEntidades } from "@/services/crudService";

export default async function Page({ params }: any) {
  const { slug } = await params;
  
  const data = await getEntidades<Aluno>('ALUNO');

  if (slug === "adicionar") {
    return <AlunosFormulary tipo="adicionar" />;
  } else if (slug === "editar") {
    return <AlunosFormulary tipo="editar" />;
  } else {
    return <AlunosTable alunos={data} />;
  }
}
