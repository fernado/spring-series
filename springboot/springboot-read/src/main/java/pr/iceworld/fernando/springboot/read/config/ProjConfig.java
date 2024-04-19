package pr.iceworld.fernando.springboot.read.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import pr.iceworld.fernando.springboot.read.component.ClazzB;
import pr.iceworld.fernando.springboot.read.component.ClazzC;

@Configuration
@ComponentScan("pr.iceworld.fernando.springboot.read")
public class ProjConfig {

    @Bean
    public ClazzB clazzB() {
        return new ClazzB();
    }

    @Bean
    public ClazzC clazzC() {
        return new ClazzC(clazzB());
    }
}
