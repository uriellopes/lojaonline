package desenvweb2.lojaonline.controller;



import desenvweb2.lojaonline.entity.ProdutoEntity;
import desenvweb2.lojaonline.repository.ProdutoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ProdutoEntity")
public class ProdutoController {
    @Autowired
    ProdutoRepository repository;

    @GetMapping("/{id}")
    @Transactional
    public ProdutoEntity getById(@PathVariable long id){
        return repository.getReferenceById(id);
    }

    @PutMapping
    @Transactional
    public void putProduto(@RequestBody @Valid ProdutoEntity dados){
        ProdutoEntity perfil =  repository.getReferenceById(dados.getId());
        perfil.AtualizarProduto(dados);
    }

    @PostMapping
    @Transactional
    public void postProduto(@RequestBody @Valid ProdutoEntity dados){
        repository.save(new ProdutoEntity(dados));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deleteProduto(@PathVariable long id){
        repository.deleteById(id);
    }
}
