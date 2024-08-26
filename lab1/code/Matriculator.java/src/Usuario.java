public abstract class Usuario {
    private static  long id = 0;
    private String senha;
    private String nome;

    public void Cadastrar(String senha, String nome) {
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
