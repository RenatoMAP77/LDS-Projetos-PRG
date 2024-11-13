package prg.lab.main.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import prg.lab.main.Models.Aluno;
import prg.lab.main.Models.EmpresaParceira;
import prg.lab.main.Models.Professor;
import prg.lab.main.Models.Usuario;
import prg.lab.main.Services.UsuarioService;
import prg.lab.main.Util.DTOs.LoginRequestDTO;

@RestController
@RequestMapping("/login")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<String> login(@RequestBody LoginRequestDTO loginRequest) {
        try {
            Usuario usuario = usuarioService.autenticar(loginRequest.getEmail(), loginRequest.getSenha());
            String tipoUsuario;

            if (usuario instanceof Aluno) {
                tipoUsuario = "Aluno";
            } else if (usuario instanceof Professor) {
                tipoUsuario = "Professor";
            } else if (usuario instanceof EmpresaParceira) {
                tipoUsuario = "Empresa";
            } else {
                tipoUsuario = "Outro";
            }

            return ResponseEntity.ok("Usuário autenticado como " + tipoUsuario);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Falha na autenticação");
        }
    }
}
