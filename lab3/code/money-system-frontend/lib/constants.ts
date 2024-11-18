export const ROUTES = {
    HOME: "/",
    LOGIN_ALUNOS: "/login/alunos",
    CADASTRAR_ALUNOS: "/cadastrar/alunos/adicionar",
    LOGIN_EMPRESAS: "/login/empresas",
    CADASTRAR_EMPRESAS: "/cadastrar/empresas/adicionar",
    LOGIN_PROFESSORES: "/login/professores",
    PAINEL_ALUNOS: "/painel/alunos",
    PAINEL_EMPRESAS: "/painel/empresas",
    PAINEL_PROFESSORES: "/painel/professores",
    PAINEL_EMPRESAS_VANTAGENS_ADICIONAR: "/painel/empresas/vantagens/adicionar",
    PAINEL_EMPRESAS_VANTAGENS_LISTAR: "/painel/empresas/vantagens/listar",
    PAINEL_ALUNOS_VANTAGENS_RESGATAR: "/painel/alunos/vantagens",
    PAINEL_ALUNOS_RECIBOS: "/painel/alunos/recibos",
    PAINEL_PROFESSORES_RECIBOS: "/painel/professores/recibos",
    PAINEL_PROFESSORES_MOEDAS: "/painel/professores/moedas",

    ADMIN_ORDER: (id: number) => `/admin/pedidos/${id}`,
};