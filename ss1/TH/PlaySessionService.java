package ss1.TH;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class PlaySessionService {
    private final INotification soundNotification;
    private final INotification popupNotification;

    @Autowired
    public PlaySessionService(@Qualifier("soundNotification")INotification soundNotification,@Qualifier("popupNotification")INotification popupNotification) {
        this.soundNotification = soundNotification;
        this.popupNotification = popupNotification;
    }

    public void notifyUser(User u){
        if(u == null) {
            System.out.println("User is null");
            return;
        }
        if(u.getBalance() < 0){
            System.out.println("User balance is negative");
            return;
        }
        if(u.isActive() && u.getBalance() < 5000){
            if(u.getArea().equalsIgnoreCase("Normal")){
                soundNotification.send(u.getUsername());
            } else {
                popupNotification.send(u.getUsername());
            }
        }
    }
}
