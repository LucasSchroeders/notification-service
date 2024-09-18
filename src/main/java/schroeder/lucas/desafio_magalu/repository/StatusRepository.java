package schroeder.lucas.desafio_magalu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import schroeder.lucas.desafio_magalu.entity.Status;

public interface StatusRepository extends JpaRepository<Status, Long> {
}
