package desenvweb2.lojaonline.controller;

import desenvweb2.lojaonline.entity.PerfilEntity;
import desenvweb2.lojaonline.repository.PerfilRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/PerfilEntity")
public class PerfilController {
    @Autowired
    PerfilRepository repository;

    @GetMapping("/{id}")
    @Transactional
    public PerfilEntity getById(@PathVariable long id){
        return repository.getReferenceById(id);
    }

    @PutMapping
    @Transactional
    public void putPerfil(@RequestBody @Valid PerfilEntity dados){
        PerfilEntity perfil =  repository.getReferenceById(dados.getId());
        perfil.AtualizarPedido(dados);
    }

    @PostMapping
    @Transactional
    public void postPerfil(@RequestBody @Valid PerfilEntity dados){
        repository.save(new PerfilEntity(dados));
        //repository.save(new PerfilEntity(dados));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deletePerfil(@PathVariable long id){
        repository.deleteById(id);
    }
}
