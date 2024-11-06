package prg.lab.main.Controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.swagger.v3.oas.annotations.Operation;
import prg.lab.main.Models.Vantagem;
import prg.lab.main.Services.EmpresaParceiraService;
import prg.lab.main.Services.VantagemService;
import prg.lab.main.Util.DTOs.VantagemDTO;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/vantagem")
public class VantagemController {
    @Autowired
    VantagemService vantagemService;
    @Autowired
    EmpresaParceiraService empresaParceiraService;

    @Operation(description = "Cadastra uma nova vantagem")
    @PostMapping()
    public ResponseEntity<Vantagem> cadastrarVantagem(VantagemDTO vantagemDTO) {
        Vantagem vantagem = vantagemService.createVantagem(new Vantagem(vantagemDTO.descricao(), vantagemDTO.custoEmMoedas(),
     empresaParceiraService.findById(vantagemDTO.empresaId())));
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(vantagem.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @Operation(description = "Lista todas as vantagens")
    @GetMapping()
    public ResponseEntity<List<Vantagem>> listarVantagens() {
        List<Vantagem> vantagens = vantagemService.getAllVantagens();
        return ResponseEntity.ok(vantagens);
    }


}
