package prg.lab.main.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import prg.lab.main.Models.Vantagem;
import prg.lab.main.Repositories.VantagemRepository;

@Service
public class VantagemService {
  
    @Autowired
    private VantagemRepository vantagemRepository;

    public Vantagem getVantagemById(Long id){
        Optional<Vantagem> vantagem = vantagemRepository.findById(id);
        return vantagem.orElseThrow(() -> new RuntimeException("Vantagem não encontrada"));
    }

    @Transactional
    public Vantagem cadastrarVantagem(Vantagem vantagem){
        if(vantagemRepository.existsByDescricao(vantagem.getDescricao())) {
            throw new RuntimeException("Vantagem já existe");
        }
        if (vantagem.getCustoEmMoedas() < 0) {
            throw new RuntimeException("Custo em moedas não pode ser negativo");
            
        }
        return vantagemRepository.save(vantagem);
    }
    @Transactional
    public Vantagem updateVantagem(Vantagem vantagem){
        Vantagem newVantagem = getVantagemById(vantagem.getId());
        newVantagem.setCustoEmMoedas(vantagem.getCustoEmMoedas());
        return vantagemRepository.save(vantagem);
    }

    @Transactional
    public List<Vantagem> listarVantagens(){
        return vantagemRepository.findAll();
    }


    @Transactional
    public void deleteVantagem(Long id){
        vantagemRepository.deleteById(id);
    }
}
