package prg.lab.main.Util.Enums;

public enum TransactionTypes {
    PREMIACAO_MOEDAS,
    TROCA_VANTAGEM;

    public static TransactionTypes fromString(String text) {
        for (TransactionTypes b : TransactionTypes.values()) {
            if (b.toString().equalsIgnoreCase(text)) {
                return b;
            }
        }
        return null;
    }
}
