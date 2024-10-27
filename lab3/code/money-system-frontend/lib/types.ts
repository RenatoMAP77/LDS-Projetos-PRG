export enum TipoUsuario {
  ALUNO = "ALUNO",
  EMPRESA = "EMPRESA",
}
export enum TipoForm {
  ADICIONAR = "ADICIONAR",
  EDITAR = "EDITAR",
}

export enum TipoEntidade {
  ALUNO = "ALUNO",
  EMPRESA = "EMPRESA",
}

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
  instituicao? : Instituicao;
};

export type Instituicao = {
  id: string;
  nome: string;
  
};
