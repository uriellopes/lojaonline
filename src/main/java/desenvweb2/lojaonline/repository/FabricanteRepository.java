package desenvweb2.lojaonline.repository;

import desenvweb2.lojaonline.model.FabricanteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FabricanteRepository extends JpaRepository<FabricanteEntity, Long> {
    FabricanteEntity findByName(String name);
}
