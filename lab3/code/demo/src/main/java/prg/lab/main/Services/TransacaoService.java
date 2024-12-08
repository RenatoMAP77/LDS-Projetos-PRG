package prg.lab.main.Services;

import jakarta.transaction.Transactional;
import prg.lab.main.Models.Aluno;
import prg.lab.main.Models.Cupom;
import prg.lab.main.Models.Transacao;
import prg.lab.main.Models.Vantagem;
import prg.lab.main.Repositories.TransacaoRepository;
import prg.lab.main.Services.EmailService.*;
import prg.lab.main.Util.DTOs.ResgatarVantagemDTO;
import prg.lab.main.Util.Enums.TransactionTypes;
import prg.lab.main.Services.EmailService.EmailContentProvider.*;
import prg.lab.main.Util.Email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class TransacaoService {

    @Autowired
    private TransacaoRepository transacaoRepository;

    @Autowired
    private ProfessorService professorService;

    @Autowired
    private AlunoService alunoService;

    @Autowired
    private CupomService cupomService;
    
    private EmailService emailService;

    @Autowired
    private VantagemService vantagemService;

    public Transacao getTransacaoById(Long id) {
        return transacaoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Transação não encontrada"));
    }

    @Transactional
    public Transacao premiarAluno(Transacao transacao) {
        validarSaldoProfessor(transacao.getProfessor().getSaldoMoedas(), transacao.getQuantidade());

        professorService.debitarMoedas(transacao.getProfessor().getId(), transacao.getQuantidade());
        alunoService.creditarMoedas(transacao.getAluno().getId(), transacao.getQuantidade());

        enviarEmailPremiacaoAluno(transacao);

        return transacaoRepository.save(transacao);
    }

    @Transactional
    public Transacao regatarVantagem(ResgatarVantagemDTO resgatarVantagemDTO) {
        Vantagem vantagem = vantagemService.getVantagemById(resgatarVantagemDTO.vantagemId());
        Aluno aluno = alunoService.getAlunoById(resgatarVantagemDTO.alunoId());

        validarSaldoAluno(aluno.getSaldoMoedas(), vantagem.getCustoEmMoedas());

        Transacao transacao = criarTransacaoTrocaVantagem(aluno, vantagem);

        alunoService.debitarMoedas(aluno.getId(), vantagem.getCustoEmMoedas());

        Cupom cupom = gerarCupom(vantagem, aluno);
        cupomService.createCupom(cupom);

        vantagem.getCupons().add(cupom);
        aluno.getCupons().add(cupom);

        enviarEmailsTrocaVantagem(aluno, vantagem, cupom);

        return transacaoRepository.save(transacao);
    }

    private void validarSaldoProfessor(double saldoProfessor, double quantidade) {
        if (saldoProfessor < quantidade) {
            throw new RuntimeException("Saldo do professor insuficiente para realizar a transação");
        }
    }

    private void validarSaldoAluno(Double saldoAluno, double custoVantagem) {
        if (saldoAluno == null || saldoAluno < custoVantagem) {
            throw new RuntimeException("Saldo do aluno insuficiente para resgatar a vantagem ou saldo é nulo");
        }
    }

    private Transacao criarTransacaoTrocaVantagem(Aluno aluno, Vantagem vantagem) {
        Transacao transacao = new Transacao();
        transacao.setQuantidade(vantagem.getCustoEmMoedas());
        transacao.setData(LocalDateTime.now());
        transacao.setTipo(TransactionTypes.TROCA_VANTAGEM);
        transacao.setAluno(aluno);
        transacao.setDescricao(vantagem.getDescricao());
        transacao.setEmpresa(vantagem.getEmpresa());
        return transacao;
    }

    private Cupom gerarCupom(Vantagem vantagem, Aluno aluno) {
        String codigoCupom = UUID.randomUUID().toString();
        return new Cupom(codigoCupom, vantagem.getEmpresa(), aluno, vantagem);
    }

    private void enviarEmailPremiacaoAluno(Transacao transacao) {
        IEmailContentProvider contentProvider = new BonusEmailContentProvider(
            transacao.getProfessor().getEmail(),
            transacao.getQuantidade(),
            transacao.getDescricao()
        );

        Email email = new Email();
        email.setTo(transacao.getAluno().getEmail());
        email.setSubject(contentProvider.getSubject());
        email.setBody(contentProvider.getBody());

        emailService.sendEmail(email);
    }

    private void enviarEmailsTrocaVantagem(Aluno aluno, Vantagem vantagem, Cupom cupom) {
        IEmailContentProvider alunoEmailContent = new VantagemEmailContentProvider(
            vantagem.getDescricao(),
            vantagem.getCustoEmMoedas(),
            cupom.getCodigo()
        );

        Email alunoEmail = new Email();
        alunoEmail.setTo(aluno.getEmail());
        alunoEmail.setSubject(alunoEmailContent.getSubject());
        alunoEmail.setBody(alunoEmailContent.getBody());

        emailService.sendEmail(alunoEmail);

        IEmailContentProvider empresaEmailContent = new VantagemEmpresaEmailContentProvider(
            vantagem.getDescricao(),
            vantagem.getCustoEmMoedas(),
            cupom.getCodigo(),
            aluno.getEmail(),
            aluno.getNome()
        );

        Email empresaEmail = new Email();
        empresaEmail.setTo(vantagem.getEmpresa().getEmail());
        empresaEmail.setSubject(empresaEmailContent.getSubject());
        empresaEmail.setBody(empresaEmailContent.getBody());

        emailService.sendEmail(empresaEmail);
    }

    @Transactional
    public void deleteTransacao(Long id) {
        transacaoRepository.deleteById(id);
    }

    public List<Transacao> history(Long id) {
        return transacaoRepository.findAllByAlunoId(id);
    }

    public List<Transacao> historyProfessor(Long id) {
        return transacaoRepository.findAllByProfessorId(id);
    }
}

