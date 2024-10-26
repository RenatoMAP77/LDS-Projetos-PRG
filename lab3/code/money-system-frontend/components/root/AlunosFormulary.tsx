"use client";
import React, { useEffect, useState } from "react";
import {
  Form,
  FormControl,
  FormField,
  FormItem,
  FormLabel,
  FormMessage,
  FormDescription,
} from "@/components/ui/form";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { zodResolver } from "@hookform/resolvers/zod";
import { useForm } from "react-hook-form";
import { z } from "zod";
import {
  createEntidade,
  updateEntidade,
  getEntidadeById,
} from "@/services/crudService";
import { useToast } from "@/hooks/use-toast";
import { useRouter } from "next/navigation";
import { useSearchParams } from "next/navigation";
import { Aluno } from "@/lib/types";

interface FormularioProps {
  tipo: "adicionar" | "editar";
}

const formSchema = z.object({
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

const AlunosFormulary: React.FC<FormularioProps> = ({ tipo }) => {
  const [loading, setLoading] = useState(false);
  const router = useRouter();
  const { toast } = useToast();
  const searchParams = useSearchParams();
  const id = searchParams.get("id");

  const form = useForm<z.infer<typeof formSchema>>({
    resolver: zodResolver(formSchema),
    defaultValues: {
      nome: "",
      email: "",
      senha: "",
      cpf: "",
      rg: "",
      endereco: "",
      saldoMoedas: "",
      curso: "",
      instituicaoId: "",
    },
  });

  useEffect(() => {
    const fetchAluno = async () => {
      if (id) {
        try {
          const Aluno = await getEntidadeById<Aluno>(id, "ALUNO");
          form.reset(Aluno);
        } catch (error) {
          toast({
            title: "Erro ao carregar a Aluno",
            description: "Não foi possível carregar os dados da Aluno.",
          });
        }
      }
    };

    if (tipo === "editar") {
      fetchAluno();
    }
  }, [id, tipo, form, toast]);

  async function onSubmit(values: z.infer<typeof formSchema>) {

    try {
      setLoading(true);
      if (tipo === "adicionar") {
        await createEntidade(values, "ALUNO");
        toast({
          title: "Aluno criado com sucesso!",
          description: "Você será redirecionado para a página anterior.",
        });
      } else {
        const formatedValues = {
          ...values,
          id: id?.toString(),
        };
        await updateEntidade<Aluno>(formatedValues, "ALUNO");
        toast({
          title: "Aluno atualizado com sucesso!",
          description: "Você será redirecionado para a página anterior.",
        });
      }
      setTimeout(() => {
        router.push("/alunos/listar");
      }, 1000);
    } catch (e) {
      toast({
        title: "Erro!",
        description: `Erro ao ${
          tipo === "adicionar" ? "criar" : "atualizar"
        } aluno, tente novamente.`,
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
                <FormMessage>{form.formState.errors.nome?.message}</FormMessage>
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
                <FormMessage>
                  {form.formState.errors.email?.message}
                </FormMessage>
              </FormItem>
            )}
          />
          <FormField
            control={form.control}
            name="senha"
            render={({ field }) => (
              <FormItem>
                <FormLabel htmlFor="senha">Senha</FormLabel>
                <Input {...field} />
                <FormMessage>
                  {form.formState.errors.senha?.message}
                </FormMessage>
              </FormItem>
            )}
          />
          <FormField
            control={form.control}
            name="cpf"
            render={({ field }) => (
              <FormItem>
                <FormLabel htmlFor="cpf">CPF</FormLabel>
                <Input {...field} />
                <FormMessage>{form.formState.errors.cpf?.message}</FormMessage>
              </FormItem>
            )}
          />
          <FormField
            control={form.control}
            name="rg"
            render={({ field }) => (
              <FormItem>
                <FormLabel htmlFor="rg">RG</FormLabel>
                <Input {...field} />
                <FormMessage>{form.formState.errors.rg?.message}</FormMessage>
              </FormItem>
            )}
          />
          <FormField
            control={form.control}
            name="saldoMoedas"
            render={({ field }) => (
              <FormItem>
                <FormLabel htmlFor="saldoMoedas">SaldoMoedas</FormLabel>
                <Input {...field} />
                <FormMessage>
                  {form.formState.errors.saldoMoedas?.message}
                </FormMessage>
              </FormItem>
            )}
          />
          <FormField
            control={form.control}
            name="instituicaoId"
            render={({ field }) => (
              <FormItem>
                <FormLabel htmlFor="instituicaoId">Instituição</FormLabel>
                <Input {...field} />
                <FormMessage>
                  {form.formState.errors.instituicaoId?.message}
                </FormMessage>
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
                <FormMessage>
                  {form.formState.errors.curso?.message}
                </FormMessage>
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
                <FormMessage>
                  {form.formState.errors.endereco?.message}
                </FormMessage>
              </FormItem>
            )}
          />

          <Button type="submit" className="mt-8" disabled={loading}>
            {tipo === "adicionar" ? "Adicionar" : "Salvar"}
          </Button>
        </div>
      </form>
    </Form>
  );
};

export default AlunosFormulary;
