package desenvweb2.lojaonline.service;

import desenvweb2.lojaonline.DTO.RegistrarPedidoDTO;
import desenvweb2.lojaonline.model.PedidoEntity;
import desenvweb2.lojaonline.model.ProdutoEntity;
import desenvweb2.lojaonline.model.UsuarioEntity;
import desenvweb2.lojaonline.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {
    @Autowired
    PedidoRepository pedidoRepository;
    @Autowired
    UsuarioService usuarioService;
    @Autowired
    ProdutoService produtoService;

    public List<PedidoEntity> listarPedidos() {
        return pedidoRepository.findAll();
    }

    public List<PedidoEntity> listarPedidosUsuario() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UsuarioEntity usuario = usuarioService.getUserIdByLogin(authentication.getName());

        return pedidoRepository.findByUsuarioId(usuario.getId());
    }

    public PedidoEntity cadastrarPedido(RegistrarPedidoDTO dados) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UsuarioEntity usuario = usuarioService.getUserIdByLogin(authentication.getName());
        Optional<ProdutoEntity> produto = produtoService.listarById(dados.id_produto());

        if( produto.isEmpty()) {
            throw new RuntimeException("O produto não existe!");
        }

        return pedidoRepository.save(new PedidoEntity(dados.quantidade(), usuario, produto.get()));
    }
}
