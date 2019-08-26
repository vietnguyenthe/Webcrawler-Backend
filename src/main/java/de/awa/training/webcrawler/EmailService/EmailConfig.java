package de.awa.training.webcrawler.EmailService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EmailConfig {

    @Value("${spring.mail.host}")
    private String host;
    private String port;
    private String username;
    private String password;
}
