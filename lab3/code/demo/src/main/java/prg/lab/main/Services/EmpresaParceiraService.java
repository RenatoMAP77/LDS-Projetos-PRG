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


    public EmpresaParceira findById(Long id){
        Optional<EmpresaParceira> empresaParceira = empresaParceiraRepository.findById(id);
        return empresaParceira.orElseThrow(() -> new RuntimeException("Empresa Parceira não encontrada"));
    }
    @Transactional
    public EmpresaParceira create(EmpresaParceira empresaParceira){
        return empresaParceiraRepository.save(empresaParceira);
    }
    @Transactional
    public EmpresaParceira update(EmpresaParceira empresaParceira){
        EmpresaParceira newEmpresaParceira = findById(empresaParceira.getId());
        newEmpresaParceira.setNome(empresaParceira.getNome());
        return empresaParceiraRepository.save(empresaParceira);
    }
    @Transactional
    public void delete(Long id){
        empresaParceiraRepository.deleteById(id);
    }

    public Iterable<EmpresaParceira> findAll(){
        return empresaParceiraRepository.findAll();
    }

    public EmpresaParceira login(String email, String senha){
        Optional<EmpresaParceira> empresa=  this.empresaParceiraRepository.findByEmailAndSenha(email, senha);
        return empresa.orElseThrow(() -> new RuntimeException("Email ou senha inválidos"));
    }

    public EmpresaParceira findByEmail(String email){
        Optional<EmpresaParceira> empresa = this.empresaParceiraRepository.findByEmail(email);
        return empresa.orElseThrow(() -> new RuntimeException("Email não encontrado"));
    }
}
