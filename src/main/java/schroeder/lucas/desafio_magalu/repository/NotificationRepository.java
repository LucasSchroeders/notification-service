package schroeder.lucas.desafio_magalu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import schroeder.lucas.desafio_magalu.entity.Notification;
import schroeder.lucas.desafio_magalu.entity.Status;

import java.time.LocalDateTime;
import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByStatusInAndDateTimeBefore(List<Status> status, LocalDateTime dateTime);
}
