package prg.lab.main.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import prg.lab.main.Models.Vantagem;
import org.springframework.stereotype.Repository;
@Repository
public interface VantagemRepository extends JpaRepository<Vantagem, Long> {
    
}
