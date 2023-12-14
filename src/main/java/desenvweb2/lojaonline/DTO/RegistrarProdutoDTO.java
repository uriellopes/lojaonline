package desenvweb2.lojaonline.DTO;

import jakarta.validation.constraints.NotNull;

public record RegistrarProdutoDTO(@NotNull String name, @NotNull double preco, @NotNull String fabricante_name) {
}
