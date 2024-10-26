import TabelaEmpresas from "@/components/root/EmpresasTable";
import EmpresasFormulary from "@/components/root/EmpresasFormulary";
import { Empresa } from "@/lib/types";
import { getEntidades } from "@/services/crudService";

export default async function Page({ params }: any) {
  const { slug } = await params;
  
  const data = await getEntidades<Empresa>('EMPRESA');

  if (slug === "adicionar") {
    return <EmpresasFormulary tipo="adicionar" />;
  } else if (slug === "editar") {
    return <EmpresasFormulary tipo="editar" />;
  } else {
    return <TabelaEmpresas empresas={data} />;
  }
}
