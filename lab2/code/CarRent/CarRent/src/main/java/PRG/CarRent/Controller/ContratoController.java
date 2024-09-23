package PRG.CarRent.Controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import PRG.CarRent.Model.BancoModel;
import PRG.CarRent.Model.ContratoModel;
import PRG.CarRent.Model.PedidoModel;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/contrato")
public class ContratoController {

    @PersistenceContext
    private EntityManager entityManager;

    @Operation(summary = "Cria um contrato")
    @PostMapping()
    @Transactional
    public ResponseEntity<ContratoModel> criarContrato(@RequestBody ContratoModel contrato) {
        contrato.setId(null);  // Certifique-se de que o ID seja null para criar um novo registro
        entityManager.persist(contrato);
        return ResponseEntity.ok(contrato);
    }

    @Operation(summary = "Busca todos os contratos")
    @GetMapping()
    public List<ContratoModel> buscarContratos() {
        return entityManager.createQuery("SELECT c FROM ContratoModel c", ContratoModel.class).getResultList();
    }

    @Operation(summary = "Busca um contrato por ID")
    @GetMapping("/{id}")
    public ResponseEntity<ContratoModel> buscarContratoPorId(@PathVariable Long id) {
        ContratoModel contrato = entityManager.find(ContratoModel.class, id);
        return contrato != null ? ResponseEntity.ok(contrato) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Atualiza um contrato")
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<String> atualizarContrato(@PathVariable Long id, @RequestBody ContratoModel contratoDetails) {
        ContratoModel contrato = entityManager.find(ContratoModel.class, id);

        if (contrato != null) {
            contrato.setDataInicio(contratoDetails.getDataInicio());
            contrato.setDataFinal(contratoDetails.getDataFinal());
            // Atualize outras associações conforme necessário
            entityManager.persist(contrato);
            return ResponseEntity.ok("Contrato atualizado com sucesso");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Deleta um contrato")
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deletarContrato(@PathVariable Long id) {
        ContratoModel contrato = entityManager.find(ContratoModel.class, id);

        if (contrato != null) {
            entityManager.remove(contrato);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
