package desenvweb2.lojaonline.controller;

import desenvweb2.lojaonline.DTO.RegistrarPedidoDTO;
import desenvweb2.lojaonline.model.PedidoEntity;
import desenvweb2.lojaonline.service.PedidoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedido")
public class PedidoController {
    @Autowired
    PedidoService pedidoService;

    @GetMapping
    public List<PedidoEntity> listarTodosPedidos() {
        return pedidoService.listarPedidos();
    }

    @GetMapping("/meus-pedidos")
    public List<PedidoEntity> listarMeusPedidos() {
        return pedidoService.listarPedidosUsuario();
    }

    @PostMapping
    public ResponseEntity<?> cadastrarPedido(@RequestBody @Valid RegistrarPedidoDTO dados) {
        try {
            return ResponseEntity.ok(pedidoService.cadastrarPedido(dados));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
