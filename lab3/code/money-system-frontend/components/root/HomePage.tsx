import Link from 'next/link'
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from "@/components/ui/card"
import { Button } from "@/components/ui/button"
import { ROUTES } from '@/lib/constants'
import Image from "next/image"

export default function HomePage() {
    return (
        <div className=" flex items-center justify-center p-4">
            <Card className="w-full max-w-4xl">
                <CardHeader className=" flex flex-col items-center">
                    <Image src="/logo.png" alt="Logo" width={150} height={150} />
                    <CardTitle className="text-3xl font-bold text-center">Sistema de Moedas Estudantil</CardTitle>
                    <CardDescription className="text-center text-lg">Escolha seu tipo de usuário para continuar</CardDescription>
                </CardHeader>
                <CardContent className="flex flex-col space-y-4">
                    <Link href={ROUTES.LOGIN_PROFESSORES} passHref>
                        <Button className="w-full h-24 text-lg" variant="outline">Professor</Button>
                    </Link>
                    <Link href={ROUTES.LOGIN_ALUNOS} passHref>
                        <Button className="w-full h-24 text-lg" variant="outline">Aluno</Button>
                    </Link>
                    <Link href={ROUTES.LOGIN_EMPRESAS} passHref>
                        <Button className="w-full h-24 text-lg" variant="outline">Empresa</Button>
                    </Link>
                </CardContent>

            </Card>
        </div>
    )
}