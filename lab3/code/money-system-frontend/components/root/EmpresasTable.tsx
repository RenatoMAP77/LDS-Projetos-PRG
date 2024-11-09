"use client";
import React, { useState, useEffect } from "react";
import { useRouter } from "next/navigation";
import { Empresa } from "@/lib/types";
import { Button } from "@/components/ui/button";
import {Skeleton} from "@/components/ui/skeleton";
import { useEntidade } from "@/context/EntidadeContext";
import { TipoUsuario } from "@/lib/types";

interface TabelaEmpresasProps {
  empresas: Empresa[];
}

const TabelaEmpresas: React.FC<TabelaEmpresasProps> = ({ empresas }) => {
  const router = useRouter();
  const [empresaList, setEmpresaList] = useState<Empresa[]>(empresas);
  const [empresaSelecionada, setEmpresaSelecionada] = useState<Empresa | null>(
    null
  );
  const [showDeleteModal, setShowDeleteModal] = useState(false);
  const [loading, setLoading] = useState(false);
  const { lerEntidades, deletarEntidade } = useEntidade();

  const handleEdit = (empresa:Empresa) => {

    router.push(`/empresas/editar?id=${empresa.id}`);
  
  };

  const handleDelete = (empresa: Empresa) => {
    setEmpresaSelecionada(empresa);
    setShowDeleteModal(true);
  };

  const confirmDelete = async () => {
    if (empresaSelecionada?.id) {
      await deletarEntidade(empresaSelecionada.id,TipoUsuario.EMPRESA);
      reloadEmpresas();
    }
    setShowDeleteModal(false);
  };

  const reloadEmpresas = async () => {
    setLoading(true);
    const novasEmpresas = await lerEntidades(TipoUsuario.EMPRESA);
    setEmpresaList(novasEmpresas);
    setLoading(false);
  };

  useEffect(() => {
    setEmpresaList(empresas);
  }, [empresas]);

  return (
    <>
      {loading ? (
        <Skeleton className="h-20 w-full" />
      ) : (
        <table className="min-w-full ">
          <thead>
            <tr className="">
              <th className="px-4 py-2 border-b text-center">Nome</th>
              <th className="px-4 py-2 border-b text-center">CNPJ</th>
              <th className="px-4 py-2 border-b text-center">Descrição</th>
              <th className="px-4 py-2 border-b text-center">Email</th>
              <th className="px-4 py-2 border-b text-center">Editar</th>
              <th className="px-4 py-2 border-b text-center">Excluir</th>
            </tr>
          </thead>
          <tbody>
            {empresaList.map((empresa) => (
              <tr key={empresa.id}>
                <td className="px-4 py-2 border-b text-center">{empresa.nome}</td>
                <td className="px-4 py-2 border-b text-center">{empresa.cnpj}</td>
                <td className="px-4 py-2 border-b text-center">{empresa.descricao}</td>
                <td className="px-4 py-2 border-b text-center">{empresa.email}</td>
                <td className="px-4 py-2 border-b ">
                  <Button
                    onClick={() => handleEdit(empresa)}
                    className="bg-blue-500 hover:bg-blue-600 text-white px-2 py-1 rounded w-full"
                  >
                    Editar
                  </Button>
                </td>
                <td className="px-4 py-2 border-b">
                  <Button
                    onClick={() => handleDelete(empresa)}
                    className="bg-red-500 hover:bg-red-600 text-white px-2 py-1 rounded w-full"
                  >
                    Excluir
                  </Button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
  

      {showDeleteModal && (
        <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50">
          <div className="bg-white p-6 rounded shadow-lg w-full max-w-md">
            <h2 className="text-lg font-bold mb-4">Confirmação de Exclusão</h2>
            <p className="mb-6">
              Tem certeza de que deseja excluir a empresa{" "}
              {empresaSelecionada?.nome}?
            </p>
            <div className="flex justify-end space-x-2">
              <Button
                onClick={() => setShowDeleteModal(false)}
                className="bg-gray-200 hover:bg-gray-300 text-gray-700 px-4 py-2 rounded"
              >
                Cancelar
              </Button>
              <Button
                onClick={confirmDelete}
                className="bg-red-500 hover:bg-red-600 text-white px-4 py-2 rounded"
              >
                Excluir
              </Button>
            </div>
          </div>
        </div>
      )}
    </>
  );
};

export default TabelaEmpresas;
