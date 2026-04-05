package ss1.TH;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        WorkStation workStation = context.getBean(WorkStation.class);
        PlaySessionService playSessionService = context.getBean(PlaySessionService.class);

        for (User u : workStation.getUsers() ){
            playSessionService.notifyUser(u);
        }


        context.registerShutdownHook();
    }
}
