package schroeder.lucas.desafio_magalu.controller.DTO;

import schroeder.lucas.desafio_magalu.entity.Channel;
import schroeder.lucas.desafio_magalu.entity.Notification;
import schroeder.lucas.desafio_magalu.entity.Status;

import java.time.LocalDateTime;

public record ScheduleNotificationDto(LocalDateTime dateTime,
                                      String destination,
                                      String message,
                                      Channel.Values channel) {

    public Notification toNotification() {
        return new Notification(
            dateTime,
            destination,
            message,
            channel.toChannel(),
            Status.Values.PENDING.toStatus()
        );
    }
}
