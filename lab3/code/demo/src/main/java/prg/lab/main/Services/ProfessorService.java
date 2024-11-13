package prg.lab.main.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import prg.lab.main.Models.Professor;
import prg.lab.main.Repositories.ProfessorRepository;

@Service
public class ProfessorService {
    @Autowired
    private ProfessorRepository professorRepository;    


    public Professor getProfessorById(Long id) {
        Optional<Professor> professor = professorRepository.findById(id);
        return professor.orElseThrow(() -> new RuntimeException("Professor não encontrado"));
    }
    @Transactional
    public Professor createProfessor(Professor professor) {
        return professorRepository.save(professor);
    }
    @Transactional
    public Professor updateProfessor(Professor professor) {
        Professor newProfessor = getProfessorById(professor.getId());
        newProfessor.setNome(professor.getNome());
        return professorRepository.save(professor);
    }
    @Transactional
    public void deleteProfessor(Long id) {
        professorRepository.deleteById(id);
    }
    @Transactional
    public List<Professor> getAllProfessores() {
        return professorRepository.findAll();
    }

    @Transactional
    public void creditarMoedas(Long id, int quantidade) {
        Professor professor = getProfessorById(id);
        professor.setSaldoMoedas(professor.getSaldoMoedas() + quantidade);
        professorRepository.save(professor);
    }

    @Transactional
    public void debitarMoedas(Long id, Double quantidade) {
        Professor professor = getProfessorById(id);
        if (professor.getSaldoMoedas() < quantidade) {
            throw new RuntimeException("Saldo do professor insuficiente para realizar a transação");
            
        }
        professor.setSaldoMoedas(professor.getSaldoMoedas() - quantidade);
        professorRepository.save(professor);
    }
}
