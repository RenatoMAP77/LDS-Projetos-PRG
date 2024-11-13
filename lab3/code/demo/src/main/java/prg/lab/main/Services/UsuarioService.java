
package prg.lab.main.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import prg.lab.main.Models.Usuario;
import prg.lab.main.Repositories.AlunoRepository;
import prg.lab.main.Repositories.EmpresaParceiraRepository;
import prg.lab.main.Repositories.ProfessorRepository;

@Service
public class UsuarioService {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private EmpresaParceiraRepository empresaRepository;

    public Usuario autenticar(String email, String senha) {
        Optional<? extends Usuario> usuarioOpt;

        // Tenta encontrar o usuário em cada repositório específico usando email e senha
        usuarioOpt = alunoRepository.findByEmailAndSenha(email, senha);
        if (usuarioOpt.isPresent()) {
            return usuarioOpt.get();
        }

        usuarioOpt = professorRepository.findByEmailAndSenha(email, senha);
        if (usuarioOpt.isPresent()) {
            return usuarioOpt.get();
        }

        usuarioOpt = empresaRepository.findByEmailAndSenha(email, senha);
        if (usuarioOpt.isPresent()) {
            return usuarioOpt.get();
        }

        // Se nenhum usuário foi encontrado, lançar uma exceção
        throw new RuntimeException("Usuário não encontrado");
    }
}
