package prg.lab.main.Controllers;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.swagger.v3.oas.annotations.Operation;
import prg.lab.main.Models.Professor;
import prg.lab.main.Services.InstituicaoService;
import prg.lab.main.Services.ProfessorService;
import prg.lab.main.Util.DTOs.LoginRequestDTO;
import prg.lab.main.Util.DTOs.LoginResponseDTO;
import prg.lab.main.Util.DTOs.ProfessorDTO;

@RestController
@RequestMapping("/professor")
public class ProfessorController {
    
    @Autowired
    private ProfessorService professorService;

    @Autowired
    private InstituicaoService instituicaoService;

    @GetMapping("/{id}")
    public ResponseEntity<Professor> getMethodName(@PathVariable Long id) {

        return ResponseEntity.ok(professorService.getProfessorById(id));
        
    }

    @PostMapping()
    public ResponseEntity<Professor> create(@RequestBody ProfessorDTO professor) {

        System.out.println(professor.toString());

        Professor newProfessor =  this.professorService.createProfessor(new Professor( professor.cpf(), professor.departamento(), 
        professor.saldoMoedas(), instituicaoService.getInstituicaoById(professor.instituicaoId()), professor.email(), professor.nome(), professor.senha()));

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newProfessor.getId()).toUri();

        return ResponseEntity.created(uri).build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<Professor> update(@PathVariable Long id, @PathVariable Professor professor) {

       professor.setId(id);
       this.professorService.updateProfessor(professor);

         return ResponseEntity.noContent().build();

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@RequestParam Long id) {
    
        this.professorService.deleteProfessor(id);

        return ResponseEntity.noContent().build();
    }

    @Operation(description = "Login de professor")
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO professor) {
        Optional<Professor> newProfessor = professorService.login(professor.getEmail(), professor.getSenha());
    
        if (newProfessor.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // Retorno para login falho
        }
    
        // Criando a resposta com o DTO
        LoginResponseDTO response = new LoginResponseDTO(
            newProfessor.get().getId(),
            newProfessor.get().getTipoUsuario()
        );
    
        return ResponseEntity.ok(response);
    }

    @GetMapping()
    public ResponseEntity<List<Professor>> getAllProfessores() {

        List<Professor> professores = professorService.getAllProfessores();

        return ResponseEntity.ok(professores);
    }


}
