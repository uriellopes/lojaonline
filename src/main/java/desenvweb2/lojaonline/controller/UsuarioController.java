package desenvweb2.lojaonline.controller;


import desenvweb2.lojaonline.entity.UsuarioEntity;
import desenvweb2.lojaonline.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/UsuarioEntity")
public class UsuarioController {

    private final UsuarioRepository repository;

    @Autowired
    public UsuarioController(UsuarioRepository repository){
        this.repository = repository;
    }

    @GetMapping("/{id}")
    @Transactional
    public UsuarioEntity getById(@PathVariable long id){
        return repository.getReferenceById(id);
    }

    @PutMapping
    @Transactional
    public void putUsuario(@RequestBody @Valid UsuarioEntity dados){
        UsuarioEntity usuario =  repository.getReferenceById(dados.getId());
        usuario.AtualizarUsuario(dados);
    }

    @PostMapping
    @Transactional
    public void postUsuario(@RequestBody @Valid UsuarioEntity dados){
        repository.save(new UsuarioEntity(dados));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deleteUsuario(@PathVariable long id){
        repository.deleteById(id);
    }
}
