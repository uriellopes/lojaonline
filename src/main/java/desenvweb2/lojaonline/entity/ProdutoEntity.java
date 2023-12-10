package desenvweb2.lojaonline.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "produto")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    @NotEmpty
    private String name;
    @NotNull
    @NotEmpty
    @Positive
    private double preco;
    @Positive
    @NotNull
    @NotEmpty
    private long fabricante_id;

    public ProdutoEntity(ProdutoEntity produto){
        this.name = produto.name;
        this.preco = produto.preco;
        this.fabricante_id = produto.fabricante_id;
    }

    public void AtualizarProduto(ProdutoEntity dados) {
        if(dados.getName() != null){
            this.name = dados.getName();
        }
        if(dados.getPreco() != 0.0){
            this.preco = dados.getPreco();
        }
        if(dados.getFabricante_id() != 0){
            this.fabricante_id = dados.getFabricante_id();
        }
    }
}
