import { useNavigate } from "react-router-dom";
import { Button } from "@/components/ui/button";
import { Card, CardHeader, CardTitle, CardContent } from "@/components/ui/card";
import { Badge } from "@/components/ui/badge";

export function HomePage() {
  const navigate = useNavigate();

  const handleLogin = () => {
    navigate("/login");
  };

  const handleRegister = () => {
    navigate("/registro");
  };

  return (
    <div className="flex justify-center items-center h-screen bg-gray-50">
      <Card className="shadow-lg p-10 w-full max-w-md">
        <CardHeader className="text-center flex justify-center">
          <Badge variant="outline" className="mx-auto">
            Bem-vindo
          </Badge>
          <CardTitle className="text-2xl font-bold">Aluguel de automóvel</CardTitle>
        </CardHeader>
        <CardContent className="space-y-4 text-center">
          <p className="text-gray-600">
            Entre ou crie uma nova conta para acessar o sistema.
          </p>
          <div className="flex flex-col space-y-3">
            <Button onClick={handleLogin} variant="default" size="lg">
              Login
            </Button>
            <Button onClick={handleRegister} variant="outline" size="lg">
              Cadastro
            </Button>
          </div>
        </CardContent>
      </Card>
    </div>
  );
}

export default HomePage;
