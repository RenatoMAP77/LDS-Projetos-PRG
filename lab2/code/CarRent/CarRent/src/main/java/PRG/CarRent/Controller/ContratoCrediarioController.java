package PRG.CarRent.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import PRG.CarRent.Model.BancoModel;
import PRG.CarRent.Model.ContratoCrediario;
import PRG.CarRent.Model.PedidoModel;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;

@RestController
@RequestMapping("/contrato-crediario")
public class ContratoCrediarioController {

    @PersistenceContext
    private EntityManager entityManager;

    

    @Operation(summary = "Busca todos os contratos crediários")
    @GetMapping()
    public List<ContratoCrediario> buscarContratosCrediarios() {
        return entityManager.createQuery("SELECT c FROM ContratoCrediario c", ContratoCrediario.class).getResultList();
    }

    @Operation(summary = "Busca um contrato crediário por ID")
    @GetMapping("/{id}")
    public ResponseEntity<ContratoCrediario> buscarContratoCrediarioPorId(@PathVariable Long id) {
        ContratoCrediario contratoCrediario = entityManager.find(ContratoCrediario.class, id);
        return contratoCrediario != null ? ResponseEntity.ok(contratoCrediario) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Atualiza um contrato crediário")
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<String> atualizarContratoCrediario(@PathVariable Long id, @RequestBody ContratoCrediario contratoCrediarioDetails) {
        ContratoCrediario contratoCrediario = entityManager.find(ContratoCrediario.class, id);

        if (contratoCrediario != null) {
            contratoCrediario.setCpfCnpjProprietario(contratoCrediarioDetails.getCpfCnpjProprietario());
            // Atualize outras associações conforme necessário
            entityManager.merge(contratoCrediario);
            return ResponseEntity.ok("Contrato Crediário atualizado com sucesso");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Deleta um contrato crediário")
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deletarContratoCrediario(@PathVariable Long id) {
        ContratoCrediario contratoCrediario = entityManager.find(ContratoCrediario.class, id);

        if (contratoCrediario != null) {
            entityManager.remove(contratoCrediario);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
