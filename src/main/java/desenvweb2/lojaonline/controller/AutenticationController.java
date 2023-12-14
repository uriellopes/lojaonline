package desenvweb2.lojaonline.controller;

import desenvweb2.lojaonline.DTO.AutenticationDTO;
import desenvweb2.lojaonline.DTO.LoginResponseDTO;
import desenvweb2.lojaonline.DTO.RegistrarUsuarioDTO;
import desenvweb2.lojaonline.model.UsuarioEntity;
import desenvweb2.lojaonline.service.TokenService;
import desenvweb2.lojaonline.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AutenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AutenticationDTO data){
        var usuarioSenha = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = authenticationManager.authenticate(usuarioSenha);
        var token = tokenService.generateToken((UsuarioEntity) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/registrar")
    public ResponseEntity<?> registrarUsuario(@RequestBody @Valid RegistrarUsuarioDTO newUser) {
        try {
            return ResponseEntity.ok(usuarioService.cadastrarUsuario(newUser));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
