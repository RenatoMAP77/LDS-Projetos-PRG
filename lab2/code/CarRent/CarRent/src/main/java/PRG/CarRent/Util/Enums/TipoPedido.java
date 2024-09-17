public enum TipoPedido {

    CREDIARIO("Crediário"),
    NORMAL("Normal");

    private String descricao;

    TipoPedido(String descricao) {
        this.descricao = descricao;
    }
    
}
