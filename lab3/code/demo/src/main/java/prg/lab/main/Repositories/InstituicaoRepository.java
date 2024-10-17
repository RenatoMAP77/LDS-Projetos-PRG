package prg.lab.main.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import prg.lab.main.Models.Instituicao;

@Repository
public interface InstituicaoRepository extends JpaRepository<Instituicao, Long> {
    
}
