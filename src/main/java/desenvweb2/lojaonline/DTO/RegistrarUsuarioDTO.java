package desenvweb2.lojaonline.DTO;

import desenvweb2.lojaonline.model.RoleEnum;
import jakarta.validation.constraints.NotNull;

public record RegistrarUsuarioDTO(@NotNull String name, @NotNull String login, @NotNull String password, @NotNull RoleEnum role) {
}
