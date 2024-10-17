package prg.lab.main.Controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import prg.lab.main.Models.Professor;
import prg.lab.main.Services.ProfessorService;

@RestController
@RequestMapping("/professor")
public class ProfessorController {
    
    @Autowired
    private ProfessorService professorService;

    @GetMapping("/{id}")
    public ResponseEntity<Professor> getMethodName(@RequestParam Long id) {
        return ResponseEntity.ok(professorService.getProfessorById(id));
    }

    @PostMapping()
    public ResponseEntity<Professor> create(@RequestParam Professor professor) {
        this.professorService.createProfessor(professor);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(professor.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Professor> update(@RequestParam("id") Long id, @RequestParam Professor professor) {
       professor.setId(id);
       this.professorService.updateProfessor(professor);
         return ResponseEntity.noContent().build();

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@RequestParam Long id) {
        this.professorService.deleteProfessor(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping()
    public ResponseEntity<List<Professor>> getAllProfessores() {
        List<Professor> professores = professorService.getAllProfessores();
        return ResponseEntity.ok(professores);
    }


}
