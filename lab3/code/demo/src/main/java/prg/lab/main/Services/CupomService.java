package prg.lab.main.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import prg.lab.main.Models.Cupom;
import prg.lab.main.Repositories.CupomRepository;

@Service
public class CupomService {
    @Autowired
    private CupomRepository cupomRepository;

    public Cupom getCupomById(Long id) {
        Optional<Cupom> cupom = cupomRepository.findById(id);
        return cupom.orElseThrow(() -> new RuntimeException("Cupom não encontrado"));
    }
    @Transactional
    public Cupom createCupom(Cupom cupom) {
        return cupomRepository.save(cupom);
    }

    @Transactional
    public void deleteCupom(Long id) {
        cupomRepository.deleteById(id);
    }
}
