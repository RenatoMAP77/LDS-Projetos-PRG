package PRG.CarRent.Controller;

import java.sql.ResultSet;
import java.sql.SQLException;


import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import PRG.CarRent.Model.AutomovelModel;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;




@Controller
@RestController("/automovel")
public class AutomovelController{


    private final JdbcTemplate jdbcTemplate;
    
    public AutomovelController(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
     private RowMapper<AutomovelModel> automovelRowMapper = new RowMapper<>() {
        @Override
        public AutomovelModel mapRow(ResultSet rs, int rowNum) throws SQLException {
            AutomovelModel automovel = new AutomovelModel();
            automovel.setId(rs.getLong("id"));
            automovel.setMatricula(rs.getString("matricula"));
            automovel.setMarca(rs.getString("marca"));
            automovel.setModelo(rs.getString("modelo"));
            automovel.setAno(rs.getInt("ano"));
            return automovel;
        }
    };

    @PostMapping()
    public ResponseEntity<String> criarAutomovel(@RequestBody AutomovelModel automovel) {
        String sql = "INSERT INTO automovel (matricula, marca, modelo, ano) VALUES (?,?,?,?)";
        int result = jdbcTemplate.update(sql, automovel.getMatricula(), automovel.getMarca(), automovel.getModelo(), automovel.getAno());
        if (result == 1){
            return ResponseEntity.ok("Automovel criado com sucesso");
        } else {
            return ResponseEntity.status(500).body("Erro ao criar automovel");
        } 
        
    }

    @GetMapping()
    public ResponseEntity<Iterable<AutomovelModel>> buscarAutomoveis() {
        String sql = "SELECT * FROM automovel";
        List<AutomovelModel> automoveis = jdbcTemplate.query(sql, automovelRowMapper);
        return ResponseEntity.ok(automoveis);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<AutomovelModel> buscarAutomovelPorId(@PathVariable Long id) {
        String sql = "SELECT * FROM automovel WHERE id = ?";
        AutomovelModel automovel = jdbcTemplate.queryForObject(sql,automovelRowMapper);
        return ResponseEntity.ok(automovel);

    }

    @PutMapping("/{id}")
    public ResponseEntity<String> atualizarAutomovel(@PathVariable Long id, @RequestBody AutomovelModel automovel) {
        String sql = "UPDATE automovel SET matricula = ?, marca = ?, modelo = ?, ano = ? WHERE id = ?";
        int result = jdbcTemplate.update(sql, automovel.getMatricula(), automovel.getMarca(), automovel.getModelo(), automovel.getAno(), id);
        if (result == 1){
            return ResponseEntity.ok("Automovel atualizado com sucesso");
        } else {
            return ResponseEntity.status(500).body("Erro ao atualizar automovel");
        }
  
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarAutomovel(@RequestParam String param) {
        String sql = "DELETE FROM automovel WHERE id = ?";
        int result = jdbcTemplate.update(sql, param);
        if (result == 1){
            return ResponseEntity.ok("Automovel deletado com sucesso");
        } else {
            return ResponseEntity.status(500).body("Erro ao deletar automovel");
        }


    }
    
    
    

}
