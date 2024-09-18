package schroeder.lucas.desafio_magalu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import schroeder.lucas.desafio_magalu.entity.Channel;

public interface ChannelRepository extends JpaRepository<Channel, Long> {

}
