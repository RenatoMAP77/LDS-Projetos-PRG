'use client'

import { useState, useEffect } from 'react'
import { useSearchParams } from 'next/navigation'
import Link from 'next/link'
import { zodResolver } from "@hookform/resolvers/zod"
import { useForm } from "react-hook-form"
import * as z from "zod"
import { Card, CardContent, CardDescription, CardFooter, CardHeader, CardTitle } from "@/components/ui/card"
import { Button } from "@/components/ui/button"
import {
    Form,
    FormControl,
    FormField,
    FormItem,
    FormLabel,
    FormMessage,
} from "@/components/ui/form"
import { Input } from "@/components/ui/input"
import { ROUTES } from '@/lib/constants'
import { loginEmpresa } from '@/services/loginService'
import { useRouter } from 'next/navigation'

const formSchema = z.object({
    email: z.string().email({ message: "Email inválido" }),
    senha: z.string().min(6, { message: "A senha deve ter pelo menos 6 caracteres" }),
})

export default function loginEmpresas() {
    const searchParams = useSearchParams()
    const router = useRouter();
    const [userType, setUserType] = useState('')

    useEffect(() => {
        const type = searchParams.get('type')
        setUserType(type || '')
    }, [searchParams])

    const form = useForm<z.infer<typeof formSchema>>({
        resolver: zodResolver(formSchema),
        defaultValues: {
            email: "",
            senha: "",
        },
    })

    async function onSubmit(values: z.infer<typeof formSchema>) {
        const data = {
            email: values.email,
            senha: values.senha
        }
        try {
            const response = await loginEmpresa(data);
            localStorage.setItem("type", response.tipoUsuario);
            localStorage.setItem("id", response.id.toString());
            router.push(ROUTES.PAINEL_EMPRESAS);
        } catch (error) {
            console.error("Login failed:", error)
        }
    }

    return (
        <div className="min-h-screen flex items-center justify-center p-4">
            <Card className="w-full max-w-md">
                <CardHeader>
                    <CardTitle className="text-2xl font-bold text-center">Login - Empresas</CardTitle>
                    <CardDescription className="text-center">Entre com suas credenciais para acessar o sistema</CardDescription>
                </CardHeader>
                <Form {...form}>
                    <form onSubmit={form.handleSubmit(onSubmit)}>
                        <CardContent className="space-y-4">
                            <FormField
                                control={form.control}
                                name="email"
                                render={({ field }) => (
                                    <FormItem>
                                        <FormLabel>Email</FormLabel>
                                        <FormControl>
                                            <Input type="email" {...field} />
                                        </FormControl>
                                        <FormMessage />
                                    </FormItem>
                                )}
                            />
                            <FormField
                                control={form.control}
                                name="senha"
                                render={({ field }) => (
                                    <FormItem>
                                        <FormLabel>Senha</FormLabel>
                                        <FormControl>
                                            <Input type="password" {...field} />
                                        </FormControl>
                                        <FormMessage />
                                    </FormItem>
                                )}
                            />
                        </CardContent>
                        <CardFooter className="flex flex-col space-y-4">
                            <Link href={ROUTES.HOME} className="border w-full rounded-md text-center p-1">Voltar</Link>
                            <Button type="submit" className="w-full">Entrar</Button>
                            <div className="text-center text-sm">
                                Não tem uma conta?{' '}
                                <Link href={ROUTES.CADASTRAR_EMPRESAS} className="text-blue-600 hover:underline">
                                    Cadastre-se
                                </Link>
                            </div>
                        </CardFooter>
                    </form>
                </Form>
            </Card>
        </div>
    )
}