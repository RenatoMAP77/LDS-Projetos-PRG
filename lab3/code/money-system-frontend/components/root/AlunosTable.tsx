"use client";
import React, { useState, useEffect } from "react";
import { useRouter } from "next/navigation";
import { Aluno } from "@/lib/types";
import { Button } from "@/components/ui/button";
import { deleteEntidade, getEntidades } from "@/services/crudService";
import { Skeleton } from "@/components/ui/skeleton";

interface AlunosTableProps {
  alunos: Aluno[];
}

const AlunosTable: React.FC<AlunosTableProps> = ({ alunos }) => {
  const router = useRouter();
  const [alunoList, setAlunoList] = useState<Aluno[]>(alunos);
  const [alunoSelecionado, setAlunoSelecionado] = useState<Aluno | null>(null);
  const [showDeleteModal, setShowDeleteModal] = useState(false);
  const [loading, setLoading] = useState(false);

  const handleEdit = (aluno: Aluno) => {
    router.push(`/alunos/editar?id=${aluno.id}`);
  };

  const handleDelete = (aluno: Aluno) => {
    setAlunoSelecionado(aluno);
    setShowDeleteModal(true);
  };

  const confirmDelete = async () => {
    if (alunoSelecionado?.id) {
      await deleteEntidade(alunoSelecionado.id, "ALUNO");
      reloadAlunos();
    }
    setShowDeleteModal(false);
  };

  const reloadAlunos = async () => {
    setLoading(true);
    const novosAlunos = await getEntidades<Aluno>("ALUNO");
    setAlunoList(novosAlunos);
    setLoading(false);
  };

  useEffect(() => {
    setAlunoList(alunos);
  }, [alunos]);

  console.log(alunos, "alunos")
  return (
    <>
      {loading ? (
        <Skeleton className="h-20 w-full" />
      ) : (
        <table className="min-w-full border border-gray-200">
          <thead>
            <tr className="bg-gray-100">
              <th className="px-4 py-2 border-b">Nome</th>
              <th className="px-4 py-2 border-b">CPF</th>
              <th className="px-4 py-2 border-b">RG</th>
              <th className="px-4 py-2 border-b">Endereço</th>
              <th className="px-4 py-2 border-b">Curso</th>
              <th className="px-4 py-2 border-b">Moedas</th>
              <th className="px-4 py-2 border-b">Instituição</th>
              <th className="px-4 py-2 border-b">Email</th>
              <th className="px-4 py-2 border-b">Editar</th>
              <th className="px-4 py-2 border-b">Excluir</th>
            </tr>
          </thead>
          <tbody>
            {alunoList.map((aluno) => (
              <tr key={aluno.id}>
                <td className="px-4 py-2 border-b">{aluno.nome}</td>
                <td className="px-4 py-2 border-b">{aluno.cpf}</td>
                <td className="px-4 py-2 border-b">{aluno.rg}</td>
                <td className="px-4 py-2 border-b">{aluno.endereco}</td>
                <td className="px-4 py-2 border-b">{aluno.curso}</td>
                <td className="px-4 py-2 border-b">{aluno.saldoMoedas}</td>
                <td className="px-4 py-2 border-b">{aluno.instituicaoId}</td>
                <td className="px-4 py-2 border-b">{aluno.email}</td>
                <td className="px-4 py-2 border-b">
                  <Button
                    onClick={() => handleEdit(aluno)}
                    className="bg-blue-500 hover:bg-blue-600 text-white px-2 py-1 rounded"
                  >
                    Editar
                  </Button>
                </td>
                <td className="px-4 py-2 border-b">
                  <Button
                    onClick={() => handleDelete(aluno)}
                    className="bg-red-500 hover:bg-red-600 text-white px-2 py-1 rounded"
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
