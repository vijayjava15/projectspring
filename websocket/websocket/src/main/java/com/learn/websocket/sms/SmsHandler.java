package com.learn.websocket.sms;

import com.learn.websocket.notification.NotificationHandler;
import com.learn.websocket.user.User;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SmsHandler implements NotificationHandler {

    @Value("${twilio.sms-from}")
    private String smsFrom;

    public String sendSms(String to, String body) {
        if (StringUtils.isBlank(to)) {
            throw new IllegalArgumentException("to is required");
        }
        if (StringUtils.isBlank(body)) {
            throw new IllegalArgumentException("message body is required");
        }
        Message message = Message.creator(
                new PhoneNumber(to),
                new PhoneNumber(smsFrom),
                body
        ).create();
        return message.getSid();
    }

    @Override
    public void process(User user, String message) {
     /***  No Impl Right now because of twillio cost  */
    }
}
