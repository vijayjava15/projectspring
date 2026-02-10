package com.learn.websocket.whatsapp;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class WhatsAppHandler {

    @Value("${twilio.whatsapp-from}")
    private String whatsappFrom;

    public String sendWhatsApp(String to, String body) {
        if (StringUtils.isBlank(to)) {
            throw new IllegalArgumentException("to is required");
        }
        if (StringUtils.isBlank(body)) {
            throw new IllegalArgumentException("message body is required");
        }

        Message message = Message.creator(
                new PhoneNumber(normalize(to)),
                new PhoneNumber(normalize(whatsappFrom)),
                body
        ).create();
        return message.getSid();
    }

    private String normalize(String number) {
        if (StringUtils.startsWithIgnoreCase(number, "whatsapp:")) {
            return number;
        }
        return "whatsapp:" + number;
    }
}
