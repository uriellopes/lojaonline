package desenvweb2.lojaonline.service;

import desenvweb2.lojaonline.model.FabricanteEntity;
import desenvweb2.lojaonline.repository.FabricanteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.core.RepositoryCreationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FabricanteService {
    @Autowired
    private FabricanteRepository fabricanteRepository;

    public List<FabricanteEntity> listarTodos() {
        return fabricanteRepository.findAll();
    }

    public FabricanteEntity findByName(String name) {
        return fabricanteRepository.findByName(name);
    }

    @Transactional
    public FabricanteEntity criarFabricante(String name) {
        FabricanteEntity existFabricante = fabricanteRepository.findByName(name);
        if( existFabricante != null ) {
            return existFabricante;
        }
        try {
            return fabricanteRepository.save(new FabricanteEntity(name));
        } catch (RepositoryCreationException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Transactional
    public FabricanteEntity atualizarFabricante(Long id, String name) {
        if( fabricanteRepository.findByName(name) != null ) {
            throw new RuntimeException("Esse nome de fabricante já é utilizado!");
        }
        try {
            FabricanteEntity fabricante = fabricanteRepository.getReferenceById(id);
            return fabricanteRepository.save(fabricante.atualizarFabricante(name));
        } catch (RepositoryCreationException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Transactional
    public void deletarFabricante(Long id) {
        fabricanteRepository.deleteById(id);
    }
}
