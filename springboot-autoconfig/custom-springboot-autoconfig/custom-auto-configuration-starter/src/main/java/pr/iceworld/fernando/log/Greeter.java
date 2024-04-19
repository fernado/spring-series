package pr.iceworld.fernando.log;

import java.time.LocalDateTime;

import static pr.iceworld.fernando.log.GreeterConfigParams.*;

public class Greeter {

    public static final String FORMAT_STR = "Hello %s, %s";
    private GreetingConfig greetingConfig;

    public Greeter(GreetingConfig greetingConfig) {
        this.greetingConfig = greetingConfig;
    }

    public String greet(LocalDateTime localDateTime) {

        String name = greetingConfig.getProperty(USER_NAME);
        int hourOfDay = localDateTime.getHour();

        if (hourOfDay >= 5 && hourOfDay < 12) {
            return String.format(FORMAT_STR, name, greetingConfig.get(MORNING_MESSAGE));
        } else if (hourOfDay >= 12 && hourOfDay < 17) {
            return String.format(FORMAT_STR, name, greetingConfig.get(AFTERNOON_MESSAGE));
        } else if (hourOfDay >= 17 && hourOfDay < 20) {
            return String.format(FORMAT_STR, name, greetingConfig.get(EVENING_MESSAGE));
        } else {
            return String.format(FORMAT_STR, name, greetingConfig.get(NIGHT_MESSAGE));
        }
    }


    public String greet() {
        return greet(LocalDateTime.now());
    }

}