import TabelaEmpresas from "@/components/root/EmpresasTable";
import GenericForm from "@/components/root/GenericForm";
import { Empresa } from "@/lib/types";
import { readAllEntidades } from "@/services/crudService";

export default async function Page({ params }: any) {
  const { slug } = await params;

  const data = await readAllEntidades<Empresa>("EMPRESA");

  if (slug === "adicionar") {
    return <GenericForm tipo="adicionar" entidade="EMPRESA" />;
  } else if (slug === "editar") {
    return <GenericForm tipo="editar" entidade="EMPRESA" />;
  } else {
    return <TabelaEmpresas empresas={data} />;
  }
}
