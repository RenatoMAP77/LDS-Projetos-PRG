"use client"
import { useState } from 'react'
import { Card, CardContent, CardDescription, CardFooter, CardHeader, CardTitle } from "@/components/ui/card"
import { Button } from "@/components/ui/button"

interface Instituicao {
    id: number;
    nome: string;
}

interface Usuario {
    id: number;
    nome: string;
    email: string;
    cpf: string;
    tipoUsuario: string;
    instituicao: Instituicao;
}

interface Aluno extends Usuario {
    rg: string;
    endereco: string;
    curso: string;
    saldoMoedas: number;
}

interface Professor extends Usuario {
    departamento: string;
    saldoMoedas: number;
}

interface ReciboData {
    id: number;
    data: string;
    descricao: string;
    quantidade: number;
    tipo: string;
    aluno: Aluno;
    empresa: null;
    professor: Professor;
}

export default function Recibo({ data }: { data: ReciboData }) {
    const [showDetails, setShowDetails] = useState(false)
    return (
        <Card className="w-full max-w-3xl mx-auto my-8">
            <CardHeader>
                <CardTitle className="text-2xl font-bold">Recibo de Transação</CardTitle>
                <CardDescription>Detalhes da movimentação de moedas</CardDescription>
            </CardHeader>
            <CardContent className="space-y-4">
                <div className="grid grid-cols-2 gap-4">
                    <div>
                        <h3 className="font-semibold">ID da Transação:</h3>
                        <p>{data.id}</p>
                    </div>
                    <div>
                        <h3 className="font-semibold">Data e Hora:</h3>
                        <p>data.data</p>
                    </div>
                    <div>
                        <h3 className="font-semibold">Tipo de Transação:</h3>
                        <p>{data.tipo}</p>
                    </div>
                    <div>
                        <h3 className="font-semibold">Quantidade de Moedas:</h3>
                        <p>{data.quantidade}</p>
                    </div>
                </div>

                <div>
                    <h3 className="font-semibold">Descrição:</h3>
                    <p>{data.descricao}</p>
                </div>

                <div className="space-y-2">
                    <h3 className="font-semibold">Aluno:</h3>
                    <p>Nome: {data.aluno.nome}</p>
                    <p>Email: {data.aluno.email}</p>
                    <p>CPF: {data.aluno.cpf}</p>
                    <p>Curso: {data.aluno.curso}</p>
                    <p>Saldo de Moedas: {data.aluno.saldoMoedas}</p>
                </div>

                <div className="space-y-2">
                    <h3 className="font-semibold">Professor:</h3>
                    <p>Nome: {data.professor.nome}</p>
                    <p>Email: {data.professor.email}</p>
                    <p>Departamento: {data.professor.departamento}</p>
                </div>

                <Button
                    variant="outline"
                    onClick={() => setShowDetails(!showDetails)}
                >
                    {showDetails ? 'Ocultar Detalhes' : 'Mostrar Mais Detalhes'}
                </Button>

                {showDetails && (
                    <div className="space-y-4">
                        <div className="space-y-2">
                            <h3 className="font-semibold">Detalhes do Aluno:</h3>
                            <p>RG: {data.aluno.rg}</p>
                            <p>Endereço: {data.aluno.endereco}</p>
                        </div>
                        <div className="space-y-2">
                            <h3 className="font-semibold">Detalhes do Professor:</h3>
                            <p>CPF: {data.professor.cpf}</p>
                            <p>Saldo de Moedas: {data.professor.saldoMoedas}</p>
                        </div>
                        <div className="space-y-2">
                            <h3 className="font-semibold">Instituição:</h3>
                            <p>ID: {data.aluno.instituicao.id}</p>
                            <p>Nome: {data.aluno.instituicao.nome}</p>
                        </div>
                    </div>
                )}
            </CardContent>
        </Card>
    )
}