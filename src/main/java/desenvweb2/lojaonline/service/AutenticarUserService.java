package desenvweb2.lojaonline.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import desenvweb2.lojaonline.entity.UsuarioEntity;
import desenvweb2.lojaonline.repository.UsuarioRepository;

import java.util.Optional;


public class AutenticarUserService implements UserDetailsService {
    @Autowired
    private UsuarioRepository UsuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<UsuarioEntity> usuario = UsuarioRepository.findByUsername(username);

        if (usuario.isEmpty()) {
            throw new RuntimeException("Usuário não encontrado");
        }

        if (username.equals("admin")) {
            return User.withUsername(usuario.get().getUsername())
                    .password(usuario.get().getPassword())
                    .roles("ADMIN")
                    .build();
        } else {
            return User.withUsername(usuario.get().getUsername())
                    .password(usuario.get().getPassword())
                    .roles("USER")
                    .build();
        }
    }
}
