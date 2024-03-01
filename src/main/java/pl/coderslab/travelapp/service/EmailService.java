package pl.coderslab.travelapp.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.coderslab.travelapp.entity.Travel;
import pl.coderslab.travelapp.entity.User;
import pl.coderslab.travelapp.repository.UserRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class EmailService {
    private final JavaMailSender mailSender;
    private final UserRepository userRepo;

    public EmailService(JavaMailSender mailSender, UserRepository userRepo) {
        this.mailSender = mailSender;
        this.userRepo = userRepo;
    }

    public void sendEmail(String to, String subject, String text){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }

    @Scheduled(cron = "0 0 12 * * ?")
    public void scheduleEmail(){
        List<User> users = userRepo.findAll();
        for(User u:users){
            List<Travel> travels = u.getTravels();
            for(Travel t:travels){
                LocalDate date = t.getPlannedDate();
                LocalDate dayBefore=date.minusDays(1);
                if(LocalDate.now().equals(dayBefore)){
                    sendEmail(u.getEmail(), "Upcoming travel","Hello. Tomorrow is the day of your planned travel: "
                            +t.getTitle()
                            +". Don't forget to bring some water with you: "
                            +t.getWaterInLiters()+" liters. "
                            +" Hava a nice travel");
                }
            }
        }
    }
}
