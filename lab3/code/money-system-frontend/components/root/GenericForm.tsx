"use client";
import React, { useEffect, useState } from "react";
import {
  Form,
  FormField,
  FormItem,
  FormLabel,
  FormMessage,
} from "@/components/ui/form";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { zodResolver } from "@hookform/resolvers/zod";
import { useForm } from "react-hook-form";
import { z } from "zod";
import {
  createEntidade,
  updateEntidade,
  readEntidadeById,
} from "@/services/crudService";
import { useToast } from "@/hooks/use-toast";
import { useRouter } from "next/navigation";
import { useSearchParams } from "next/navigation";
import { useEntidade } from "@/context/EntidadeContext";

const AlunoSchema = z.object({
  nome: z.string().min(2, { message: "Insira no mínimo 2 caracteres." }),
  email: z.string().email(),
  senha: z.string().min(8, { message: "Insira no mínimo 8 caracteres." }),
  cpf: z.string().length(11, { message: "Insira 11 caracteres." }),
  rg: z.string().length(8, { message: "Insira 8 caracteres." }),
  endereco: z.string().min(2, { message: "Insira no mínimo 2 caracteres." }),
  curso: z.string().min(2, { message: "Insira no mínimo 2 caracteres." }),
  saldoMoedas: z.string().min(1, { message: "Insira no mínimo 1 caractere." }),
  instituicaoId: z
    .string()
    .min(1, { message: "Insira no mínimo 1 caractere." }),
});

const EmpresaSchema = z.object({
  nome: z.string().min(2, { message: "Insira no mínimo 2 caracteres." }),
  email: z.string().email(),
  senha: z.string().min(8, { message: "Insira no mínimo 8 caracteres." }),
  cnpj: z.string().length(14, { message: "Insira 14 caracteres." }),
  descricao: z
    .string()
    .max(1000)
    .min(10, { message: "Insira no mínimo 10 caracteres e no máximo 1000." }),
});
type FormValues = z.infer<typeof AlunoSchema> | z.infer<typeof EmpresaSchema>;

enum TipoUsuario {
  ALUNO = "ALUNO",
  EMPRESA = "EMPRESA",
}
enum TipoForm {
  ADICIONAR = "ADICIONAR",
  EDITAR = "EDITAR",
}

interface GenericFormProps {
  tipo: TipoForm;
  entidade: TipoUsuario;
}

export default function GenericForm({ tipo, entidade }: GenericFormProps) {
  const [loading, setLoading] = useState(false);
  const router = useRouter();
  const { toast } = useToast();
  const searchParams = useSearchParams();
  const id = searchParams.get("id") ?? undefined;
  const schema = entidade === "ALUNO" ? AlunoSchema : EmpresaSchema;
  const { adicionarEntidade, editarEntidade } = useEntidade();

  const form = useForm<FormValues>({
    resolver: zodResolver(schema),
    defaultValues: {
      nome: "",
      email: "",
      senha: "",
      cpf: "",
      rg: "",
      endereco: "",
      saldoMoedas: "",
      cnpj: "",
      descricao: "",
      instituicaoId: "",
      curso: "",
    },
  });

  useEffect(() => {
    const fetchEntidade = async () => {
      if (id) {
        try {
          const data = (await readEntidadeById(id, entidade)) as z.infer<
            typeof schema
          >;
          form.reset(data);
        } catch (error) {
          toast({
            title: `Erro ao carregar ${entidade}`,
            description: `Não foi possível carregar os dados da ${entidade}.`,
          });
        }
      }
    };

    if (tipo === TipoForm.EDITAR) {
      fetchEntidade();
    }
  }, [id, tipo, entidade, form, toast]);

  async function onSubmit(values: z.infer<typeof schema>) {
    try {
      setLoading(true);
      if (tipo === TipoForm.ADICIONAR) {
        await adicionarEntidade(values, entidade);
      } else {
        await editarEntidade(id as string, values, entidade);
      }
      setTimeout(() => {
        router.push(`/${entidade.toLowerCase()}s/listar`);
      }, 1000);
    } catch (e) {
      toast({
        title: "Erro!",
        description: `Erro ao ${
          tipo === TipoForm.ADICIONAR ? "criar" : "atualizar"
        } ${entidade}, tente novamente.`,
      });
      setLoading(false);
    }
  }

  return (
    <Form {...form}>
      <form onSubmit={form.handleSubmit(onSubmit)} className="space-y-8">
        <div className="grid grid-cols-1 md:grid-cols-2 gap-4 p-10">
          <FormField
            control={form.control}
            name="nome"
            render={({ field }) => (
              <FormItem>
                <FormLabel htmlFor="nome">Nome</FormLabel>
                <Input {...field} />
              </FormItem>
            )}
          />
          <FormField
            control={form.control}
            name="email"
            render={({ field }) => (
              <FormItem>
                <FormLabel htmlFor="email">Email</FormLabel>
                <Input {...field} />
              </FormItem>
            )}
          />
          <FormField
            control={form.control}
            name="senha"
            render={({ field }) => (
              <FormItem>
                <FormLabel htmlFor="senha">
                  Senha (mínimo 8 caracteres)
                </FormLabel>
                <Input type="password" {...field} />
              </FormItem>
            )}
          />
          {entidade === "ALUNO" ? (
            <>
              <FormField
                control={form.control}
                name="cpf"
                render={({ field }) => (
                  <FormItem>
                    <FormLabel htmlFor="cpf">CPF (11 caracteres)</FormLabel>
                    <Input {...field} />
                  </FormItem>
                )}
              />
              <FormField
                control={form.control}
                name="rg"
                render={({ field }) => (
                  <FormItem>
                    <FormLabel htmlFor="rg">RG (8 caracteres)</FormLabel>
                    <Input {...field} />
                  </FormItem>
                )}
              />
              <FormField
                control={form.control}
                name="saldoMoedas"
                render={({ field }) => (
                  <FormItem>
                    <FormLabel htmlFor="saldoMoedas">Saldo Moedas</FormLabel>
                    <Input {...field} />
                  </FormItem>
                )}
              />
              <FormField
                control={form.control}
                name="curso"
                render={({ field }) => (
                  <FormItem>
                    <FormLabel htmlFor="curso">Curso</FormLabel>
                    <Input {...field} />
                  </FormItem>
                )}
              />
              <FormField
                control={form.control}
                name="endereco"
                render={({ field }) => (
                  <FormItem>
                    <FormLabel htmlFor="endereco">Endereço</FormLabel>
                    <Input {...field} />
                  </FormItem>
                )}
              />
              <FormField
                control={form.control}
                name="instituicaoId"
                render={({ field }) => (
                  <FormItem>
                    <FormLabel htmlFor="instituicaoId">
                      Instituição ID
                    </FormLabel>
                    <Input {...field} />
                  </FormItem>
                )}
              />
            </>
          ) : (
            <>
              <FormField
                control={form.control}
                name="cnpj"
                render={({ field }) => (
                  <FormItem>
                    <FormLabel htmlFor="cnpj">CNPJ (14 caracteres)</FormLabel>
                    <Input {...field} />
                  </FormItem>
                )}
              />
              <FormField
                control={form.control}
                name="descricao"
                render={({ field }) => (
                  <FormItem>
                    <FormLabel htmlFor="descricao">Descrição</FormLabel>
                    <Input {...field} />
                  </FormItem>
                )}
              />
            </>
          )}
        </div>
        <div className="flex justify-center mb-10">
          <Button type="submit" disabled={loading}>
            {loading
              ? "Carregando..."
              : `${
                  tipo === TipoForm.ADICIONAR ? "Adicionar" : "Atualizar"
                } ${entidade}`}
          </Button>
        </div>
      </form>
    </Form>
  );
}
