package prg.lab.main.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import prg.lab.main.Models.Instituicao;
import prg.lab.main.Repositories.InstituicaoRepository;

@Service
public class InstituicaoService {
@Autowired
private InstituicaoRepository instituicaoRepository;

    public Instituicao getInstituicaoById(Long id){
        Optional<Instituicao> instituicao = instituicaoRepository.findById(id);
        return instituicao.orElseThrow(() -> new RuntimeException("Instituicao não encontrada"));
    }
    @Transactional
    public Instituicao createInstituicao(Instituicao instituicao){
        return instituicaoRepository.save(instituicao);
    }
    @Transactional
    public Instituicao updateInstituicao(Instituicao instituicao){
        Instituicao newInstituicao = getInstituicaoById(instituicao.getId());
        newInstituicao.setNome(instituicao.getNome());
        return instituicaoRepository.save(instituicao);
    }
    @Transactional
    public void deleteInstituicao(Long id){
        instituicaoRepository.deleteById(id);
    }
}
