"use client";
import React, { useState, useEffect } from "react";
import { useRouter } from "next/navigation";
import { Aluno } from "@/lib/types";
import { Button } from "@/components/ui/button";
import { useEntidade } from "@/context/EntidadeContext";
import { Skeleton } from "@/components/ui/skeleton";
import { TipoUsuario } from "@/lib/types";

interface AlunosTableProps {
  alunos: Aluno[];
}

const AlunosTable: React.FC<AlunosTableProps> = ({ alunos }) => {
  const router = useRouter();
  const [alunoList, setAlunoList] = useState<Aluno[]>(alunos);
  const [alunoSelecionado, setAlunoSelecionado] = useState<Aluno | null>(null);
  const [showDeleteModal, setShowDeleteModal] = useState(false);
  const [loading, setLoading] = useState(false);
  const {lerEntidades, deletarEntidade} = useEntidade();

  const handleEdit = (aluno: Aluno) => {
    router.push(`/alunos/editar?id=${aluno.id}`);
  };

  const handleDelete = (aluno: Aluno) => {
    setAlunoSelecionado(aluno);
    setShowDeleteModal(true);
  };

  const confirmDelete = async () => {
    if (alunoSelecionado?.id) {
      await deletarEntidade(alunoSelecionado.id, TipoUsuario.ALUNO);
      reloadAlunos();
    }
    setShowDeleteModal(false);
  };

  const reloadAlunos = async () => {
    setLoading(true);
    const novosAlunos = await lerEntidades(TipoUsuario.ALUNO);
    setAlunoList(novosAlunos);
    setLoading(false);
  };

  useEffect(() => {
    setAlunoList(alunos);
  }, [alunos]);

 

  return (
    <>
      {loading ? (
        <Skeleton className="h-20 w-full" />
      ) : (
        <table className="min-w-full">
          <thead>
            <tr>
              <th className="px-4 py-2 border-b text-center">Nome</th>
              <th className="px-4 py-2 border-b text-center">CPF</th>
              <th className="px-4 py-2 border-b text-center">RG</th>
              <th className="px-4 py-2 border-b text-center">Endereço</th>
              <th className="px-4 py-2 border-b text-center">Curso</th>
              <th className="px-4 py-2 border-b text-center">Moedas</th>
              <th className="px-4 py-2 border-b text-center">Instituição</th>
              <th className="px-4 py-2 border-b text-center">Email</th>
              <th className="px-4 py-2 border-b text-center">Editar</th>
              <th className="px-4 py-2 border-b text-center">Excluir</th>
            </tr>
          </thead>
          <tbody>
            {alunoList.map((aluno) => (
              <tr key={aluno.id}>
                <td className="px-4 py-2 border-b text-center">{aluno.nome}</td>
                <td className="px-4 py-2 border-b text-center">{aluno.cpf}</td>
                <td className="px-4 py-2 border-b text-center">{aluno.rg}</td>
                <td className="px-4 py-2 border-b text-center">{aluno.endereco}</td>
                <td className="px-4 py-2 border-b text-center">{aluno.curso}</td>
                <td className="px-4 py-2 border-b text-center">{aluno.saldoMoedas}</td>
                <td className="px-4 py-2 border-b text-center">{aluno.instituicao?.nome}</td>
                <td className="px-4 py-2 border-b text-center">{aluno.email}</td>
                <td className="px-4 py-2 border-b text-center">
                  <Button
                    onClick={() => handleEdit(aluno)}
                    className="bg-blue-500 hover:bg-blue-600 text-white px-2 py-1 rounded w-full"
                  >
                    Editar
                  </Button>
                </td>
                <td className="px-4 py-2 border-b">
                  <Button
                    onClick={() => handleDelete(aluno)}
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
              {alunoSelecionado?.nome}?
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

export default AlunosTable;
