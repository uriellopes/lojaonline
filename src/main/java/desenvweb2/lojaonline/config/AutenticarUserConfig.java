package desenvweb2.lojaonline.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import desenvweb2.lojaonline.entity.UsuarioEntity;
import desenvweb2.lojaonline.repository.UsuarioRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.PostMapping;


import javax.sql.DataSource;
import java.util.Optional;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class AutenticarUserConfig implements  UserDetailsService{

    @Autowired
    private UsuarioRepository repository;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsuarioEntity usuario = repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Dados inválidos!"));

        return User.withUsername(usuario.getUsername())
                .password(usuario.getPassword())
                .roles("ADMIN")
                .build();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        return http.securityMatcher("/**")
                .authorizeHttpRequests(a -> a
                        .anyRequest().hasRole("ADMIN")
                )
                .formLogin(withDefaults())
                .httpBasic(withDefaults())
                .build();
    }

}