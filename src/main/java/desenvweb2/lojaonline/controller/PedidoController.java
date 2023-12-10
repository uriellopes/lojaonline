package desenvweb2.lojaonline.controller;

import desenvweb2.lojaonline.entity.PedidoEntity;
import desenvweb2.lojaonline.repository.PedidoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/PedidoEntity")
public class PedidoController {
    @Autowired
    PedidoRepository repository;

    @GetMapping("/{id}")
    @Transactional
    public PedidoEntity getById(@PathVariable long id){
        return repository.getReferenceById(id);
    }

    @PutMapping
    @Transactional
    public void putPedido(@RequestBody @Valid PedidoEntity dados){
        PedidoEntity pedido =  repository.getReferenceById(dados.getId());
        pedido.AtualizarPedido(dados);
    }

    @PostMapping
    @Transactional
    public void postPedido(@RequestBody @Valid PedidoEntity dados){
        repository.save(new PedidoEntity(dados));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deletePedido(@PathVariable long id){
        repository.deleteById(id);
    }
}
