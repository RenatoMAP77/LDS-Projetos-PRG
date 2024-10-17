package prg.lab.main.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import prg.lab.main.Models.EmpresaParceira;
import prg.lab.main.Repositories.EmpresaParceiraRepository;

@Service
public class EmpresaParceiraService {
    @Autowired
    private EmpresaParceiraRepository empresaParceiraRepository;


    public EmpresaParceira getEmpresaParceiraById(Long id){
        Optional<EmpresaParceira> empresaParceira = empresaParceiraRepository.findById(id);
        return empresaParceira.orElseThrow(() -> new RuntimeException("Empresa Parceira não encontrada"));
    }
    @Transactional
    public EmpresaParceira createEmpresaParceira(EmpresaParceira empresaParceira){
        return empresaParceiraRepository.save(empresaParceira);
    }
    @Transactional
    public EmpresaParceira updateEmpresaParceira(EmpresaParceira empresaParceira){
        EmpresaParceira newEmpresaParceira = getEmpresaParceiraById(empresaParceira.getId());
        newEmpresaParceira.setNome(empresaParceira.getNome());
        return empresaParceiraRepository.save(empresaParceira);
    }
    @Transactional
    public void deleteEmpresaParceira(Long id){
        empresaParceiraRepository.deleteById(id);
    }
}
