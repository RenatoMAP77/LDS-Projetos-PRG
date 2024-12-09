package prg.lab.main.Controllers;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.swagger.v3.oas.annotations.Operation;
import prg.lab.main.Models.EmpresaParceira;
import prg.lab.main.Services.EmpresaParceiraService;
import prg.lab.main.Util.DTOs.EmpresaParceiraDTO;
import prg.lab.main.Util.DTOs.LoginRequestDTO;
import prg.lab.main.Util.DTOs.LoginResponseDTO;

@RestController
@RequestMapping("/empresaParceira")
public class EmpresaParceiraController {

    @Autowired
    private EmpresaParceiraService empresaParceiraService;

    @GetMapping("/{id}")
    public ResponseEntity<EmpresaParceira> findById(@PathVariable Long id) {
        EmpresaParceira empresaParceira = empresaParceiraService.findById(id);
        
        return ResponseEntity.ok().body(empresaParceira);
    }

    @Operation(description = "Busca uma empresa pelo email")
    @GetMapping("/email/{email}")
    public ResponseEntity<EmpresaParceira> findByEmail(@PathVariable String email) {
        EmpresaParceira empresa = this.empresaParceiraService.findByEmail(email);

        return ResponseEntity.ok().body(empresa);
    }

    @Operation(description = "Busca todas as empresas")
    @GetMapping
    public ResponseEntity<Iterable<EmpresaParceira>> findAll() {
        Iterable<EmpresaParceira> empresas = this.empresaParceiraService.findAll();

        return ResponseEntity.ok().body(empresas);
    }

    @Operation(description = "Cria uma nova empresa")
    @PostMapping
    public ResponseEntity<String> create(@RequestBody EmpresaParceiraDTO empresa) {
        EmpresaParceira empresaParceira = new EmpresaParceira(
            empresa.cnpj(),
            empresa.nome(),
            empresa.email(),
            empresa.senha(),
            empresa.descricao()
        );
        this.empresaParceiraService.create(empresaParceira);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(empresaParceira.getId())
                .toUri();

        return ResponseEntity.ok(empresaParceira.getTipoUsuario().toString());
    }

    @Operation(description = "Exclui uma empresa pelo ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.empresaParceiraService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @Operation(description = "Atualiza uma empresa existente")
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody EmpresaParceira empresa) {
        empresa.setId(id);
        this.empresaParceiraService.update(empresa);

        return ResponseEntity.noContent().build();
    }

    @Operation(description = "Faz login de uma empresa", summary = "Faz login de uma empresa")
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        Optional<EmpresaParceira> empresa = empresaParceiraService.login(
            loginRequestDTO.getEmail(),
            loginRequestDTO.getSenha()
        );

        if (empresa.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // Retorno caso login falhe
        }

        LoginResponseDTO response = new LoginResponseDTO(
            empresa.get().getId(),
            empresa.get().getTipoUsuario()
        );

        return ResponseEntity.ok(response);
    }


    // @Operation(description = "Atualiza a senha de uma empresa", summary = "Atualiza a senha de uma empresa")
    // @PatchMapping("/{id}/senha")
    // public ResponseEntity<Void> updateSenha(@PathVariable Long id, @RequestBody String senha) {
    //     this.empresaParceiraService.mudarSenha(senha, id);
    //     return ResponseEntity.noContent().build();
    // }
}
