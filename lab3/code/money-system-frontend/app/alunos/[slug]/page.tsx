import AlunosTable from "@/components/root/AlunosTable";
import GenericForm from "@/components/root/GenericForm";
import { Aluno } from "@/lib/types";
import { readAllEntidades } from "@/services/crudService";

export default async function Page({ params }: any) {
  const { slug } = await params;
  
  const data = await readAllEntidades<Aluno>('ALUNO');

  if (slug === "adicionar") {
    return <GenericForm tipo="adicionar" entidade="ALUNO" />;
  } else if (slug === "editar") {
    return <GenericForm tipo="editar" entidade="ALUNO" />;
  } else {
    return <AlunosTable alunos={data} />;
  }
}
