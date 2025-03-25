package win.TIA202.www.web.team.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import win.TIA202.www.web.service.impl.SesSender;

@Service
public class SendMailService {
    @Autowired
    private SesSender sesSender;

    public void sendTestEmail () {
        String recipient = "minhung0302@gmail.com";
        String subject = "Test Email";
        String body = "Verication code: 123456";
        
        sesSender.send(recipient, subject, body);
    }
}
