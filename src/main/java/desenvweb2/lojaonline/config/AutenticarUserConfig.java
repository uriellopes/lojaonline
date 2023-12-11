package desenvweb2.lojaonline.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import desenvweb2.lojaonline.entity.UsuarioEntity;
import desenvweb2.lojaonline.repository.UsuarioRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


import javax.sql.DataSource;
import java.util.Optional;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class AutenticarUserConfig{

    @Autowired
    private UsuarioRepository repository;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }



    @Bean
    public UserDetailsService userDetailsService(){
        return username -> {
            Optional<UsuarioEntity> usuario = repository.findByUsername(username);
            return usuario.map(usuarioEntity -> User.withUsername(usuarioEntity.getUsername())
                    .password(usuarioEntity.getPassword())
                    .roles("ADMIN")
                    .build()).orElse(null);

        };
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