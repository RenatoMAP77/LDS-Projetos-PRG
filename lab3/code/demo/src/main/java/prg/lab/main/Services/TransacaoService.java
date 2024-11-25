package prg.lab.main.Services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import prg.lab.main.Models.Aluno;
import prg.lab.main.Models.Cupom;
import prg.lab.main.Models.Transacao;
import prg.lab.main.Models.Vantagem;
import prg.lab.main.Repositories.TransacaoRepository;
import prg.lab.main.Util.DTOs.ResgatarVantagemDTO;
import prg.lab.main.Util.Enums.TransactionTypes;

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

    @Autowired
    private EmailService emailService;

    @Autowired
    private VantagemService vantagemService;

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

    public Transacao regatarVantagem(ResgatarVantagemDTO resgatarVantagemDTO) {
        // Buscar a vantagem usando o ID do DTO
        Vantagem vantagem = vantagemService.getVantagemById(resgatarVantagemDTO.vantagemId());
        double valor = (double) vantagem.getCustoEmMoedas();

        // Buscar o aluno usando o ID do DTO
        Aluno aluno = alunoService.getAlunoById(resgatarVantagemDTO.alunoId());
        Double saldo = aluno.getSaldoMoedas();

        // Verificar se o saldo do aluno é suficiente
        if (saldo == null || saldo < valor) {
            throw new RuntimeException("Saldo do aluno insuficiente para resgatar a vantagem ou saldo é nulo");
        }

        if (saldo == 0) {
            throw new RuntimeException("Aluno sem saldo");
        }

        // Criar a transação
        Transacao transacao = new Transacao();
        transacao.setQuantidade(valor);
        transacao.setData(LocalDateTime.now());
        transacao.setTipo(TransactionTypes.TROCA_VANTAGEM);
        transacao.setAluno(aluno);
        transacao.setDescricao(vantagem.getDescricao());
        transacao.setEmpresa(vantagem.getEmpresa());

        // Debitar as moedas do aluno
        alunoService.debitarMoedas(aluno.getId(), valor);

        // Gerar o código do cupom
        String codigoCupom = UUID.randomUUID().toString();
        Cupom cupom = new Cupom(codigoCupom, vantagem.getEmpresa(), aluno, vantagem);

        // Criar o cupom
        cupomService.createCupom(cupom);

        // Adicionar o cupom à lista de cupons da vantagem
        vantagem.getCupons().add(cupom);

        aluno.getCupons().add(cupom);

        // Enviar email
        emailService.enviarVantagemEmail(
                aluno.getEmail(),
                vantagem.getDescricao(),
                valor,
                codigoCupom);

        emailService.enviarVantagemEmailEmpresa(
                vantagem.getEmpresa().getEmail(),
                vantagem.getDescricao(),
                valor,
                codigoCupom,
                aluno.getEmail(),
                aluno.getNome());

        // Salvar a transação no repositório
        return transacaoRepository.save(transacao);
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

    // @Transactional
    // public Transacao updateTransacao(Transacao transacao){
    // Transacao newTransacao = getTransacaoById(transacao.getId());
    // newTransacao.setData(transacao.getData());
    // newTransacao.setQuantidade(transacao.getQuantidade());
    // return transacaoRepository.save(transacao);

    // }
}
