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
import prg.lab.main.Services.InstituicaoService;
import prg.lab.main.Util.DTOs.AlunoDTO;
import prg.lab.main.Util.DTOs.LoginDTO;

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

    @Autowired
    private InstituicaoService instituicaoService;

    @Operation(description = "Busca um aluno pelo id")
    @GetMapping("/{id}")
    public ResponseEntity<Aluno> getMethodName(@PathVariable Long id) {
        return ResponseEntity.ok(alunoService.getAlunoById(id));
    }

    @Operation(description = "Cria um aluno")
    @PostMapping()
    public ResponseEntity<Aluno> create(@RequestBody AlunoDTO alunoDto) {
        
       Aluno aluno = this.alunoService.createAluno(new Aluno(alunoDto.cpf(), alunoDto.rg(), alunoDto.endereco(),
        alunoDto.curso(), alunoDto.nome(), alunoDto.email(), alunoDto.senha(),
         instituicaoService.getInstituicaoById(alunoDto.instituicaoId())));
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
    public ResponseEntity<Aluno> login(@RequestBody LoginDTO loginDTO) {
        return ResponseEntity.ok(alunoService.login(loginDTO.email(), loginDTO.senha()));
    }

}
