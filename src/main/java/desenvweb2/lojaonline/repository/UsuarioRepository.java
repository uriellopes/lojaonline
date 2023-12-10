package desenvweb2.lojaonline.repository;

import desenvweb2.lojaonline.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity,Long> {
}
