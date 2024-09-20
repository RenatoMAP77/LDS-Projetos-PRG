// package PRG.CarRent.Controller;

// import java.sql.ResultSet;
// import java.sql.SQLException;
// import java.time.LocalDateTime;
// import java.util.List;

// import org.springframework.http.ResponseEntity;
// import org.springframework.jdbc.core.JdbcTemplate;
// import org.springframework.jdbc.core.RowMapper;
// import org.springframework.stereotype.Controller;
// import org.springframework.web.bind.annotation.*;

// import PRG.CarRent.Model.ContratoModel;


// @RestController()
// @RequestMapping("/contrato")
// public class ContratoController {

//     private final JdbcTemplate jdbcTemplate;

//     public ContratoController(JdbcTemplate jdbcTemplate) {
//         this.jdbcTemplate = jdbcTemplate;
//     }

//     private RowMapper<ContratoModel> contratoRowMapper = new RowMapper<>() {
//         @Override
//         public ContratoModel mapRow(ResultSet rs, int rowNum) throws SQLException {
//             ContratoModel contrato = new ContratoModel();
//             contrato.setId(rs.getLong("id"));
//             contrato.setDataInicio(rs.getTimestamp("data_inicio").toLocalDateTime());
//             contrato.setDataFinal(rs.getTimestamp("data_final").toLocalDateTime());
//             // Para o relacionamento OneToOne com Pedido, o ID será obtido separadamente.
//             return contrato;
//         }
//     };

//     @PostMapping()
//     public ResponseEntity<String> criarContrato(@RequestBody ContratoModel contrato) {
//         String sql = "INSERT INTO contrato (data_inicio, data_final, pedido_id) VALUES (?,?,?)";
//         int result = jdbcTemplate.update(sql,
//                 contrato.getDataInicio(),
//                 contrato.getDataFinal(),
//                 contrato.getPedido().getId());

//         if (result == 1) {
//             return ResponseEntity.ok("Contrato criado com sucesso");
//         } else {
//             return ResponseEntity.status(500).body("Erro ao criar contrato");
//         }
//     }

//     @GetMapping()
//     public ResponseEntity<Iterable<ContratoModel>> buscarContratos() {
//         String sql = "SELECT * FROM contrato";
//         List<ContratoModel> contratos = jdbcTemplate.query(sql, contratoRowMapper);
//         return ResponseEntity.ok(contratos);
//     }

//     @GetMapping("/{id}")
//     public ResponseEntity<ContratoModel> buscarContratoPorId(@PathVariable Long id) {
//         String sql = "SELECT * FROM contrato WHERE id = ?";
//         ContratoModel contrato = jdbcTemplate.queryForObject(sql, new Object[]{id}, contratoRowMapper);
//         return ResponseEntity.ok(contrato);
//     }

//     @PutMapping("/{id}")
//     public ResponseEntity<String> atualizarContrato(@PathVariable Long id, @RequestBody ContratoModel contrato) {
//         String sql = "UPDATE contrato SET data_inicio = ?, data_final = ?, pedido_id = ? WHERE id = ?";
//         int result = jdbcTemplate.update(sql,
//                 contrato.getDataInicio(),
//                 contrato.getDataFinal(),
//                 contrato.getPedido().getId(),
//                 id);

//         if (result == 1) {
//             return ResponseEntity.ok("Contrato atualizado com sucesso");
//         } else {
//             return ResponseEntity.status(500).body("Erro ao atualizar contrato");
//         }
//     }

//     @DeleteMapping("/{id}")
//     public ResponseEntity<String> deletarContrato(@PathVariable Long id) {
//         String sql = "DELETE FROM contrato WHERE id = ?";
//         int result = jdbcTemplate.update(sql, id);
//         if (result == 1) {
//             return ResponseEntity.ok("Contrato deletado com sucesso");
//         } else {
//             return ResponseEntity.status(500).body("Erro ao deletar contrato");
//         }
//     }
// }
