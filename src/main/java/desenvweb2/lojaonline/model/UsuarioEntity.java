package desenvweb2.lojaonline.model;

import desenvweb2.lojaonline.DTO.AtualizarUsuarioDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Table(name="usuario")
@Entity(name="Usuario")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String login;
    @NotNull
    private String password;
    @NotNull
    @Enumerated(EnumType.STRING)
    private RoleEnum role;

    public UsuarioEntity(String name, String login, String password, RoleEnum role) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.role == RoleEnum.ADMIN) {
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        }else if(this.role == RoleEnum.USER) {
            return List.of(new SimpleGrantedAuthority("ROLE_USER"));
        } else {
            return List.of(new SimpleGrantedAuthority("ROLE_LOJA"));
        }
    }

    @Override
    public String getUsername() {
        return this.login;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }

    public UsuarioEntity atualizarUsuario(AtualizarUsuarioDTO dados) {
        if(dados.name() != null){
            this.name = dados.name();
        }if(dados.login() != null){
            this.login = dados.login();
        }if(dados.password() != null){
            this.password = dados.password();
        }if(dados.role() != null){
            this.role = dados.role();
        }
        return this;
    }
}
