package desenvweb2.lojaonline.repository;

import desenvweb2.lojaonline.entity.UsuarioEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity,Long> {
    @Transactional
    Optional<UsuarioEntity> findByUsername(String username);
}
