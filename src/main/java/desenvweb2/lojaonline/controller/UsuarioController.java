package desenvweb2.lojaonline.controller;

import desenvweb2.lojaonline.DTO.AtualizarUsuarioDTO;
import desenvweb2.lojaonline.DTO.RegistrarUsuarioDTO;
import desenvweb2.lojaonline.model.UsuarioEntity;
import desenvweb2.lojaonline.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    UsuarioService usuarioService;

    @GetMapping
    public List<UsuarioEntity> listar() {
        return usuarioService.listarUsuarios();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listarUsuario(@PathVariable Long id) {
        return usuarioService.findUserById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> cadastrarUsuario(@RequestBody @Valid RegistrarUsuarioDTO newUser) {
        try {
            return ResponseEntity.ok(usuarioService.cadastrarUsuario(newUser));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarUsuario(@PathVariable Long id, @RequestBody @Valid AtualizarUsuarioDTO dados) {
        try {
            return ResponseEntity.ok().body(usuarioService.atualizarUsuario(id, dados));
        }catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public String deletarUsuario(@PathVariable Long id) {
        usuarioService.deleteUsuario(id);
        return "Usuário deletado com sucesso!";
    }
}
