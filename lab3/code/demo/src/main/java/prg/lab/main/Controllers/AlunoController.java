package prg.lab.main.Controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.swagger.v3.oas.annotations.Operation;
import prg.lab.main.Models.Aluno;
import prg.lab.main.Services.AlunoService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/aluno")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @Operation(description = "Busca um aluno pelo id")
    @GetMapping("/{id}")
    public ResponseEntity<Aluno> getMethodName(@RequestParam Long id) {
        return ResponseEntity.ok(alunoService.getAlunoById(id));
    }

    @Operation(description = "Cria um aluno")
    @PostMapping()
    public ResponseEntity<Aluno> create(@RequestParam Aluno aluno) {
        this.alunoService.createAluno(aluno);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(aluno.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @Operation(description = "Atualiza um aluno pelo id")
    @PutMapping("/{id}")
    public ResponseEntity<Aluno> update(@PathVariable("id") Long id, @RequestBody Aluno aluno) {
        aluno.setId(id);
        this.alunoService.updateAluno(aluno);
        return ResponseEntity.noContent().build();

    }

    @Operation(description = "Exclui um aluno pelo id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        this.alunoService.deleteAluno(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(description = "Busca todos os alunos")
    @GetMapping
    public ResponseEntity<List<Aluno>> getAllAlunos() {
        List<Aluno> alunos = alunoService.getAllAlunos();
        return ResponseEntity.ok(alunos);
    }

    @Operation(description = "Login de aluno")
    @GetMapping("/login")
    public ResponseEntity<Aluno> login(@RequestParam String email, @RequestParam String senha) {
        return ResponseEntity.ok(alunoService.login(email, senha));
    }

}
