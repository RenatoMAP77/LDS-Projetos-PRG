package prg.lab.main.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import prg.lab.main.Models.EmpresaParceira;

@Repository
public interface EmpresaParceiraRepository extends JpaRepository<EmpresaParceira, Long> {

    Optional<EmpresaParceira> findByEmailAndSenha(String email, String senha);

    Optional<EmpresaParceira> findByEmail(String email);
    
}
