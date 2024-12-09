package prg.lab.main.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import prg.lab.main.Models.Aluno;
import prg.lab.main.Repositories.AlunoRepository;

@Service
public class AlunoService {
    @Autowired
    private AlunoRepository alunoRepository;


    public Aluno getAlunoById(Long id) {
        Optional<Aluno> aluno = alunoRepository.findById(id);
        return aluno.orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
    }
    @Transactional
    public Aluno createAluno(Aluno aluno) {
        if (alunoRepository.existsByEmail(aluno.getEmail())) {
            throw new RuntimeException("Email já cadastrado");
            
        }
        if (aluno.getSaldoMoedas()<0) {
            throw new RuntimeException("Saldo de moedas não pode ser negativo");
            
        }
        return alunoRepository.save(aluno);
    }
    @Transactional
    public Aluno updateAluno(Aluno aluno) {
        Aluno newAluno = getAlunoById(aluno.getId());
        newAluno.setNome(aluno.getNome());
        newAluno.setInstituicao(aluno.getInstituicao());
        return alunoRepository.save(aluno);
    }
    @Transactional
    public void deleteAluno(Long id) {
        alunoRepository.deleteById(id);
    }
    @Transactional
    public List<Aluno> getAllAlunos() {
        return alunoRepository.findAll();
    }

    public Optional<Aluno> login(String email, String senha) {
        Optional<Aluno> aluno = this.alunoRepository.findByEmailAndSenha(email, senha);
        return aluno;
    }

    @Transactional
    public void creditarMoedas(Long id, double quantidade) {
        Aluno aluno = getAlunoById(id);
        aluno.setSaldoMoedas(aluno.getSaldoMoedas() + quantidade);
        alunoRepository.save(aluno);
    }

    @Transactional
    public void debitarMoedas(Long id, Double quantidade) {
        Aluno aluno = getAlunoById(id);
        if (aluno.getSaldoMoedas() < quantidade) {
            throw new RuntimeException("Saldo do aluno insuficiente para realizar a transação");
            
        }
        aluno.setSaldoMoedas(aluno.getSaldoMoedas() - quantidade);
        alunoRepository.save(aluno);
    }
}
