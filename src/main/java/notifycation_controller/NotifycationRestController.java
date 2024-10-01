package notifycation_controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class NotifycationRestController {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @PostMapping("/send-notification")
    public String sendNotification() {
        messagingTemplate.convertAndSend("/topic/notifications/thanhtrung", new Notification("Hello tu server"));
        return "Notification sent!";
    }
}
