package prg.lab.main.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import prg.lab.main.Models.Professor;
@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {
    
}
