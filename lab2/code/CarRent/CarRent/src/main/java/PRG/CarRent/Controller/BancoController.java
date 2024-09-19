// package PRG.CarRent.Controller;

// import java.sql.ResultSet;
// import java.sql.SQLException;


// import java.util.List;
// import org.springframework.http.ResponseEntity;
// import org.springframework.jdbc.core.JdbcTemplate;
// import org.springframework.jdbc.core.RowMapper;
// import org.springframework.stereotype.Controller;
// import org.springframework.web.bind.annotation.RestController;
// import PRG.CarRent.Model.BancoModel;

// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.PutMapping;




// @Controller
// @RestController("/banco")
// public class BancoController{


//     private final JdbcTemplate jdbcTemplate;
    
//     public BancoController(JdbcTemplate jdbcTemplate){
//         this.jdbcTemplate = jdbcTemplate;
//     }
//         private RowMapper<BancoModel> bancoRowMapper = new RowMapper<>() {
//             @Override
//             public BancoModel mapRow(ResultSet rs, int rowNum) throws SQLException {
//                 BancoModel banco = new BancoModel();
//                 banco.setId(rs.getLong("id"));
//                 banco.setNome(rs.getString("nome"));
//                 banco.setCnpj(rs.getString("cnpj"));
//                 banco.setEmail(rs.getString("email"));
//                 return banco;
//             }
//         };
    
    

// }
