package PRG.CarRent.Util.Enums;

public enum TipoProprietario {
    
    EMPRESA("Empresa"),
    CLIENTE("Cliente");

    private String nome;

    TipoProprietario(String nome) {
        this.nome = nome;
    }
    public String getNome() {
        return nome;
    }
    
}
