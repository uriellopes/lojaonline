package desenvweb2.lojaonline.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name="produto")
@Entity(name="Produto")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String name;
    @NotNull
    @ManyToOne
    @JoinColumn(name="id_fabricante", nullable = false)
    private FabricanteEntity fabricante;
    @NotNull
    @ManyToOne
    @JoinColumn(name="id_loja", nullable = false)
    private UsuarioEntity loja;

    public ProdutoEntity(String name, FabricanteEntity fabricante, UsuarioEntity loja) {
        this.name = name;
        this.fabricante = fabricante;
        this.loja = loja;
    }
}
