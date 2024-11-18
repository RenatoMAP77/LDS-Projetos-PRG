import TabelaEmpresas from "@/components/root/EmpresasTable";
import GenericForm from "@/components/root/GenericForm";
import { Empresa, TipoForm, TipoUsuario } from "@/lib/types";
import { readAllEntidades } from "@/services/crudService";


interface Params {
    params: {
        slug: string;
    };
}

export default async function Page({ params }: Params) {
    const { slug } = await params;
    const data = await readAllEntidades<Empresa>(TipoUsuario.EMPRESA);

    if (slug === TipoForm.ADICIONAR.toLowerCase()) {
        return (
            <GenericForm tipo={TipoForm.ADICIONAR} entidade={TipoUsuario.EMPRESA} />
        );
    } else if (slug === TipoForm.EDITAR.toLowerCase()) {
        return (
            <GenericForm tipo={TipoForm.EDITAR} entidade={TipoUsuario.EMPRESA} />
        );
    } else {
        return <TabelaEmpresas empresas={data} />;
    }
}
