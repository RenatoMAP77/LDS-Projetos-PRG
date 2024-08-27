import java.io.Serializable;

public abstract class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    private static long currentId = 0;
    private long id;
    private String senha;
    private String nome;

    public void Cadastrar(String nome, String senha) {
        this.nome = nome;
        this.senha = senha;
        currentId++;
        id = currentId;
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
    public void setId(long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
}
