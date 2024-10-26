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
import { Empresa } from "@/lib/types";

interface FormularioProps {
  tipo: "adicionar" | "editar";
}

const formSchema = z.object({
  nome: z.string().min(2, { message: "Insira no mínimo 2 caracteres." }),
  email: z.string().email(),
  senha: z.string().min(8, { message: "Insira no mínimo 8 caracteres." }),
  cnpj: z.string().length(14, { message: "Insira 14 caracteres." }),
  descricao: z
    .string()
    .max(1000)
    .min(10, { message: "Insira no mínimo 10 caracteres e no máximo 1000." }),
});

const EmpresasFormulary: React.FC<FormularioProps> = ({ tipo }) => {
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
      cnpj: "",
      descricao: "",
    },
  });

  useEffect(() => {
    const fetchEmpresa = async () => {
      if (id) {
        try {
          const empresa = await getEntidadeById<Empresa>(id, "EMPRESA");
          form.reset(empresa);
        } catch (error) {
          toast({
            title: "Erro ao carregar a empresa",
            description: "Não foi possível carregar os dados da empresa.",
          });
        }
      }
    };

    if (tipo === "editar") {
      fetchEmpresa();
    }
  }, [id, tipo, form, toast]);

  async function onSubmit(values: z.infer<typeof formSchema>) {
    try {
      setLoading(true);
      if (tipo === "adicionar") {
        await createEntidade(values, "EMPRESA");
        toast({
          title: "Empresa criada com sucesso!",
          description: "Você será redirecionado para a página anterior.",
        });
      } else {
        const formatedValues = {
          ...values,
          id: id?.toString(),
        };
        await updateEntidade<Empresa>(formatedValues, "EMPRESA");
        toast({
          title: "Empresa atualizada com sucesso!",
          description: "Você será redirecionado para a página anterior.",
        });
      }
      setTimeout(() => {
        router.push("/empresas/listar");
      }, 1000);
    } catch (e) {
      toast({
        title: "Erro!",
        description: `Erro ao ${
          tipo === "adicionar" ? "criar" : "atualizar"
        } empresa, tente novamente.`,
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
            name="cnpj"
            render={({ field }) => (
              <FormItem>
                <FormLabel htmlFor="cnpj">CNPJ</FormLabel>
                <Input {...field} />
                <FormMessage>{form.formState.errors.cnpj?.message}</FormMessage>
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
                <FormMessage>
                  {form.formState.errors.descricao?.message}
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

export default EmpresasFormulary;
