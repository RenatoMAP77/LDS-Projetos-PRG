public class Usuario {
    private long id ;
    private String senha;
    private String nome;

   public void  Cadastrar(long id, String senha, String nome){
        //...
    }

   
    public String getNome() {
        return nome;
    }
    public String getSenha() {
        return senha;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
}
