package PRG.CarRent.Controller;

import java.util.List;
import java.util.Optional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import PRG.CarRent.Model.EmpresaModel;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/empresa")
public class EmpresaController {

    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping
    public List<EmpresaModel> getAllEmpresas() {
        return entityManager.createQuery("SELECT e FROM EmpresaModel e", EmpresaModel.class).getResultList();
    }

    @Operation(description = "Busca uma empresa pelo ID")
    @GetMapping("/{id}")
    public ResponseEntity<EmpresaModel> getEmpresaById(@PathVariable Long id) {
        EmpresaModel empresa = entityManager.find(EmpresaModel.class, id);
        return empresa != null ? ResponseEntity.ok(empresa) : ResponseEntity.notFound().build();
    }

    @Operation(description = "Cria uma nova empresa")
    @PostMapping
    @Transactional
    public ResponseEntity<EmpresaModel> createEmpresa(@RequestBody EmpresaModel empresa) {
        empresa.setId(null);
        entityManager.persist(empresa);
        return ResponseEntity.status(HttpStatus.CREATED).body(empresa);
    }

    @Operation(description = "Atualiza uma empresa existente")
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<EmpresaModel> updateEmpresa(@PathVariable Long id, @RequestBody EmpresaModel empresaDetails) {
        EmpresaModel empresa = entityManager.find(EmpresaModel.class, id);

        if (empresa != null) {
            empresa.setNome(empresaDetails.getNome());
            empresa.setCnpj(empresaDetails.getCnpj());
            empresa.setEmail(empresaDetails.getEmail());
            empresa.setQtdeFuncionarios(empresaDetails.getQtdeFuncionarios());
            entityManager.persist(empresa);
            return ResponseEntity.ok(empresa);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(description = "Deleta uma empresa existente")
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deleteEmpresa(@PathVariable Long id) {
        EmpresaModel empresa = entityManager.find(EmpresaModel.class, id);

        if (empresa != null) {
            entityManager.remove(empresa);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
