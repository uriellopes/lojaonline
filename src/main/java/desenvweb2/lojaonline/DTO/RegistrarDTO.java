package desenvweb2.lojaonline.DTO;

import desenvweb2.lojaonline.model.RoleEnum;

public record RegistrarDTO(String name, String login, String password, RoleEnum role) {
}
