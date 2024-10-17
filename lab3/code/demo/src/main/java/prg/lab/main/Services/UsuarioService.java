


 package prg.lab.main.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import prg.lab.main.Models.Usuario;
import prg.lab.main.Repositories.UsuarioRepository;

@Service
public class UsuarioService {

@Autowired
private UsuarioRepository usuarioRepository;

 public Usuario getUsuarioById(Long id) {
     Optional<Usuario> usuario = usuarioRepository.findById(id);
     return usuario.orElseThrow(() -> new RuntimeException("Usuario não encontrado"));
    }
    @Transactional
    public Usuario saveUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Transactional
    public void deleteUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }
    
}