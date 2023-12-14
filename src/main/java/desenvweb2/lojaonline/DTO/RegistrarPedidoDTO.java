package desenvweb2.lojaonline.DTO;

import jakarta.validation.constraints.NotNull;

public record RegistrarPedidoDTO(@NotNull int quantidade, @NotNull Long id_produto) {
}
