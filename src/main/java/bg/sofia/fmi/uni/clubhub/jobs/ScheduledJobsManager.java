package bg.sofia.fmi.uni.clubhub.jobs;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import bg.sofia.fmi.uni.clubhub.service.DiscountService;
import bg.sofia.fmi.uni.clubhub.service.EventService;

@Component
public class ScheduledJobsManager {

    @Autowired
    private DiscountService discountService;

    @Autowired
    private EventService eventService;

    // At 00:00 on every day-of-month
    @Scheduled(cron = "0 0 0 */1 * *")
    public void deleteExpiredDiscounts() {
        discountService.getAllDiscounts().stream() //
                .filter(discount -> discount.getEndDate().before(new Date())) //
                .peek(discount -> System.out.println("Deleting discount: " + discount.getId())) //
                .forEach(discount -> discountService.delete(discount.getClubId(), discount.getId()));
    }

    // At 00:00 on day-of-month 1 in every month
    @Scheduled(cron = "0 0 0 1 */1 *")
    public void deleteExpiredEvents() {
        eventService.getAllEvents().stream() //
                .filter(event -> event.getDate().after(new Date())) //
                .peek(event -> System.out.println("Deleting event: " + event.getName())) //
                .forEach(event -> eventService.deleteById(event.getId()));
    }
}
