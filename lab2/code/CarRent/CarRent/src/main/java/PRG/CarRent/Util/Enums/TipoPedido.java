package PRG.CarRent.Util.Enums; 

public enum TipoPedido {

    CREDIARIO("Crediário"),
    NORMAL("Normal");

    private String descricao;

    TipoPedido(String descricao) {
        this.descricao = descricao;
    }
    public String getDescricao() {
        return descricao;
    }
    
}
