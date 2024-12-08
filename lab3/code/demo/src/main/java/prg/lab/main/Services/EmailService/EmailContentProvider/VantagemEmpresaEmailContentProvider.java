package prg.lab.main.Services.EmailService.EmailContentProvider;

public class VantagemEmpresaEmailContentProvider implements IEmailContentProvider {

    private final String nomeVantagem;
    private final Double quantidadeMoedas;
    private final String codigoCupom;
    private final String emailAluno;
    private final String nomeAluno;

    public VantagemEmpresaEmailContentProvider(String nomeVantagem, Double quantidadeMoedas, String codigoCupom, String emailAluno, String nomeAluno) {
        this.nomeVantagem = nomeVantagem;
        this.quantidadeMoedas = quantidadeMoedas;
        this.codigoCupom = codigoCupom;
        this.emailAluno = emailAluno;
        this.nomeAluno = nomeAluno;
    }

    @Override
    public String getSubject() {
        return "Vantagem resgatada!";
    }

    @Override
    public String getBody() {
        return "<h1>Parabéns!</h1><p>A vantagem " + nomeVantagem +
               " foi resgatada pelo aluno: " + nomeAluno +
               " com email: " + emailAluno + " por " + quantidadeMoedas + " moedas</p>" +
               "<p>Código do cupom: " + codigoCupom + "</p>";
    }
    
}
