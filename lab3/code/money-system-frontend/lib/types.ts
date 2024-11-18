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

export type Professor = {
    id: number,
    nome: string,
    email: string,
    senha: string,
    cpf: string,
    departamento: string,
    saldoMoedas: number,
    tipoUsuario: TipoUsuario,
    instituicao: Instituicao
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
    instituicao?: Instituicao;
};

export type Instituicao = {
    id: string;
    nome: string;
};

export type VantagemCreate = {
    custoEmMoedas: number;
    descricao: string;
    empresaId: string | undefined;
};

export type VantagemRead = {
    id: number;
    descricao: string;
    custoEmMoedas: number;
    empresa: Empresa;
    cupons: any[];
    foto: string;
};


export type LoginResponse = {
    tipoUsuario: "ALUNO" | "EMPRESA" | "PROFESSOR";
    id: number;
}
