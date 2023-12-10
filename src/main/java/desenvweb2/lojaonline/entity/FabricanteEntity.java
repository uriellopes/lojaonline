package desenvweb2.lojaonline.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.transaction.annotation.Transactional;

@Table(name = "fabricante")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Transactional
public class FabricanteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    @NotEmpty
    private String name;

    public FabricanteEntity(FabricanteEntity fabricante){
        this.name = fabricante.name;
    }

    public void AtualizarFabricante(FabricanteEntity dados){
        if(dados.getName() != null){
            this.name = dados.getName();
        }
    }
}
