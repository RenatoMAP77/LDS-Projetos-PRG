public enum StatusPedido {
    
    PENDENTE("Pendente"),
    APROVADO("Aprovado"),
    REPROVADO("Reprovado")//,
    // CANCELADO("Cancelado"),
    // FINALIZADO("Finalizado")
    ;

    private String status;

    StatusPedido(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
