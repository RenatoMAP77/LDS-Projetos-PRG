package prg.lab.main.Services.EmailService.EmailContentProvider;

public class BonusEmailContentProvider implements IEmailContentProvider {

    private final String nomeProfessor;
    private final Double quantidadeMoedas;
    private final String descricao;

    public BonusEmailContentProvider(String nomeProfessor, Double quantidadeMoedas, String descricao) {
        this.nomeProfessor = nomeProfessor;
        this.quantidadeMoedas = quantidadeMoedas;
        this.descricao = descricao;
    }

    @Override
    public String getSubject() {
        return "Você foi premiado!";
    }

    @Override
    public String getBody() {
        return "<h1>Parabéns!</h1><p>Você foi premiado com " + quantidadeMoedas +
               " moedas pelo professor " + nomeProfessor + "</p>" +
               "<p>Descrição: " + descricao + "</p>";
    }
    
}
