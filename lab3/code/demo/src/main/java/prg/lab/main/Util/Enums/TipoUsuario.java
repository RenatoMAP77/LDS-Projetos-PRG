package prg.lab.main.Util.Enums;

public enum TipoUsuario {
    ALUNO,
    PROFESSOR,
    EMPRESA;

    public static TipoUsuario fromString(String text) {
        for (TipoUsuario b : TipoUsuario.values()) {
            if (b.toString().equalsIgnoreCase(text)) {
                return b;
            }
        }
        return null;
    }

}
