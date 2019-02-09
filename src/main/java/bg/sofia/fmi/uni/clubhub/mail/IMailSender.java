package bg.sofia.fmi.uni.clubhub.mail;

import bg.sofia.fmi.uni.clubhub.model.Club;
import bg.sofia.fmi.uni.clubhub.model.Customer;
import bg.sofia.fmi.uni.clubhub.model.Event;

public interface IMailSender {

    void sendEventNotification(Event event, Club club, Customer customer);
}
