"use client"
import { Button } from "@/components/ui/button"
import {
    Card,
    CardContent,
    CardDescription,
    CardHeader,
    CardTitle,
} from "@/components/ui/card"
import {
    Form,
    FormControl,
    FormDescription,
    FormField,
    FormItem,
    FormLabel,
    FormMessage,
} from "@/components/ui/form"
import { Input } from "@/components/ui/input"
import { zodResolver } from "@hookform/resolvers/zod"
import { useForm } from "react-hook-form"
import { z } from "zod"
import { login } from "@/services/authService"
import { useNavigate } from "react-router-dom"
import { useToast } from "@/hooks/use-toast"

const formSchema = z.object({
    email: z.string().min(2).max(50),
    senha: z.string().min(2).max(50),
})


export function LoginPage() {
    const { toast } = useToast()
    const navigate = useNavigate();
    const form = useForm<z.infer<typeof formSchema>>({
        resolver: zodResolver(formSchema),
        defaultValues: {
            email: "",
            senha: "",
        },
    })

    function onSubmit(values: z.infer<typeof formSchema>) {


        login(values.email, values.senha)
            .then((data) => {
                console.log(data)
                localStorage.setItem("user", JSON.stringify(data));
                toast({
                    title: "Bem-vindo ao sistema!",
                    description: "Você já pode alugar um veículo"
                })
                navigate('/sucesso')
            })
            .catch((error) => {
                toast({
                    title: "Erro ao logar!",
                    description:"Tente novamente.",
                    variant: "destructive"
                })
                console.error(error)
            })
    }


    return (
        <div className="flex justify-center items-center h-screen bg-gray-50">

        <Card  className="shadow-lg p-10 w-full max-w-md">
            <CardHeader>
                <CardTitle className="text-2xl">Login</CardTitle>
                <CardDescription>
                    Entre com seu email abaixo para acessar o sistema
                </CardDescription>
            </CardHeader>
            <CardContent>
                <Form {...form}>
                    <form onSubmit={form.handleSubmit(onSubmit)} className="space-y-8">
                        <FormField
                            control={form.control}
                            name="email"
                            render={({ field }) => (
                                <FormItem>
                                    <FormLabel>Email</FormLabel>
                                    <FormControl>
                                        <Input {...field} />
                                    </FormControl>
                                    <FormDescription>
                                        Esse é seu email cadastrado
                                    </FormDescription>
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
                                        <Input {...field} />
                                    </FormControl>
                                    <FormMessage />
                                </FormItem>
                            )}
                        />
                        <div className="flex flex-col space-y-2 w-full">
                            <Button variant="default" type="submit">Acessar sistema</Button>
                            <Button variant="outline" onClick={() => navigate('/')} >Voltar</Button>
                        </div>
                    </form>
                </Form>
            </CardContent>
        </Card>
        </div>
    )
}
