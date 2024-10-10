package be.pxl.services.controller;

import be.pxl.services.domain.Notification;
import be.pxl.services.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/notification")
public class NotifcationController {
    @Autowired
    private NotificationService notificationService;

    @PostMapping
    public ResponseEntity<?> sendMessage(@RequestBody Notification notification){
        notificationService.sendMessage(notification);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
