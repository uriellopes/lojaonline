package desenvweb2.lojaonline.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name="pedido")
@Entity(name="Pedido")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PedidoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private int quantidade;
    @NotNull
    private double total;
    @NotNull
    @ManyToOne
    @JoinColumn(name="id_usuario", nullable = false)
    private UsuarioEntity usuario;
    @NotNull
    @ManyToOne
    @JoinColumn(name="id_produto", nullable = false)
    private ProdutoEntity produto;

    public PedidoEntity(int quantidade, UsuarioEntity usuario, ProdutoEntity produto) {
        this.quantidade = quantidade;
        this.total = quantidade * produto.getPreco();
        this.usuario = usuario;
        this.produto = produto;
    }
}
