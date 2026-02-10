package com.learn.websocket.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("notification")
public class NotificationController {

    @Autowired
    NotificationService notificationService;

    @PostMapping("/save")
    public Object create(@RequestBody Notification notification) {
        return notificationService.create(notification);
    }

    @PutMapping("/update/{id}")
    public Object update(@PathVariable Long id, @RequestBody Notification notification) {
        return notificationService.update(id, notification);
    }

    @GetMapping("/get/{id}")
    public Object getById(@PathVariable Long id) {
        return notificationService.getById(id);
    }

    @GetMapping("/getAll")
    public Object getAll() {
        return notificationService.getAll();
    }

    @DeleteMapping("/delete/{id}")
    public Object delete(@PathVariable Long id) {
        return notificationService.delete(id);
    }
}
