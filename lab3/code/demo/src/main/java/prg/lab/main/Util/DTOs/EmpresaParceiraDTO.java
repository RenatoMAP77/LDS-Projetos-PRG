package prg.lab.main.Util.DTOs;

public record EmpresaParceiraDTO(String cnpj, String nome, String email, String senha, String descricao) {
        
        public EmpresaParceiraDTO {
            if (cnpj == null || cnpj.isBlank()) {
                throw new IllegalArgumentException("CNPJ não pode ser nulo ou vazio");
            }
            if (nome == null || nome.isBlank()) {
                throw new IllegalArgumentException("Nome não pode ser nulo ou vazio");
            }
            if (email == null || email.isBlank()) {
                throw new IllegalArgumentException("Email não pode ser nulo ou vazio");
            }
            if (senha == null || senha.isBlank()) {
                throw new IllegalArgumentException("Senha não pode ser nula ou vazia");
            }
            if (descricao == null || descricao.isBlank()) {
                throw new IllegalArgumentException("Descrição não pode ser nula ou vazia");
            }
        }
}
