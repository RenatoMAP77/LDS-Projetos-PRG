package prg.lab.main.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import prg.lab.main.Models.Cupom;

@Repository
public interface CupomRepository extends JpaRepository<Cupom, Long> {
    
}
