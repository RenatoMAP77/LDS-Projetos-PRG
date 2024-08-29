import java.io.Serializable;

public abstract class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    private static long currentId = 0;
    private long id;
    private String senha;
    private String nome;

    public void Cadastrar(String nome, String senha) {
        try {
            if (nome == null || senha == null) {
                throw new IllegalArgumentException("Nome e senha não podem ser nulos");
            }
            this.nome = nome;
            this.senha = senha;
            currentId++;
            id = currentId;
        } catch (Exception e) {
            System.err.println("Erro ao cadastrar usuário: " + e.getMessage());
            e.printStackTrace();
        }
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
