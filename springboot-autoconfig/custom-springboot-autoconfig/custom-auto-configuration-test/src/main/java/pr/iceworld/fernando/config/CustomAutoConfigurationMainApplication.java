package pr.iceworld.fernando.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CustomAutoConfigurationMainApplication {
    public static void main(String[] args) {
        SpringApplication.run(CustomAutoConfigurationMainApplication.class, args);
    }

    /**
     * if we do not declare the LogApi bean, it will automatically use
     * JSONLog
     * @param logapi
     * @return
     */
    //@Bean
    //public CommandLineRunner commandLineRunner(LogApi logapi) {
    //    return args -> { logapi.generate("test log for custom auto config"); };
    //}



}