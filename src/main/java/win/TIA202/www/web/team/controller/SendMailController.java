package win.TIA202.www.web.team.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import win.TIA202.www.web.team.service.impl.SendMailService;

@RestController
@RequestMapping("/sendMail")
public class SendMailController {

    @Autowired
    private SendMailService sendMailService;

    @GetMapping("/test")
    public void sendTestEmail() {

        sendMailService.sendTestEmail();
    }
}
