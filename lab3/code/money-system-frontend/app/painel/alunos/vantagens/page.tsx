import VantagensTable from "@/components/root/VantagensTable";
import { VantagemRead } from "@/lib/types";
import { readAllVantagens } from "@/services/vantagemService";


export default async function Page() {
    const data = await readAllVantagens<VantagemRead[]>();
    console.log(data, "data dentro da page")
    return <VantagensTable data={data} />;
}
