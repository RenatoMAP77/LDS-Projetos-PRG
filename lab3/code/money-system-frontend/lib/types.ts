export type tipoUsuario = "EMPRESA" | "ALUNO";

export type Empresa = {
  id?: string | undefined;
  cnpj: string;
  descricao: string;
  email: string;
  senha: string;
  nome: string;
};

export type Aluno = {
  id?: string | undefined;
  cpf: string;
  rg: string;
  endereco: string;
  curso: string;
  saldoMoedas: string;
  instituicaoId: string;
  nome: string;
  email: string;
  senha: string;
};
