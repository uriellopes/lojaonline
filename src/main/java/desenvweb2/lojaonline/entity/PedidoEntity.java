package desenvweb2.lojaonline.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "pedido")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PedidoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long  id_usuario;
    private long  id_produto;

    public PedidoEntity(PedidoEntity pedido){
        this.id_usuario = pedido.getId_usuario();
        this.id_produto = pedido.getId_produto();
    }

    public void AtualizarPedido(PedidoEntity dados) {
        if(dados.getId_usuario() != 0){
            this.id_usuario = dados.getId_usuario();
        }
        if(dados.getId_produto() != 0){
            this.id_produto = dados.getId_produto();
        }
    }
}
