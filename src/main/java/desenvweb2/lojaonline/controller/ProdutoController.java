package desenvweb2.lojaonline.controller;

import desenvweb2.lojaonline.DTO.RegistrarProdutoDTO;
import desenvweb2.lojaonline.model.ProdutoEntity;
import desenvweb2.lojaonline.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {
    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public List<ProdutoEntity> listarTodosProdutos() {
        return produtoService.listarTodos();
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Valid RegistrarProdutoDTO dados) {
        try{
            return ResponseEntity.ok(produtoService.cadastrarProduto(dados));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
