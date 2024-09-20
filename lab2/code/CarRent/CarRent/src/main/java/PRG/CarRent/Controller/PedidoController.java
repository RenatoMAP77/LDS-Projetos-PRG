
// package PRG.CarRent.Controller;

// import java.sql.ResultSet;
// import java.sql.SQLException;
// import java.util.List;

// import org.springframework.http.ResponseEntity;
// import org.springframework.jdbc.core.JdbcTemplate;
// import org.springframework.jdbc.core.RowMapper;
// import org.springframework.stereotype.Controller;
// import org.springframework.web.bind.annotation.*;

// import PRG.CarRent.Model.PedidoModel;
// import PRG.CarRent.Util.Enums.StatusPedido;
// import PRG.CarRent.Util.Enums.TipoPedido;

// @RestController
// @RequestMapping("/pedido")
// public class PedidoController {

//     private final JdbcTemplate jdbcTemplate;

//     public PedidoController(JdbcTemplate jdbcTemplate) {
//         this.jdbcTemplate = jdbcTemplate;
//     }

//     private RowMapper<PedidoModel> pedidoRowMapper = new RowMapper<>() {
//         @Override
//         public PedidoModel mapRow(ResultSet rs, int rowNum) throws SQLException {
//             PedidoModel pedido = new PedidoModel();
//             pedido.setId(rs.getLong("id"));
//             pedido.setCodigoPedido(rs.getString("codigo_pedido"));
//             pedido.setTipoPedido(TipoPedido.valueOf(rs.getString("tipo_pedido")));
//             pedido.setStatusPedido(StatusPedido.valueOf(rs.getString("status_pedido")));
//             // As associações com Automovel, Cliente, Empresa, etc. precisam ser gerenciadas com consultas separadas
//             return pedido;
//         }
//     };

//     @PostMapping()
//     public ResponseEntity<String> criarPedido(@RequestBody PedidoModel pedido) {
//         String sql = "INSERT INTO pedido (codigo_pedido, tipo_pedido, status_pedido, automovel_id, cliente_id, empresa_id, banco_id, contrato_id, contrato_crediario_id) VALUES (?,?,?,?,?,?,?,?,?)";
//         int result = jdbcTemplate.update(sql,
//                 pedido.getCodigoPedido(),
//                 pedido.getTipoPedido().toString(),
//                 pedido.getStatusPedido().toString(),
//                 pedido.getAutomovel().getId(),
//                 pedido.getCliente().getId(),
//                 pedido.getEmpresa().getId(),
//                 pedido.getBanco().getId(),
//                 pedido.getContrato().getId(),
//                 pedido.getContratoCrediario().getId());

//         if (result == 1) {
//             return ResponseEntity.ok("Pedido criado com sucesso");
//         } else {
//             return ResponseEntity.status(500).body("Erro ao criar pedido");
//         }
//     }

//     @GetMapping()
//     public ResponseEntity<Iterable<PedidoModel>> buscarPedidos() {
//         String sql = "SELECT * FROM pedido";
//         List<PedidoModel> pedidos = jdbcTemplate.query(sql, pedidoRowMapper);
//         return ResponseEntity.ok(pedidos);
//     }

//     @GetMapping("/{id}")
//     public ResponseEntity<PedidoModel> buscarPedidoPorId(@PathVariable Long id) {
//         String sql = "SELECT * FROM pedido WHERE id = ?";
//         PedidoModel pedido = jdbcTemplate.queryForObject(sql, new Object[]{id}, pedidoRowMapper);
//         return ResponseEntity.ok(pedido);
//     }

//     @PutMapping("/{id}")
//     public ResponseEntity<String> atualizarPedido(@PathVariable Long id, @RequestBody PedidoModel pedido) {
//         String sql = "UPDATE pedido SET codigo_pedido = ?, tipo_pedido = ?, status_pedido = ?, automovel_id = ?, cliente_id = ?, empresa_id = ?, banco_id = ?, contrato_id = ?, contrato_crediario_id = ? WHERE id = ?";
//         int result = jdbcTemplate.update(sql,
//                 pedido.getCodigoPedido(),
//                 pedido.getTipoPedido().toString(),
//                 pedido.getStatusPedido().toString(),
//                 pedido.getAutomovel().getId(),
//                 pedido.getCliente().getId(),
//                 pedido.getEmpresa().getId(),
//                 pedido.getBanco().getId(),
//                 pedido.getContrato().getId(),
//                 pedido.getContratoCrediario().getId(),
//                 id);

//         if (result == 1) {
//             return ResponseEntity.ok("Pedido atualizado com sucesso");
//         } else {
//             return ResponseEntity.status(500).body("Erro ao atualizar pedido");
//         }
//     }

//     @DeleteMapping("/{id}")
//     public ResponseEntity<String> deletarPedido(@PathVariable Long id) {
//         String sql = "DELETE FROM pedido WHERE id = ?";
//         int result = jdbcTemplate.update(sql, id);
//         if (result == 1) {
//             return ResponseEntity.ok("Pedido deletado com sucesso");
//         } else {
//             return ResponseEntity.status(500).body("Erro ao deletar pedido");
//         }
//     }
// }

