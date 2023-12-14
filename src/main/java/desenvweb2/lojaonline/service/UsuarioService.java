package desenvweb2.lojaonline.service;

import desenvweb2.lojaonline.DTO.AtualizarUsuarioDTO;
import desenvweb2.lojaonline.DTO.RegistrarUsuarioDTO;
import desenvweb2.lojaonline.model.UsuarioEntity;
import desenvweb2.lojaonline.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.core.RepositoryCreationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService implements UserDetailsService {
    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return usuarioRepository.findByLogin(login);
    }

    public List<UsuarioEntity> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public Optional<UsuarioEntity> findUserById(Long id) {
        return usuarioRepository.findById(id);
    }

    @Transactional
    public String cadastrarUsuario(RegistrarUsuarioDTO dados) {
        if( usuarioRepository.findByLogin(dados.login()) != null ) {
            throw new RuntimeException("Esse login já existe.");
        }
        try{
            String senhaEncriptada = new BCryptPasswordEncoder().encode(dados.password());
            usuarioRepository.save(new UsuarioEntity(dados.name(),dados.login(), senhaEncriptada, dados.role()));
            return "Usuário cadastrado com sucesso!";
        } catch (RepositoryCreationException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Transactional
    public UsuarioEntity atualizarUsuario(Long id, AtualizarUsuarioDTO dados) {
        if( dados.login() != null && usuarioRepository.findByLogin(dados.login()) != null ) {
            throw new RuntimeException("Login já existe. Escolha outro login.");
        }
        try {
            UsuarioEntity usuario = usuarioRepository.getReferenceById(id);
            return usuarioRepository.save(usuario.atualizarUsuario(dados));
        } catch (RepositoryCreationException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Transactional
    public boolean deleteUsuario(Long id) {
        usuarioRepository.deleteById(id);
        return true;
    }
}
