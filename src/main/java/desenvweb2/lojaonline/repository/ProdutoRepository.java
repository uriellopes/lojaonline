package desenvweb2.lojaonline.repository;

import desenvweb2.lojaonline.entity.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<ProdutoEntity,Long> {
}
