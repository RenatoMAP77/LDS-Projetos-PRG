import VantagensTable from "@/components/root/VantagensTable";
import { TipoUsuario, VantagemRead } from "@/lib/types";
import { readAllVantagens } from "@/services/vantagemService";


export default async function Page() {
    const data = await readAllVantagens<VantagemRead[]>();
    return <VantagensTable data={data} tipo={TipoUsuario.ALUNO} />;
}
