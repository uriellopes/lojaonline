package desenvweb2.lojaonline.service;

import desenvweb2.lojaonline.DTO.RegistrarProdutoDTO;
import desenvweb2.lojaonline.model.FabricanteEntity;
import desenvweb2.lojaonline.model.ProdutoEntity;
import desenvweb2.lojaonline.model.UsuarioEntity;
import desenvweb2.lojaonline.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {
    @Autowired
    ProdutoRepository produtoRepository;
    @Autowired
    UsuarioService usuarioService;
    @Autowired
    FabricanteService fabricanteService;

    public List<ProdutoEntity> listarTodos() {
        return produtoRepository.findAll();
    }

    public ProdutoEntity cadastrarProduto(RegistrarProdutoDTO dados) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UsuarioEntity loja = usuarioService.getUserIdByLogin(authentication.getName());
        FabricanteEntity fabricante = fabricanteService.findByName(dados.fabricante_name());

        if( fabricante != null ) {
            return produtoRepository.save(new ProdutoEntity(dados.name(), fabricante, loja));
        }

        FabricanteEntity novoFabricante = fabricanteService.criarFabricante(dados.fabricante_name());
        return produtoRepository.save(new ProdutoEntity(dados.name(), novoFabricante, loja));
    }
}
