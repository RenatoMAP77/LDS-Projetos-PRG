import TabelaAlunos from "@/components/root/AlunosTable";
import { Aluno, TipoUsuario } from "@/lib/types";
import { readAllEntidades } from "@/services/crudService";


export default async function Page() {
    const data = await readAllEntidades<Aluno>(TipoUsuario.ALUNO);

    return <TabelaAlunos alunos={data} />;
  
}
