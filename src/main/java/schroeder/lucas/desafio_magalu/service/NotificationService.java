package schroeder.lucas.desafio_magalu.service;

import org.springframework.stereotype.Service;
import schroeder.lucas.desafio_magalu.controller.DTO.ScheduleNotificationDto;
import schroeder.lucas.desafio_magalu.entity.Channel;
import schroeder.lucas.desafio_magalu.entity.Notification;
import schroeder.lucas.desafio_magalu.entity.Status;
import schroeder.lucas.desafio_magalu.repository.NotificationRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@Service
public class NotificationService {
    private final NotificationRepository notificationRepository;

    private MailService mailService;
    private SmsService smsService;

    public NotificationService(NotificationRepository notificationRepository, MailService mailService, SmsService smsService) {
        this.notificationRepository = notificationRepository;
        this.mailService = mailService;
        this.smsService = smsService;
    }

    public void scheduleNotification(ScheduleNotificationDto dto){
        notificationRepository.save(dto.toNotification());
    }

    public Optional<Notification> findById(Long notificationId){
        return notificationRepository.findById(notificationId);
    }

    public List<Notification> findAll(){
        return notificationRepository.findAll();
    }

    public void cancelNotification(Long notificationId){
        var notification = findById(notificationId);

        if (notification.isPresent()) {
            notification.get().setStatus(Status.Values.CANCELLED.toStatus());
            notificationRepository.save(notification.get());
        }
    }

    public void checkAndSend(LocalDateTime dateTime) {
        var notification = notificationRepository.findByStatusInAndDateTimeBefore(List.of(
            Status.Values.PENDING.toStatus(),
            Status.Values.ERROR.toStatus()
        ), dateTime);

        notification.forEach(sendNotification());
    }

    private Consumer<Notification> sendNotification() {
        return n -> {
            //TODO realizar o envio da notification
            boolean is_send = false;
            var channel = n.getChannel().getDescription();
            if (Channel.Values.EMAIL.toChannel().getDescription().equals(channel)){
                var msg = mailService.enviarMailTexto(n.getDestination(), n.getMessage());
                if (msg == "Email enviado"){
                    is_send = true;
                }
            } else if (Channel.Values.SMS.toChannel().getDescription().equals(channel)) {
                smsService.enviarSms(n.getDestination(), n.getMessage());
                is_send = true;
            }


            if (is_send == true) {
                n.setStatus(Status.Values.SUCCESS.toStatus());
                notificationRepository.save(n);
            }

        };
    }
}
