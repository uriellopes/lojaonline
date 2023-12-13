package desenvweb2.lojaonline.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Table(name="usuario")
@Entity(name="Usuario")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String login;
    private String password;
    @Enumerated(EnumType.STRING)
    private RoleEnum role;

    public UsuarioEntity(UsuarioEntity usuario) {
        this.name = usuario.name;
        this.login = usuario.login;
        this.password = new BCryptPasswordEncoder().encode(usuario.password);
        this.role = usuario.role;
    }
}
