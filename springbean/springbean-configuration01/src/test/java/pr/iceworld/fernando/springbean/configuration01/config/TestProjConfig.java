package pr.iceworld.fernando.springbean.configuration01.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pr.iceworld.fernando.springbean.configuration01.Clazz0A;
import pr.iceworld.fernando.springbean.configuration01.Clazz0B;

@Configuration
public class TestProjConfig {

    @Bean
    public Clazz0A clazz0A() {
        return new Clazz0A();
    }

    @Bean
    public Clazz0B clazz0B() {
        return new Clazz0B("Clazz0BName", clazz0A());
    }
}
