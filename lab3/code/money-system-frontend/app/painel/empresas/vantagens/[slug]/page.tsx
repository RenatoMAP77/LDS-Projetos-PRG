import VantagensTable from "@/components/root/VantagensTable";
import { VantagemRead, TipoForm, TipoUsuario } from "@/lib/types";
import { readAllVantagens } from "@/services/vantagemService";
import VantagensForm from "@/components/root/VantagensForm";

interface Params {
    params: {
        slug: string;
    };
}

export default async function Page({ params }: Params) {
    const { slug } = await params;
    const data = await readAllVantagens<VantagemRead[]>();


    if (slug === TipoForm.ADICIONAR.toLowerCase()) {
        return (
            <VantagensForm />
        );
    } else {
        return <VantagensTable data={data} />;
    }
}
