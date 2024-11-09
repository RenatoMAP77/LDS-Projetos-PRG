"use client"
import { VantagemRead } from "@/lib/types";
import { Button } from "../ui/button";

type VantagemTableProps = {
  data: VantagemRead[];
};

export default function VantagensTable({
  data,
}: VantagemTableProps) {
  return (
    <table className="min-w-full ">
      <thead>
        <tr>
          <th className="  px-4 py-2 border-b ">Custo</th>
          <th className="  px-4 py-2 border-b">Descrição</th>
          <th className="  px-4 py-2 border-b">Empresa ID</th>
          <th className="  px-4 py-2 border-b">Ações</th>
        </tr>
      </thead>
      <tbody>
        {data.map((vantagem, index) => (
          <tr key={index}>
            <td className="  px-4 py-2 border-b text-center">
                {vantagem.custoEmMoedas}
            </td>
            <td className="  px-4 py-2 border-b text-center">
              {vantagem.descricao}
            </td>
            <td className="  px-4 py-2 border-b text-center">
              {vantagem.empresa.id}
            </td>
            <td className="  px-4 py-2 flex space-x-2">
              <Button
                className="bg-green-500 text-white px-2 py-1 rounded w-full"
              >
                Resgatar
              </Button>
            </td>
          </tr>
        ))}
      </tbody>
    </table>
  );
}
