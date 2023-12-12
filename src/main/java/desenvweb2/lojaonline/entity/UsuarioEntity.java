package desenvweb2.lojaonline.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "usuario")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    @NotEmpty
    private String name;
    @NotNull
    @NotEmpty
    private String username;
    @NotNull
    @NotEmpty
    private String password;
    @NotNull
    @NotEmpty
    @Positive
    private long perfil_id;

    @NotNull
    @NotEmpty
    private String role;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public long getPerfil_id() {
        return perfil_id;
    }

    public long getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    public UsuarioEntity(UsuarioEntity usuario){
        this.name = usuario.name;
        this.username = usuario.username;
        this.password = usuario.password;
        this.perfil_id = usuario.perfil_id;
        this.role = usuario.role;
    }

    public void AtualizarUsuario(UsuarioEntity dados) {
        if(dados.getName() != null){
            this.name = dados.getName();
        }
        if(dados.getUsername() != null){
            this.username = dados.getUsername();
        }
        if(dados.getPassword() != null){
            this.password = dados.getPassword();
        }
        if(dados.getPerfil_id() != 0){
            this.perfil_id = dados.getPerfil_id();
        }
        if(dados.getRole() != null){
            this.role = dados.getRole();
        }
    }
}
