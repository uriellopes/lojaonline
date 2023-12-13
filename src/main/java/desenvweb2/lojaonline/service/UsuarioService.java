package desenvweb2.lojaonline.service;

import desenvweb2.lojaonline.model.UsuarioEntity;
import desenvweb2.lojaonline.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository;

    @Transactional
    public UsuarioEntity cadastrarUsuario(UsuarioEntity newUser) {
        if( usuarioRepository.existsByLogin(newUser.getLogin())) {
            throw new RuntimeException("Login já existente. Escolha outro login.");
        }
        return usuarioRepository.save(new UsuarioEntity(newUser));
    }
}
