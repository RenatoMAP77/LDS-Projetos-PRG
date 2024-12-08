package prg.lab.main.Services.EmailService.EmailContentProvider;

public class VantagemEmailContentProvider implements IEmailContentProvider {

    private final String nomeVantagem;
    private final Double quantidadeMoedas;
    private final String codigoCupom;

    public VantagemEmailContentProvider(String nomeVantagem, Double quantidadeMoedas, String codigoCupom) {
        this.nomeVantagem = nomeVantagem;
        this.quantidadeMoedas = quantidadeMoedas;
        this.codigoCupom = codigoCupom;
    }

    @Override
    public String getSubject() {
        return "Você resgatou uma vantagem!";
    }

    @Override
    public String getBody() {
        return "<h1>Parabéns!</h1><p>Você resgatou a vantagem " + nomeVantagem +
               " por " + quantidadeMoedas + " moedas</p>" +
               "<p>Seu código de cupom é: " + codigoCupom + "</p>";
    }
    
}
