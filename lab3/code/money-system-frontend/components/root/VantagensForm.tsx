"use client";

import React, { useState } from "react";
import {
    Form,
    FormControl,
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
import { useToast } from "@/hooks/use-toast";
import { useRouter } from "next/navigation";
import { useVantagem } from "@/context/VantagemContext";
import { createVantagem } from "@/services/vantagemService";
import { ROUTES } from "@/lib/constants";

const VantagemSchema = z.object({
    custoEmMoedas: z
        .preprocess((val) => parseInt(val as string, 10), z.number().positive({
            message: "O valor deve ser um número positivo.",
        })),
    descricao: z.string().min(10, { message: "Insira ao menos 10 caracteres." }),
    foto: z.string().min(1, { message: "Insira a URL da foto." }),
});

type VantagemFormValues = z.infer<typeof VantagemSchema>;

export default function VantagensForm() {
    const [loading, setLoading] = useState(false);
    const { toast } = useToast();
    const router = useRouter();
    const { adicionarVantagem } = useVantagem();

    const form = useForm<VantagemFormValues>({
        resolver: zodResolver(VantagemSchema),
        defaultValues: {
            custoEmMoedas: 0,
            descricao: "",
            foto: "",
        },
    });

    async function onSubmit(values: VantagemFormValues) {
        try {
            setLoading(true);
            const empresaId = localStorage.getItem('id');
            if (!empresaId) {
                throw new Error("ID da empresa não encontrado");
            }
            await createVantagem({
                ...values,
                custoEmMoedas: Number(values.custoEmMoedas),
                empresaId: Number(empresaId)
            });
            toast({
                title: "Vantagem criada com sucesso",
                description: "A vantagem foi adicionada ao sistema.",
            });
            setTimeout(() => {
                router.push(ROUTES.PAINEL_EMPRESAS);
            }, 1000);
        } catch (error) {
            toast({
                title: "Erro ao criar vantagem",
                description: "Houve um problema ao tentar criar a vantagem.",
            });
        } finally {
            setLoading(false);
        }
    }

    return (
        <Form {...form}>
            <form onSubmit={form.handleSubmit(onSubmit)} className="space-y-8">
                <div className="grid grid-cols-1 md:grid-cols-2 gap-4 p-10">
                    <FormField
                        control={form.control}
                        name="custoEmMoedas"
                        render={({ field }) => (
                            <FormItem>
                                <FormLabel htmlFor="custoEmMoedas">Custo em Moedas</FormLabel>
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
                                <FormLabel htmlFor="descricao">Descrição</FormLabel>
                                <FormControl>
                                    <Input {...field} />
                                </FormControl>
                                <FormMessage />
                            </FormItem>
                        )}
                    />

                    <FormField
                        control={form.control}
                        name="foto"
                        render={({ field }) => (
                            <FormItem>
                                <FormLabel htmlFor="foto">URL da Foto</FormLabel>
                                <FormControl>
                                    <Input {...field} />
                                </FormControl>
                                <FormMessage />
                            </FormItem>
                        )}
                    />
                </div>
                <div className="flex justify-center mb-10">
                    <Button type="submit" disabled={loading}>
                        {loading ? "Carregando..." : "Adicionar Vantagem"}
                    </Button>
                </div>
            </form>
        </Form>
    );
}