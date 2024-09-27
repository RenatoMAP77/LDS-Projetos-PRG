package PRG.CarRent.Controller;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import PRG.CarRent.Model.BancoModel;
import PRG.CarRent.Model.PedidoModel;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/banco")
public class BancoController {

    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping
    public List<BancoModel> getAllBancos() {
        return entityManager.createQuery("SELECT b FROM BancoModel b", BancoModel.class).getResultList();
    }

    @Operation(description = "Busca um banco pelo ID")
    @GetMapping("/{id}")
    public ResponseEntity<BancoModel> getBancoById(@PathVariable Long id) {
        BancoModel banco = entityManager.find(BancoModel.class, id);
        return banco != null ? ResponseEntity.ok(banco) : ResponseEntity.notFound().build();
    }

    @Operation(description = "Cria um novo banco")
    @PostMapping
    @Transactional
    public ResponseEntity<BancoModel> createBanco(@RequestBody BancoModel banco) {
        banco.setBanco_id(null);
        entityManager.persist(banco);
        return ResponseEntity.ok(banco);
    }

    @Operation(description = "Atualiza um banco existente")
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<BancoModel> updateBanco(@PathVariable Long id, @RequestBody BancoModel bancoDetails) {
        BancoModel banco = entityManager.find(BancoModel.class, id);

        if (banco != null) {
            banco.setNome(bancoDetails.getNome());
            banco.setCnpj(bancoDetails.getCnpj());
            banco.setEmail(bancoDetails.getEmail());
            banco.setTipoBanco(bancoDetails.getTipoBanco());
            entityManager.persist(banco);
            return ResponseEntity.ok(banco);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(description = "Deleta um banco existente")
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deleteBanco(@PathVariable Long id) {
        BancoModel banco = entityManager.find(BancoModel.class, id);

        if (banco != null) {
            entityManager.remove(banco);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(description = "Busca todos os contratos de crediario associados a esse banco")
    @GetMapping("/{id}/contratos")
    @Transactional
    public List<BancoModel> getContratos(@PathVariable Long id) {
        return entityManager.createQuery("SELECT c FROM BancoModel c WHERE c.banco_id = :id", BancoModel.class)
                .setParameter("id", id).getResultList();
    }

    @Operation(description = "Busca todos os contratos associados a esse banco")
    @GetMapping("/{id}/contratos")
    @Transactional
    public List<BancoModel> getContratosCrediario(@PathVariable Long id) {
        return entityManager.createQuery("SELECT c FROM ContratoCrediario c WHERE c.banco_id = :id", BancoModel.class)
                .setParameter("id", id).getResultList();
    }

    @Operation(description = "Busca todos os pedidos associados a esse banco")
    @GetMapping("/{id}/pedidos")
    @Transactional
    public List<BancoModel> getPedidos(@PathVariable Long id) {
        return entityManager.createQuery("SELECT c FROM PedidoModel c WHERE c.banco_id = :id", BancoModel.class)
                .setParameter("id", id).getResultList();
    }

    @Operation(summary = "Avaliar pedido, relacionado a este banco")
    @PatchMapping("/{id}/pedido/{idPedido}/{resposta}")
    @Transactional
    public ResponseEntity<String> avaliarPedido(@PathVariable Long id, @PathVariable Long idPedido,
            @PathVariable boolean resposta) {
        BancoModel banco = entityManager.find(BancoModel.class, id);
        PedidoModel pedido = entityManager.find(PedidoModel.class, idPedido);

        if (banco != null && pedido != null) {
            if (banco.avaliarPedido(pedido, resposta)) {
                entityManager.persist(banco);
                entityManager.persist(pedido);
                return ResponseEntity.ok("Pedido avaliado com sucesso");
            } else {
                return ResponseEntity.badRequest().body("Pedido já foi avaliado");
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    


}
