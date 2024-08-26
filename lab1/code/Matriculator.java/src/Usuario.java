import java.io.Serializable;

public abstract class Usuario implements Serializable {
    Long serialVersionUID = 1L;
    private static  long id = 0;
    private String senha;
    private String nome;

    public void Cadastrar(String nome, String senha) {
        this.nome = nome;
        this.senha = senha;
        id = id + 1;
    }

    public String getNome() {
        return nome;
    }

    public String getSenha() {
        return senha;
    }

    public long getId() {
        return id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
