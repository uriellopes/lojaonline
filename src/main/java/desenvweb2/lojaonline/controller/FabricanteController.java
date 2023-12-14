package desenvweb2.lojaonline.controller;

import desenvweb2.lojaonline.DTO.DadosFabricanteDTO;
import desenvweb2.lojaonline.model.FabricanteEntity;
import desenvweb2.lojaonline.service.FabricanteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fabricante")
public class FabricanteController {
    @Autowired
    private FabricanteService fabricanteService;

    @GetMapping
    public List<FabricanteEntity> listarTodosFabricantes() {
        return fabricanteService.listarTodos();
    }

    @PostMapping
    public ResponseEntity<?> cadastrarFabricante(@RequestBody @Valid DadosFabricanteDTO dados) {
        try {
            return ResponseEntity.ok(fabricanteService.criarFabricante(dados.name()));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarFabricante(@PathVariable Long id, @RequestBody @Valid DadosFabricanteDTO dados) {
        try {
            return ResponseEntity.ok(fabricanteService.atualizarFabricante(id, dados.name()));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public String deletarFabricante(@PathVariable Long id) {
        fabricanteService.deletarFabricante(id);
        return "Fabricante deletado com sucesso!";
    }
}
