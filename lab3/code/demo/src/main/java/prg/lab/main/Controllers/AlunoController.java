package prg.lab.main.Controllers;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.swagger.v3.oas.annotations.Operation;
import prg.lab.main.Models.Aluno;
import prg.lab.main.Services.AlunoService;
import prg.lab.main.Services.InstituicaoService;
import prg.lab.main.Util.DTOs.AlunoDTO;
import prg.lab.main.Util.DTOs.LoginRequestDTO;
import prg.lab.main.Util.DTOs.LoginResponseDTO;

@RestController
@RequestMapping("/aluno")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @Autowired
    private InstituicaoService instituicaoService;

    @Operation(description = "Busca um aluno pelo ID")
    @GetMapping("/{id}")
    public ResponseEntity<Aluno> getAlunoById(@PathVariable Long id) {
        return ResponseEntity.ok(alunoService.getAlunoById(id));
    }

    @Operation(description = "Cria um aluno")
    @PostMapping
    public ResponseEntity<Aluno> create(@RequestBody AlunoDTO alunoDto) {

        Aluno aluno = alunoService.createAluno(
            new Aluno(
                alunoDto.cpf(),
                alunoDto.rg(),
                alunoDto.endereco(),
                alunoDto.curso(),
                alunoDto.nome(),
                alunoDto.email(),
                alunoDto.senha(),
                alunoDto.saldoMoedas(),
                instituicaoService.getInstituicaoById(alunoDto.instituicaoId())
            )
        );

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(aluno.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @Operation(description = "Atualiza um aluno pelo ID")
    @PutMapping("/{id}")
    public ResponseEntity<Aluno> update(@PathVariable("id") Long id, @RequestBody Aluno aluno) {

        aluno.setId(id);
        alunoService.updateAluno(aluno);

        return ResponseEntity.noContent().build();

    }

    @Operation(description = "Exclui um aluno pelo ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {

        alunoService.deleteAluno(id);

        return ResponseEntity.noContent().build();
    }

    @Operation(description = "Busca todos os alunos")
    @GetMapping
    public ResponseEntity<List<Aluno>> getAllAlunos() {

        List<Aluno> alunos = alunoService.getAllAlunos();

        return ResponseEntity.ok(alunos);

    }

    @Operation(description = "Login de aluno")
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO aluno) {
        Optional<Aluno> newAluno = alunoService.login(aluno.getEmail(), aluno.getSenha());

        if (newAluno.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // Retorno para login falho
        }

        LoginResponseDTO response = new LoginResponseDTO(
            newAluno.get().getId(),
            newAluno.get().getTipoUsuario()
        );

        return ResponseEntity.ok(response);
        
    }

}
