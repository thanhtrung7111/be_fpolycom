package notifycation_controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class NotifycationController {
//    @MessageMapping("/notify")
//    @SendTo("/topic/notifications")
//    public Notification sendNotification(Notification notification) {
//        return new Notification("Thông báo: " + HtmlUtils.htmlEscape(notification.getMessage()));
//    }
}

class Notification {
    private String message;

    public Notification(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}