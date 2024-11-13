package prg.lab.main.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import prg.lab.main.Models.Transacao;
import prg.lab.main.Repositories.TransacaoRepository;

@Service
public class TransacaoService {

    @Autowired
    private TransacaoRepository transacaoRepository;

    @Autowired
    private ProfessorService professorService;

    @Autowired
    private AlunoService alunoService;

    @Autowired
    private EmailService emailService;

    public Transacao getTransacaoById(Long id) {
        Optional<Transacao> transacao = transacaoRepository.findById(id);
        return transacao.orElseThrow(() -> new RuntimeException("Transacao não encontrada"));
    }

    @Transactional
    public Transacao premiarAluno(Transacao transacao) {
        if (transacao.getProfessor().getSaldoMoedas() < transacao.getQuantidade()) {
            throw new RuntimeException("Saldo do professor insuficiente para realizar a transação");
        }
        professorService.debitarMoedas(transacao.getProfessor().getId(), transacao.getQuantidade());
        alunoService.creditarMoedas(transacao.getAluno().getId(), transacao.getQuantidade());
        emailService.enviarBonusEmail(
                transacao.getAluno().getEmail(),
                transacao.getProfessor().getEmail(),
                transacao.getQuantidade(),
                transacao.getDescricao());
        return transacaoRepository.save(transacao);
    }

    @Transactional
    public void deleteTransacao(Long id) {
        transacaoRepository.deleteById(id);
    }

    // @Transactional
    // public Transacao updateTransacao(Transacao transacao){
    // Transacao newTransacao = getTransacaoById(transacao.getId());
    // newTransacao.setData(transacao.getData());
    // newTransacao.setQuantidade(transacao.getQuantidade());
    // return transacaoRepository.save(transacao);

    // }
}
