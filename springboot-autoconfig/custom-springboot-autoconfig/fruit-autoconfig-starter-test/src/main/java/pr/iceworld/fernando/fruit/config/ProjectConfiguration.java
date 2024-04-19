package pr.iceworld.fernando.fruit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import pr.iceworld.fernando.fruit.service.Fruit;
import pr.iceworld.fernando.fruit.service.Pear;
import pr.iceworld.fernando.fruit.service.Vegetable;

@Configuration
public class ProjectConfiguration {

    @Bean
    /**
     * 不使用这个修饰符，会导致spring bean 判定不出应该使用哪一个`Fruit` bean
     */
    @Primary
    public Fruit pear() {
        System.out.println("loading pear");
        return new Pear();
    }

    @Bean
    public Vegetable vegetable() {
        return new Vegetable();
    }
}
