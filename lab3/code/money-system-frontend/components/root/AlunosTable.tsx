"use client";
import React, { useState, useEffect } from "react";
import { useRouter } from "next/navigation";
import { Aluno } from "@/lib/types";
import { Button } from "@/components/ui/button";
import { useEntidade } from "@/context/EntidadeContext";
import { Skeleton } from "@/components/ui/skeleton";
import { TipoUsuario } from "@/lib/types";
import { Input } from "@/components/ui/input";
import { zodResolver } from "@hookform/resolvers/zod";
import { set, useForm } from "react-hook-form";
import * as z from "zod";
import {
    Form,
    FormControl,
    FormField,
    FormItem,
    FormLabel,
    FormMessage,
} from "@/components/ui/form";
import { Textarea } from "@/components/ui/textarea";
import { createDonate } from "@/services/donateService";
import { toast } from "@/hooks/use-toast";
import { Loader } from "lucide-react";

interface AlunosTableProps {
    alunos: Aluno[];
}

const formSchema = z.object({
    quantidade: z.number().min(1, "A quantidade deve ser maior que zero"),
    descricao: z.string().min(1, "A descrição é obrigatória"),
});

const AlunosTable: React.FC<AlunosTableProps> = ({ alunos }) => {
    const [alunoList, setAlunoList] = useState<Aluno[]>(alunos);
    const [alunoSelecionado, setAlunoSelecionado] = useState<Aluno | null>(null);
    const [showDonationModal, setShowDonationModal] = useState(false);
    const [loading, setLoading] = useState(false);
    const [loadingButton, setLoadingButton] = useState(false)
    const { lerEntidades, deletarEntidade } = useEntidade();
    const idProfessor = localStorage.getItem("id");

    const form = useForm<z.infer<typeof formSchema>>({
        resolver: zodResolver(formSchema),
        defaultValues: {
            quantidade: 0,
            descricao: "",
        },
    });

    const handleDonation = (aluno: Aluno) => {
        setAlunoSelecionado(aluno);
        setShowDonationModal(true);
    };

    const onSubmit = async (values: z.infer<typeof formSchema>) => {
        try {
            setLoadingButton(true);
            const data = {
                idAluno: alunoSelecionado?.id,
                idProfessor: Number(idProfessor),
                quantidade: values.quantidade,
                descricao: values.descricao,
            }
            await createDonate(data);
            await reloadAlunos();
            toast({
                title: "Doação feita com sucesso",
            });
        }
        catch (error) {
            toast({
                title: "Erro!",
                variant: "destructive",
            });
            console.error("Doação falhou:", error);
        } finally {
            setShowDonationModal(false);
            setLoadingButton(false);
        }
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
                            <th className="px-4 py-2 border-b text-center">Curso</th>
                            <th className="px-4 py-2 border-b text-center">Moedas</th>
                            <th className="px-4 py-2 border-b text-center">Instituição</th>
                            <th className="px-4 py-2 border-b text-center">Doar Moedas</th>
                        </tr>
                    </thead>
                    <tbody>
                        {alunoList.map((aluno) => (
                            <tr key={aluno.id}>
                                <td className="px-4 py-2 border-b text-center">{aluno.nome}</td>
                                <td className="px-4 py-2 border-b text-center">{aluno.cpf}</td>
                                <td className="px-4 py-2 border-b text-center">{aluno.rg}</td>
                                <td className="px-4 py-2 border-b text-center">{aluno.curso}</td>
                                <td className="px-4 py-2 border-b text-center">{aluno.saldoMoedas}</td>
                                <td className="px-4 py-2 border-b text-center">{aluno.instituicao?.nome}</td>
                                <td className="px-4 py-2 border-b text-center">
                                    <Button
                                        onClick={() => handleDonation(aluno)}
                                        className="bg-green-500 hover:bg-green-600 text-white px-2 py-1 rounded w-full"
                                        disabled={loadingButton}
                                    >
                                        {loadingButton ? <Loader /> : "Doar"}
                                    </Button>
                                </td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            )}

            {showDonationModal && (
                <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50">
                    <div className="bg-white p-6 rounded shadow-lg w-full max-w-md">
                        <h2 className="text-xl font-bold mb-4">Doar Moedas para {alunoSelecionado?.nome}</h2>
                        <Form {...form}>
                            <form onSubmit={form.handleSubmit(onSubmit)} className="space-y-4">
                                <FormField
                                    control={form.control}
                                    name="quantidade"
                                    render={({ field }) => (
                                        <FormItem>
                                            <FormLabel>Quantidade de Moedas</FormLabel>
                                            <FormControl>
                                                <Input type="number" {...field} onChange={(e) => field.onChange(Number(e.target.value))} />
                                            </FormControl>
                                            <FormMessage />
                                        </FormItem>
                                    )}
                                />
                                <FormField
                                    control={form.control}
                                    name="descricao"
                                    render={({ field }) => (
                                        <FormItem>
                                            <FormLabel>Descrição</FormLabel>
                                            <FormControl>
                                                <Textarea {...field} />
                                            </FormControl>
                                            <FormMessage />
                                        </FormItem>
                                    )}
                                />
                                <div className="flex justify-end space-x-2 pt-2">
                                    <Button
                                        type="button"
                                        onClick={() => setShowDonationModal(false)}
                                        variant="secondary"
                                        disabled={loadingButton}
                                    >
                                        Cancelar
                                    </Button>
                                    <Button type="submit" disabled={loadingButton}
                                    >
                                        {loadingButton ? <Loader /> : "Confirmar"}
                                    </Button>
                                </div>
                            </form>
                        </Form>
                    </div>
                </div>
            )}
        </>
    );
};

export default AlunosTable;