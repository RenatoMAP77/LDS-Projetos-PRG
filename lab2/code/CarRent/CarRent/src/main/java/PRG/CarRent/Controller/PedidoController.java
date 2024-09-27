package PRG.CarRent.Controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import PRG.CarRent.Model.AutomovelModel;
import PRG.CarRent.Model.ClienteModel;
import PRG.CarRent.Model.ContratoCrediario;
import PRG.CarRent.Model.ContratoModel;
import PRG.CarRent.Model.EmpresaModel;
import PRG.CarRent.Model.PedidoModel;
import PRG.CarRent.Util.Enums.StatusPedido;
import PRG.CarRent.Util.Enums.TipoPedido;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    @PersistenceContext
    private EntityManager entityManager;

    @Operation(summary = "Cria um pedido")
    @PostMapping()
    @Transactional
    public ResponseEntity<PedidoModel> criarPedido(@RequestBody PedidoModel pedido) {
        pedido.setId(null); // Certifique-se de que o ID seja null para criar um novo registro
        entityManager.persist(pedido);
        return ResponseEntity.ok(pedido);
    }

    @Operation(summary = "Busca todos os pedidos")
    @GetMapping()
    public List<PedidoModel> buscarPedidos() {
        return entityManager.createQuery("SELECT p FROM PedidoModel p", PedidoModel.class).getResultList();
    }

    @Operation(summary = "Busca um pedido por ID")
    @GetMapping("/{id}")
    public ResponseEntity<PedidoModel> buscarPedidoPorId(@PathVariable Long id) {
        PedidoModel pedido = entityManager.find(PedidoModel.class, id);
        return pedido != null ? ResponseEntity.ok(pedido) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Cria Contrato Creditário ou Contrato Normal para um pedido") 
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<String> atualizarPedido(@PathVariable Long id, @RequestBody PedidoModel pedidoDetails) {
        PedidoModel pedido = entityManager.find(PedidoModel.class, id);

        if (pedido != null) {
            pedido.setCodigoPedido(pedidoDetails.getCodigoPedido());
            pedido.setTipoPedido(pedidoDetails.getTipoPedido());
            pedido.setStatusPedido(pedidoDetails.getStatusPedido());
            pedido.setAutomovel(pedidoDetails.getAutomovel());
            if (pedido.getStatusPedido() == StatusPedido.APROVADO && pedido.getTipoPedido() == TipoPedido.NORMAL) {
            ContratoModel contrato = new ContratoModel();
            contrato.setDataInicio(pedido.getDataInicio());
            contrato.setDataFinal(pedido.getDataFinal());
            contrato.setPedido(pedido);
            entityManager.persist(pedido);
            return ResponseEntity.ok("Contrato criado com sucesso");
            } else if (pedido.getStatusPedido() == StatusPedido.APROVADO && pedido.getTipoPedido() == TipoPedido.CREDIARIO) {
            ContratoCrediario contratoCred = new ContratoCrediario();
            contratoCred.setDataInicio(pedido.getDataInicio());
            contratoCred.setDataFinal(pedido.getDataFinal());
            contratoCred.setPedido(pedido);
            contratoCred.setBanco(pedido.getBanco());
            entityManager.persist(pedido);
            return ResponseEntity.ok("Contrato Creditário criado com sucesso");
            } 

            // Atualize outras associações conforme necessário
            entityManager.persist(pedido);
            return ResponseEntity.ok("Pedido atualizado com sucesso");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Deleta um pedido")
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deletarPedido(@PathVariable Long id) {
        PedidoModel pedido = entityManager.find(PedidoModel.class, id);

        if (pedido != null) {
            entityManager.remove(pedido);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @Operation(summary = "Aluga um automóvel")
    @PostMapping("/alugar/{idAutomovel}")
    @Transactional
    public ResponseEntity<String> alugarAutomovel(@RequestBody PedidoModel pedido, @PathVariable Long idAutomovel ) {
        AutomovelModel automovel = entityManager.find(AutomovelModel.class, idAutomovel);
        for (PedidoModel p : automovel.getPedidos()) {
            if (p.getStatusPedido() == StatusPedido.APROVADO &&
             (pedido.getDataInicio().isAfter(p.getDataInicio()) 
             && pedido.getDataInicio().isBefore(p.getDataFinal()) 
             && pedido.getDataFinal().isBefore(p.getDataFinal())
              && pedido.getDataFinal().isAfter(p.getDataInicio()))){   
                return ResponseEntity.badRequest().body("Automóvel já alugado nesse período");
            
        }}
        pedido.setAutomovel(automovel);
        pedido.setStatusPedido(StatusPedido.PENDENTE);
        entityManager.persist(pedido);
        return ResponseEntity.ok("Automóvel alugado com sucesso");
    }
}
