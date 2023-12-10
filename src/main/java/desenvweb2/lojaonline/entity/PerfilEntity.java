package desenvweb2.lojaonline.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "perfil")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PerfilEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    @NotEmpty
    private String name;

    public PerfilEntity(PerfilEntity perfil){
        this.name = perfil.name;
    }

    public void AtualizarPedido(PerfilEntity dados) {
        if(dados.getName() != null){
            this.name = dados.getName();
        }
    }
}
