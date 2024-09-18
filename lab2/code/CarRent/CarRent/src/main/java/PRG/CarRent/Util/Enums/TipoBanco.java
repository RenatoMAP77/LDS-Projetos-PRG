package PRG.CarRent.Util.Enums; 

public enum TipoBanco {
    FISICO("Fisico"),
    ONLINE("Online");

    private String tipo;

    TipoBanco(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }
    
}
