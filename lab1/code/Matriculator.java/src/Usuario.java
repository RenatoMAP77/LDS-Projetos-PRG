public abstract class Usuario {
    private static  long id = 0;
    private String senha;
    private String nome;
    private int permissao;

    public void Cadastrar(String senha, String nome, int permissao) {
        this.nome = nome;
        this.senha = senha;
        id = id + 1;
        this.permissao = permissao;
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

    public int getPermissao(){
        return this.permissao;
    }
}
