package PRG.CarRent.Controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import PRG.CarRent.Model.AutomovelModel;
import PRG.CarRent.Model.ClienteModel;
import PRG.CarRent.Util.Enums.TipoProprietario;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/automovel")
public class AutomovelController {

    @PersistenceContext
    private EntityManager entityManager;

    @Operation(description = "Busca todos os automóveis")
    @GetMapping
    public List<AutomovelModel> getAllAutomoveis() {
        return entityManager.createQuery("SELECT a FROM AutomovelModel a", AutomovelModel.class).getResultList();
    }

    @Operation(description = "Busca um automóvel por ID")
    @GetMapping("/{id}")
    public ResponseEntity<AutomovelModel> getAutomovelById(@PathVariable Long id) {
        AutomovelModel automovel = entityManager.find(AutomovelModel.class, id);
        return automovel != null ? ResponseEntity.ok(automovel) : ResponseEntity.notFound().build();
    }

    @Operation(description = "Cria um novo automóvel")
    @PostMapping
    @Transactional
    public ResponseEntity<AutomovelModel> createAutomovel(@RequestBody AutomovelModel automovel) {
        automovel.setId(null);  // Certifique-se de que o ID seja null para criar um novo registro
        entityManager.persist(automovel);
        return ResponseEntity.ok(automovel);
    }

    @Operation(description = "Atualiza um automóvel existente")
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<AutomovelModel> updateAutomovel(@PathVariable Long id, @RequestBody AutomovelModel automovelDetails) {
        AutomovelModel automovel = entityManager.find(AutomovelModel.class, id);

        if (automovel != null) {
            automovel.setMatricula(automovelDetails.getMatricula());
            automovel.setMarca(automovelDetails.getMarca());
            automovel.setModelo(automovelDetails.getModelo());
            automovel.setAno(automovelDetails.getAno());
            automovel.setTipoProprietario(automovelDetails.getTipoProprietario());
            entityManager.persist(automovel);
            return ResponseEntity.ok(automovel);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(description = "Deleta um automóvel existente")
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deleteAutomovel(@PathVariable Long id) {
        AutomovelModel automovel = entityManager.find(AutomovelModel.class, id);

        if (automovel != null) {
            entityManager.remove(automovel);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
