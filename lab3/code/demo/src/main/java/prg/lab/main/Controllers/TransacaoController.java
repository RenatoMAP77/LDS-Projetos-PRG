package prg.lab.main.Controllers;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import prg.lab.main.Models.Transacao;
import prg.lab.main.Services.AlunoService;
import prg.lab.main.Services.EmpresaParceiraService;
import prg.lab.main.Services.ProfessorService;
import prg.lab.main.Services.TransacaoService;
import prg.lab.main.Util.DTOs.PremiarAlunoDTO;
import prg.lab.main.Util.DTOs.ResgatarVantagemDTO;
import prg.lab.main.Util.Enums.TransactionTypes;

@RestController
@RequestMapping("/transacao")
public class TransacaoController {
    
    @Autowired
    private TransacaoService transacaoService;

    @Autowired
    private AlunoService alunoService;

    @Autowired
    private ProfessorService professorService;

    @Autowired
    private EmpresaParceiraService empresaParceiraService;
    
    @Operation(description = "Busca uma transacao pelo id")
    @GetMapping("/{id}")
    public ResponseEntity<Transacao> findById(@PathVariable Long id) {
        return ResponseEntity.ok(transacaoService.getTransacaoById(id));
    }

    @Operation(description = "Premia um aluno")
    @PostMapping("/premiar")
    public ResponseEntity<Transacao> premiarAluno(@RequestBody PremiarAlunoDTO transacao) {

        if (transacao.quantidade()<=0) {
            throw new RuntimeException("Quantidade de moedas inválida");
        }
        return ResponseEntity.ok(transacaoService.premiarAluno(
        new Transacao(LocalDateTime.now(),
         transacao.descricao(),
          transacao.quantidade(),
          TransactionTypes.PREMIACAO_MOEDAS,
           alunoService.getAlunoById(transacao.idAluno()),
           null,
            professorService.getProfessorById(transacao.idProfessor()))));
    }

    @PostMapping("/resgatar")
    public ResponseEntity<Transacao> resgatarVantagem(@RequestBody ResgatarVantagemDTO resgatarVantagemDTO) {

        
        return ResponseEntity.ok(transacaoService.regatarVantagem(resgatarVantagemDTO));
       
    }

    @GetMapping("/history/{id}")
    public ResponseEntity<?> getHistory(@PathVariable Long id) {
        return ResponseEntity.ok(transacaoService.history(id));
    }
}
