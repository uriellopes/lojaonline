package desenvweb2.lojaonline.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name="fabricante")
@Entity(name="Fabricante")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FabricanteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String name;

    public FabricanteEntity(String name) {
        this.name = name;
    }

    public FabricanteEntity atualizarFabricante(String name) {
        this.name = name;
        return this;
    }
}
