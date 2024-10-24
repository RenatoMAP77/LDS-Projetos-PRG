package prg.lab.main.Controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.swagger.v3.oas.annotations.Operation;
import prg.lab.main.Models.EmpresaParceira;
import prg.lab.main.Services.EmpresaParceiraService;

@RestController
@RequestMapping("/empresaParceira")
public class EmpresaParceiraController {
    
    @Autowired
    private EmpresaParceiraService empresaParceiraService;
    
    @GetMapping("/{id}")
    public ResponseEntity<EmpresaParceira> findById (@PathVariable Long id) {
        EmpresaParceira empresaParceira = empresaParceiraService.findById(id);
        return ResponseEntity.ok().body(empresaParceira);
    }

     @Operation(description = "Busca um empresa pelo email")
    @GetMapping("/email/{email}")
    public ResponseEntity<EmpresaParceira> findByEmail(@PathVariable String email){
        EmpresaParceira empresa = this.empresaParceiraService.findByEmail(email);
        return ResponseEntity.ok().body(empresa);
    }


    @Operation(description = "Busca todos as empresas")
    @GetMapping
    public ResponseEntity<Iterable<EmpresaParceira>> findAll(){
        Iterable<EmpresaParceira> empresas = this.empresaParceiraService.findAll();
        return ResponseEntity.ok().body(empresas);
    }
     
    @Operation(description = "Cria uma nova empresa")
    @PostMapping
    public ResponseEntity<Void> create(@RequestBody EmpresaParceira empresa){
         this.empresaParceiraService.create(empresa);
         URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}").buildAndExpand(empresa.getId()).toUri() ;
         return ResponseEntity.created(uri).build();
    }

    @Operation(description = "Exclui uma empresa pelo id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
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

    @Operation(description="Faz login de uma empresa",summary = "Faz login de uma empresa")
    @GetMapping("/login")
    public ResponseEntity<EmpresaParceira> login(@RequestBody String email, @RequestBody String senha){
        EmpresaParceira empresa = this.empresaParceiraService.login(email, senha);
        return ResponseEntity.ok().body(empresa);
    }

    // @Operation(description="Atualiza a senha de uma empresa",summary = "Atualiza a senha de uma empresa")
    // @PatchMapping("/{id}/senha")
    // public ResponseEntity<Void> updateSenha(@PathVariable Long id, @RequestBody  String senha)
    // {
    //     this.empresaParceiraService.mudarSenha(senha,id);
    //     return ResponseEntity.noContent().build();
    // }

}
