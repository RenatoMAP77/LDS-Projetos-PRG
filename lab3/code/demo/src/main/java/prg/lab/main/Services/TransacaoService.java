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

    public Transacao getTransacaoById(Long id) {
        Optional<Transacao> transacao = transacaoRepository.findById(id);
        return transacao.orElseThrow(() -> new RuntimeException("Transacao não encontrada"));
    }
    
    @Transactional
    public Transacao createTransacao(Transacao transacao){
        return transacaoRepository.save(transacao);
    }

    @Transactional
    public Transacao updateTransacao(Transacao transacao){
        Transacao newTransacao = getTransacaoById(transacao.getId());
        newTransacao.setData(transacao.getData());
        newTransacao.setQuantidade(transacao.getQuantidade());
        return transacaoRepository.save(transacao);
    
    }

    @Transactional
    public void deleteTransacao(Long id){
        transacaoRepository.deleteById(id);
    }
}
