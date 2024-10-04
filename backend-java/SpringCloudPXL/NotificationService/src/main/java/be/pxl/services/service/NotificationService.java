package be.pxl.services.service;

import be.pxl.services.domain.Notification;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    public void sendMessage(Notification notification) {
        System.out.println("Receiving information");
        System.out.printf("Sending ... {%s}%n", notification.getMessage());
        System.out.printf("To ... {%s}%n", notification.getTo());

    }
}
