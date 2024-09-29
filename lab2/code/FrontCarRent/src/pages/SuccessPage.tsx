import { Badge } from "@/components/ui/badge";
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card";
import { useEffect } from "react";
import { useNavigate } from "react-router-dom";

export function SuccessPage() {
    const navigate = useNavigate();

    useEffect(() => {
      const user = localStorage.getItem("user");
      if (!user) {
        navigate("/login");
      }
    }, [navigate]);
  
    const user = JSON.parse(localStorage.getItem("user") || "{}");
  

  return (
    <div className="flex justify-center items-center h-screen bg-gray-50">
      <Card className="shadow-lg p-10 w-full max-w-md">
        <CardHeader className="text-center">
          <Badge variant="outline" className="mx-auto">
            Bem-vindo
          </Badge>
          <CardTitle className="text-2xl font-bold">Dados do Cliente</CardTitle>
        </CardHeader>
        <CardContent className="space-y-4">
          <div className="flex flex-col">
            <div className="flex justify-between">
              <span className="font-medium">Nome:</span>
              <span>{user.nome}</span>
            </div>
            <div className="flex justify-between">
              <span className="font-medium">Email:</span>
              <span>{user.email}</span>
            </div>
            <div className="flex justify-between">
              <span className="font-medium">CPF:</span>
              <span>{user.cpf}</span>
            </div>
            <div className="flex justify-between">
              <span className="font-medium">RG:</span>
              <span>{user.rg}</span>
            </div>
            
            <div className="flex justify-between">
              <span className="font-medium">Profissão:</span>
              <span>{user.profissao}</span>
            </div>
            <div className="flex justify-between">
              <span className="font-medium">Rendimento:</span>
              <span>{user.rendimento}</span>
            </div>
            <div className="flex justify-between">
              <span className="font-medium">Empregadores:</span>
              <span>{user.empregadores.join(", ") || "Nenhum"}</span>
            </div>
          </div>
        </CardContent>
      </Card>
    </div>
  );
}

export default SuccessPage;
