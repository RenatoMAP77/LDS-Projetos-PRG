package prg.lab.main.Controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.swagger.v3.oas.annotations.Operation;

import prg.lab.main.Models.Instituicao;
import prg.lab.main.Services.InstituicaoService;
import prg.lab.main.Util.DTOs.InstituicaoDTO;

@RestController
@RequestMapping("/instituicao")
public class InstituicaoController {

    @Autowired
    private InstituicaoService instituicaoService;

    @Operation(description = "Busca uma instituição pelo ID")
    @GetMapping("/{id}")
    public ResponseEntity<Instituicao> findById(@PathVariable Long id) {

        return ResponseEntity.ok(instituicaoService.getInstituicaoById(id));
        
    }

    @Operation(description = "Busca todas as instituições")
    @GetMapping
    public ResponseEntity<Iterable<Instituicao>> findAll() {
        return ResponseEntity.ok(instituicaoService.findAll());
    }

    @Operation(description = "Cria uma nova instituição")
    @PostMapping
    public ResponseEntity<Instituicao> create(@RequestBody InstituicaoDTO instituicaoDTO) {

        if (instituicaoDTO == null) {

            System.out.println("instituicaoDTO é null!");

        } else {

            System.out.println("Instituição DTO Nome: " + instituicaoDTO.nome());

        }

        Instituicao instituicao = instituicaoService.createInstituicao(
            new Instituicao(instituicaoDTO.nome())
        );

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(instituicao.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }
}
