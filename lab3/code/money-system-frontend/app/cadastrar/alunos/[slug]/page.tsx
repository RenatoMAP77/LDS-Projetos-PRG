import TabelaAlunos from "@/components/root/AlunosTable";
import GenericForm from "@/components/root/GenericForm";
import { Aluno, TipoForm, TipoUsuario } from "@/lib/types";
import { readAllEntidades } from "@/services/crudService";

interface Params {
    params: {
        slug: string;
    };
}

export default async function Page({ params }: Params) {
    const { slug } = await params;
    const data = await readAllEntidades<Aluno>(TipoUsuario.ALUNO);

    if (slug === TipoForm.ADICIONAR.toLowerCase()) {
        return <GenericForm tipo={TipoForm.ADICIONAR} entidade={TipoUsuario.ALUNO} />;

    } else if (slug === TipoForm.EDITAR.toLowerCase()) {
        return <GenericForm tipo={TipoForm.EDITAR} entidade={TipoUsuario.ALUNO} />;
    } else {
        return <TabelaAlunos alunos={data} />;
    }
}
