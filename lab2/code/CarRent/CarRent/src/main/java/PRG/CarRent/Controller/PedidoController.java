package PRG.CarRent.Controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
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
            if (pedidoDetails.getCodigoPedido() != null) {
                pedido.setCodigoPedido(pedidoDetails.getCodigoPedido());
            }
            if (pedidoDetails.getDataInicio() != null) {
                pedido.setDataInicio(pedidoDetails.getDataInicio());
            }
            if (pedidoDetails.getDataFinal() != null) {
                pedido.setDataFinal(pedidoDetails.getDataFinal());
            }
            if (pedidoDetails.getStatusPedido() != StatusPedido.APROVADO && pedidoDetails.getStatusPedido() != null) {
                pedido.setStatusPedido(pedidoDetails.getStatusPedido());
            }
            if (pedidoDetails.getTipoPedido() != null) {
                pedido.setTipoPedido(pedidoDetails.getTipoPedido());
            }
            if (pedidoDetails.getEmpresa() != null) {
                pedido.setEmpresa(pedidoDetails.getEmpresa());
            }
            if (pedidoDetails.getBanco() != null) {
                pedido.setBanco(pedidoDetails.getBanco());
            }
            if (pedidoDetails.getAutomovel() != null) {
                pedido.setAutomovel(pedidoDetails.getAutomovel());

            }
            if (pedidoDetails.getCliente() != null) {
                pedido.setCliente(pedidoDetails.getCliente());
            }

            if (pedidoDetails.getStatusPedido() == StatusPedido.CANCELADO) {
                entityManager.remove(pedido.getContrato());
            }
            if (pedido.getStatusPedido() == StatusPedido.APROVADO
                    && pedidoDetails.getStatusPedido() == StatusPedido.APROVADO) {
                return ResponseEntity.badRequest().body("Pedido já aprovado");
            } else if (pedidoDetails.getStatusPedido() == StatusPedido.REPROVADO) {
                entityManager.remove(pedido);
                return ResponseEntity.ok("Pedido reprovado com sucesso");
            } else if (pedidoDetails.getStatusPedido() == StatusPedido.APROVADO
                    && pedido.getTipoPedido() == TipoPedido.NORMAL) {
                pedido.setStatusPedido(StatusPedido.APROVADO);
                ContratoModel contrato = new ContratoModel();

                contrato.setDataInicio(pedido.getDataInicio());
                contrato.setDataFinal(pedido.getDataFinal());
                contrato.setPedido(pedido);
                entityManager.merge(pedido);
                entityManager.persist(contrato);



                return ResponseEntity.ok("Contrato criado com sucesso");
            } else if (pedidoDetails.getStatusPedido() == StatusPedido.APROVADO
                    && pedido.getTipoPedido() == TipoPedido.CREDIARIO) {


                ContratoCrediario contratoCred = new ContratoCrediario();
                if (pedido.getCliente() != null) {
                    contratoCred.setCpfCnpjProprietario(pedido.getCliente().getCpf());


                } else if (pedido.getEmpresa() != null) {
                    contratoCred.setCpfCnpjProprietario(pedido.getEmpresa().getCnpj());


                }
                pedido.setStatusPedido(StatusPedido.APROVADO);

                // Calcula o valor total do contrato
                long dias = ChronoUnit.DAYS.between(pedido.getDataInicio(), pedido.getDataFinal());


                contratoCred.setDataInicio(pedido.getDataInicio());
                contratoCred.setDataFinal(pedido.getDataFinal());
                contratoCred.setPedido(pedido);
                contratoCred.setBanco(pedido.getBanco());
                contratoCred.setValorTotal(pedido.getAutomovel().getDiaria() * dias);
                

                entityManager.merge(pedido);
                entityManager.persist(contratoCred);
                

                return ResponseEntity.ok("Contrato Creditário criado com sucesso");
            }

            // Atualize outras associações conforme necessário
            entityManager.merge(pedido); // Atualizar pedido
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

    @Operation(summary = "Aluga um automóvel ")
    @PostMapping("/alugar")
    @Transactional
    public ResponseEntity<String> alugarAutomovel(@RequestBody PedidoModel pedido) {
        Long idAutomovel = pedido.getAutomovel().getId();
        AutomovelModel automovel = entityManager.find(AutomovelModel.class, idAutomovel);
        for (PedidoModel p : automovel.getPedidos()) {
            if (p.getStatusPedido() == StatusPedido.APROVADO 
                    &&
                    (pedido.getDataInicio().isAfter(p.getDataInicio())
                    && pedido.getDataInicio().isBefore(p.getDataFinal())
                    && pedido.getDataFinal().isBefore(p.getDataFinal())
                    && pedido.getDataFinal().isAfter(p.getDataInicio())
                    )) {

                return ResponseEntity.badRequest().body("Automóvel já alugado nesse período");

            }
        }
        pedido.setAutomovel(automovel);
        pedido.setStatusPedido(StatusPedido.PENDENTE);
        entityManager.persist(pedido);
        return ResponseEntity.ok("Automóvel alugado com sucesso");
    }

}
