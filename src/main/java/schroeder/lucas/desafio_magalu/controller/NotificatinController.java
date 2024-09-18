package schroeder.lucas.desafio_magalu.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import schroeder.lucas.desafio_magalu.controller.DTO.ScheduleNotificationDto;
import schroeder.lucas.desafio_magalu.entity.Notification;
import schroeder.lucas.desafio_magalu.service.NotificationService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/notifications")
public class NotificatinController {

    private NotificationService notificationService;

    public NotificatinController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping
    public ResponseEntity<Void> scheduleNotification(@RequestBody ScheduleNotificationDto dto) {
        notificationService.scheduleNotification(dto);

        return ResponseEntity.accepted().build();
    }

    @GetMapping("/{notificationId}")
    public ResponseEntity<Notification> getNotification(@PathVariable("notificationId") Long notificationId){
        var notification = notificationService.findById(notificationId);

        if (notification.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(notification.get());
    }
    
    @GetMapping
    public ResponseEntity<List<Notification>> getAllNotifications(){
        var notification = notificationService.findAll();

        if (notification.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(notification);
    }

    @DeleteMapping("/{notificationId}")
    public ResponseEntity<Void> cancelNotification(@PathVariable("notificationId") Long notificationId){
        notificationService.cancelNotification(notificationId);
        return ResponseEntity.noContent().build();
    }

}
