package ss1.TH;


import org.springframework.stereotype.Component;

@Component("soundNotification")
public class SoundNotification implements INotification {
    @Override
    public void send(String username) {
        System.out.println("Sending sound notification to " + username);
    }
}
