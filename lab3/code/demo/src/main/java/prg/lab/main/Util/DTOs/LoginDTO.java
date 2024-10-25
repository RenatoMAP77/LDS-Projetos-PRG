package prg.lab.main.Util.DTOs;

public record LoginDTO(String email, String senha) {
    
    public LoginDTO {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email não pode ser nulo ou vazio");
        }
        if (senha == null || senha.isBlank()) {
            throw new IllegalArgumentException("Senha não pode ser nula ou vazia");
        }
    }
}
