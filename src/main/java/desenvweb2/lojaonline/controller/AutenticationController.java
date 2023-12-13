package desenvweb2.lojaonline.controller;

import desenvweb2.lojaonline.DTO.AutenticationDTO;
import desenvweb2.lojaonline.DTO.LoginResponseDTO;
import desenvweb2.lojaonline.DTO.RegistrarDTO;
import desenvweb2.lojaonline.model.UsuarioEntity;
import desenvweb2.lojaonline.repository.UsuarioRepository;
import desenvweb2.lojaonline.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    private UsuarioRepository usuarioRepository;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AutenticationDTO data){
        var usuarioSenha = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = authenticationManager.authenticate(usuarioSenha);
        var token = tokenService.generateToken((UsuarioEntity) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/registrar")
    public ResponseEntity registrarUsuario(@RequestBody @Valid RegistrarDTO newUser) {
        if(usuarioRepository.findByLogin(newUser.login()) != null) {
            return ResponseEntity.badRequest().build();
        } else {
            String senhaEncriptada = new BCryptPasswordEncoder().encode(newUser.password());
            UsuarioEntity usuario = new UsuarioEntity(newUser.name(), newUser.login(), senhaEncriptada, newUser.role());
            this.usuarioRepository.save(usuario);
            return ResponseEntity.noContent().build();
        }
    }

}
