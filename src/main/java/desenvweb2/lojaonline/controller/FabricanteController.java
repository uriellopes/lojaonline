package desenvweb2.lojaonline.controller;

import desenvweb2.lojaonline.entity.FabricanteEntity;
import desenvweb2.lojaonline.repository.FabricanteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/FabricanteEntity")
@Transactional
public class FabricanteController {
    @Autowired
    FabricanteRepository repository;

    @GetMapping("/{id}")
    @Transactional
    public FabricanteEntity getById(@PathVariable long id){
        return repository.getReferenceById(id);
    }

    @PutMapping
    @Transactional
    public void putFabricante(@RequestBody @Valid FabricanteEntity dados){
        FabricanteEntity fabricante =  repository.getReferenceById(dados.getId());
        fabricante.AtualizarFabricante(dados);
    }

    @PostMapping
    @Transactional
    public void postFabricante(@RequestBody @Valid FabricanteEntity dados){
        repository.save(new FabricanteEntity(dados));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deleteFabricante(@PathVariable long id){
        repository.deleteById(id);
    }
}
