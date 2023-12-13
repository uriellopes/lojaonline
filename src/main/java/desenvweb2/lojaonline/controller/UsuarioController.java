package desenvweb2.lojaonline.controller;

import desenvweb2.lojaonline.model.UsuarioEntity;
import desenvweb2.lojaonline.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    UsuarioService usuarioService;

    public UsuarioEntity cadastrar(@RequestBody @Valid UsuarioEntity newUser) {
        return usuarioService.cadastrarUsuario(newUser);
    }
}
