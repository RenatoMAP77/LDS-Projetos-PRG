import { Button } from "@/components/ui/button";
import {
    Card,
    CardContent,
    CardDescription,
    CardHeader,
    CardTitle,
} from "@/components/ui/card";
import { Input } from "@/components/ui/input";
import { zodResolver } from "@hookform/resolvers/zod";
import { useForm } from "react-hook-form";
import { z } from "zod";
import {
    Form,
    FormControl,
    FormField,
    FormItem,
    FormLabel,
    FormMessage,
} from "@/components/ui/form";
import { cadastrarCliente } from "@/services/authService";
import { Cliente } from "@/lib/utils";
import { useNavigate } from "react-router-dom";
import { useToast } from "@/hooks/use-toast"


const formSchema = z.object({
    email: z.string().min(2).max(50),
    senha: z.string().min(2).max(50),
    nome: z.string().min(2).max(50),
    cpf: z.string().min(2).max(50),
    rg: z.string().min(2).max(50),
    pais: z.string().min(2).max(50),
    estado: z.string().min(2).max(50),
    cidade: z.string().min(2).max(50),
    bairro: z.string().min(2).max(50),
    rua: z.string().min(2).max(50),
    numero: z.string().min(2).max(50),
    cep: z.string().min(2).max(50),
    profissao: z.string().min(2).max(50),
    rendimento: z.string().min(2).max(50),
    empregadores: z.string().optional(),
});

export function RegisterPage() {
    const { toast } = useToast()
    const navigate = useNavigate();
    const form = useForm<z.infer<typeof formSchema>>({
        resolver: zodResolver(formSchema),
        defaultValues: {
            email: "",
            senha: "",
            nome: "",
            cpf: "",
            rg: "",
            pais: "",
            estado: "",
            cidade: "",
            bairro: "",
            rua: "",
            numero: "",
            cep: "",
            profissao: "",
            rendimento: "",
            empregadores: "",
        },
    });

    function onSubmit(values: z.infer<typeof formSchema>) {
        const empregadoresArray = values.empregadores?.split(",").map(emp => emp.trim()) || [];

        const clienteData: Cliente = {
            ...values,
            empregadores: empregadoresArray,
        };

        console.log(clienteData, "data");

        cadastrarCliente(clienteData)
            .then(() => {
                toast({
                    title: "Cadastro realizado com sucesso!",
                    description:"Acesse o sistema."
                })
                navigate('/login');
            })
            .catch((error) => {
                console.log(error);
                toast({
                    title: "Erro ao cadastrar!",
                    description:"Tente novamente.",
                    variant: "destructive"
                })
            });
    }

    return (
        <div className="flex justify-center items-center py-4 bg-gray-50">
            <Card className="shadow-lg p-10 w-full max-w-4xl">
                <CardHeader>
                    <CardTitle className="text-2xl">Registre-se</CardTitle>
                    <CardDescription>
                        Entre com suas informações para criar uma conta
                    </CardDescription>
                </CardHeader>
                <CardContent>
                    <Form {...form}>
                        <form onSubmit={form.handleSubmit(onSubmit)} className="grid grid-cols-1 md:grid-cols-2 gap-4">
                            <FormField
                                control={form.control}
                                name="email"
                                render={({ field }) => (
                                    <FormItem>
                                        <FormLabel>Email</FormLabel>
                                        <FormControl>
                                            <Input {...field} />
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
                                            <Input {...field} />
                                        </FormControl>
                                        <FormMessage />
                                    </FormItem>
                                )}
                            />
                            <FormField
                                control={form.control}
                                name="nome"
                                render={({ field }) => (
                                    <FormItem>
                                        <FormLabel>Nome</FormLabel>
                                        <FormControl>
                                            <Input {...field} />
                                        </FormControl>
                                        <FormMessage />
                                    </FormItem>
                                )}
                            />
                            <FormField
                                control={form.control}
                                name="cpf"
                                render={({ field }) => (
                                    <FormItem>
                                        <FormLabel>CPF</FormLabel>
                                        <FormControl>
                                            <Input {...field} />
                                        </FormControl>
                                        <FormMessage />
                                    </FormItem>
                                )}
                            />
                            <FormField
                                control={form.control}
                                name="rg"
                                render={({ field }) => (
                                    <FormItem>
                                        <FormLabel>RG</FormLabel>
                                        <FormControl>
                                            <Input {...field} />
                                        </FormControl>
                                        <FormMessage />
                                    </FormItem>
                                )}
                            />
                            <FormField
                                control={form.control}
                                name="pais"
                                render={({ field }) => (
                                    <FormItem>
                                        <FormLabel>País</FormLabel>
                                        <FormControl>
                                            <Input {...field} />
                                        </FormControl>
                                        <FormMessage />
                                    </FormItem>
                                )}
                            />
                            <FormField
                                control={form.control}
                                name="estado"
                                render={({ field }) => (
                                    <FormItem>
                                        <FormLabel>Estado</FormLabel>
                                        <FormControl>
                                            <Input {...field} />
                                        </FormControl>
                                        <FormMessage />
                                    </FormItem>
                                )}
                            />
                            <FormField
                                control={form.control}
                                name="cidade"
                                render={({ field }) => (
                                    <FormItem>
                                        <FormLabel>Cidade</FormLabel>
                                        <FormControl>
                                            <Input {...field} />
                                        </FormControl>
                                        <FormMessage />
                                    </FormItem>
                                )}
                            />
                            <FormField
                                control={form.control}
                                name="bairro"
                                render={({ field }) => (
                                    <FormItem>
                                        <FormLabel>Bairro</FormLabel>
                                        <FormControl>
                                            <Input {...field} />
                                        </FormControl>
                                        <FormMessage />
                                    </FormItem>
                                )}
                            />
                            <FormField
                                control={form.control}
                                name="rua"
                                render={({ field }) => (
                                    <FormItem>
                                        <FormLabel>Rua</FormLabel>
                                        <FormControl>
                                            <Input {...field} />
                                        </FormControl>
                                        <FormMessage />
                                    </FormItem>
                                )}
                            />
                            <FormField
                                control={form.control}
                                name="numero"
                                render={({ field }) => (
                                    <FormItem>
                                        <FormLabel>Número</FormLabel>
                                        <FormControl>
                                            <Input {...field} />
                                        </FormControl>
                                        <FormMessage />
                                    </FormItem>
                                )}
                            />
                            <FormField
                                control={form.control}
                                name="cep"
                                render={({ field }) => (
                                    <FormItem>
                                        <FormLabel>CEP</FormLabel>
                                        <FormControl>
                                            <Input {...field} />
                                        </FormControl>
                                        <FormMessage />
                                    </FormItem>
                                )}
                            />
                            <FormField
                                control={form.control}
                                name="profissao"
                                render={({ field }) => (
                                    <FormItem>
                                        <FormLabel>Profissão</FormLabel>
                                        <FormControl>
                                            <Input {...field} />
                                        </FormControl>
                                        <FormMessage />
                                    </FormItem>
                                )}
                            />
                            <FormField
                                control={form.control}
                                name="rendimento"
                                render={({ field }) => (
                                    <FormItem>
                                        <FormLabel>Rendimento</FormLabel>
                                        <FormControl>
                                            <Input {...field} />
                                        </FormControl>
                                        <FormMessage />
                                    </FormItem>
                                )}
                            />
                            <FormField
                                control={form.control}
                                name="empregadores"
                                render={({ field }) => (
                                    <FormItem>
                                        <FormLabel>Empregadores (separados por vírgulas)</FormLabel>
                                        <FormControl>
                                            <Input {...field} />
                                        </FormControl>
                                        <FormMessage />
                                    </FormItem>
                                )}
                            />
                            <div className="flex flex-col space-y-2 col-span-1 md:col-span-2">
                                <Button type="submit" variant="default">Realizar cadastro</Button>
                                <Button onClick={() => navigate('/')} variant="outline">Voltar</Button>
                            </div>
                        </form>
                    </Form>
                </CardContent>
            </Card>
        </div>
    );
}
