package prg.lab.main.Util.DTOs;

public record AlunoDTO(String cpf, String rg, String endereco, String curso, int saldoMoedas, Long instituicaoId, String nome, String email, String senha) {
    
    public AlunoDTO {
        if (cpf == null || cpf.isBlank()) {
            throw new IllegalArgumentException("CPF não pode ser nulo ou vazio");
        }
        if (rg == null || rg.isBlank()) {
            throw new IllegalArgumentException("RG não pode ser nulo ou vazio");
        }
        if (endereco == null || endereco.isBlank()) {
            throw new IllegalArgumentException("Endereço não pode ser nulo ou vazio");
        }
        if (curso == null || curso.isBlank()) {
            throw new IllegalArgumentException("Curso não pode ser nulo ou vazio");
        }
        if (saldoMoedas < 0) {
            throw new IllegalArgumentException("Saldo de moedas não pode ser negativo");
        }
        if (instituicaoId == null) {
            throw new IllegalArgumentException("ID da instituição não pode ser nulo");
        }
    }
    
}
