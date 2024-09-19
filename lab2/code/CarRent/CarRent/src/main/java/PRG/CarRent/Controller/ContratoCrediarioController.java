package PRG.CarRent.Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import PRG.CarRent.Model.ContratoCrediario;

@RestController
@RequestMapping("/contrato-crediario")
public class ContratoCrediarioController {

    private final JdbcTemplate jdbcTemplate;

    public ContratoCrediarioController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<ContratoCrediario> contratoCrediarioRowMapper = new RowMapper<>() {
        @Override
        public ContratoCrediario mapRow(ResultSet rs, int rowNum) throws SQLException {
            ContratoCrediario contratoCrediario = new ContratoCrediario();
            contratoCrediario.setId(rs.getLong("id"));
            contratoCrediario.setCpfCnpjProprietario(rs.getString("cpf_cnpj_proprietario"));
            // Como o pedido é uma relação OneToOne, você pode buscar o ID do pedido separadamente, se necessário.
            return contratoCrediario;
        }
    };

    @PostMapping()
    public ResponseEntity<String> criarContratoCrediario(@RequestBody ContratoCrediario contratoCrediario) {
        String sql = "INSERT INTO contrato_crediario (cpf_cnpj_proprietario, pedido_id) VALUES (?,?)";
        int result = jdbcTemplate.update(sql,
                contratoCrediario.getCpfCnpjProprietario(),
                contratoCrediario.getPedido().getId());

        if (result == 1) {
            return ResponseEntity.ok("Contrato Crediário criado com sucesso");
        } else {
            return ResponseEntity.status(500).body("Erro ao criar Contrato Crediário");
        }
    }

    @GetMapping()
    public ResponseEntity<Iterable<ContratoCrediario>> buscarContratosCrediarios() {
        String sql = "SELECT * FROM contrato_crediario";
        List<ContratoCrediario> contratosCrediarios = jdbcTemplate.query(sql, contratoCrediarioRowMapper);
        return ResponseEntity.ok(contratosCrediarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContratoCrediario> buscarContratoCrediarioPorId(@PathVariable Long id) {
        String sql = "SELECT * FROM contrato_crediario WHERE id = ?";
        ContratoCrediario contratoCrediario = jdbcTemplate.queryForObject(sql, new Object[]{id}, contratoCrediarioRowMapper);
        return ResponseEntity.ok(contratoCrediario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> atualizarContratoCrediario(@PathVariable Long id, @RequestBody ContratoCrediario contratoCrediario) {
        String sql = "UPDATE contrato_crediario SET cpf_cnpj_proprietario = ?, pedido_id = ? WHERE id = ?";
        int result = jdbcTemplate.update(sql,
                contratoCrediario.getCpfCnpjProprietario(),
                contratoCrediario.getPedido().getId(),
                id);

        if (result == 1) {
            return ResponseEntity.ok("Contrato Crediário atualizado com sucesso");
        } else {
            return ResponseEntity.status(500).body("Erro ao atualizar Contrato Crediário");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarContratoCrediario(@PathVariable Long id) {
        String sql = "DELETE FROM contrato_crediario WHERE id = ?";
        int result = jdbcTemplate.update(sql, id);
        if (result == 1) {
            return ResponseEntity.ok("Contrato Crediário deletado com sucesso");
        } else {
            return ResponseEntity.status(500).body("Erro ao deletar Contrato Crediário");
        }
    }
}
