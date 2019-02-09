package bg.sofia.fmi.uni.clubhub.mail;

import static java.lang.String.format;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import bg.sofia.fmi.uni.clubhub.model.Club;
import bg.sofia.fmi.uni.clubhub.model.Customer;
import bg.sofia.fmi.uni.clubhub.model.Event;

@Component
public class CustomMailSender implements IMailSender {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM-dd-yyyy");

    private static final String MAIL_TEMPLATE = //
            "Hello Dear %s %s,%n%nThere is a new event at club '%s'.%n%s%n%nBest Regards,%nClubHub Corp";

    private final JavaMailSender mailSender;

    @Autowired
    public CustomMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendEventNotification(Event event, Club club, Customer customer) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(customer.getEmail());
        message.setSubject(format("ClubHub New Event - %s on %s", event.getName(), DATE_FORMAT.format(event.getDate())));
        message.setText(format(MAIL_TEMPLATE, customer.getFirstName(), customer.getLastName(), //
                club.getAddress(), event.getDescription()));
        mailSender.send(message);
    }
}
