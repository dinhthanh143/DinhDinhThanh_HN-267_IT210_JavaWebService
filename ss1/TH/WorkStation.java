package ss1.TH;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class WorkStation {
    private List<User> users;

    public WorkStation() {
        users = new ArrayList<>();
        users.add(new User("Gamer_Vip_01", 3000, true, "VIP"));    // Sẽ hiện Popup
        users.add(new User("Tre_Trau_Net", 2000, true, "Normal")); // Sẽ phát Sound
        users.add(new User("Hacker_Loi", -500, true, "VIP"));      // Bẫy dữ liệu: Âm tiền
        users.add(new User("Gamer_Giau", 50000, true, "Normal"));  // Không cảnh báo
        users.add(new User("May_Trong", 1000, false, "VIP"));      // Bẫy: isActive = false
    }

    public List<User> getUsers(){
        return users;
    }

}
