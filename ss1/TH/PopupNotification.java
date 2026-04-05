package ss1.TH;

import org.springframework.stereotype.Component;

@Component("popupNotification")
public class PopupNotification implements  INotification{
    @Override
    public void send(String username) {
        System.out.println("Sending popup notification to " + username);
    }
}
