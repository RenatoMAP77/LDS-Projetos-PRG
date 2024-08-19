public class Usuario {
    static private long id = 0;
    private String senha;
    private String nome;




   public void  Cadastrar(long id, String senha, String nome){
        //...
        id = id + 1; 
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
