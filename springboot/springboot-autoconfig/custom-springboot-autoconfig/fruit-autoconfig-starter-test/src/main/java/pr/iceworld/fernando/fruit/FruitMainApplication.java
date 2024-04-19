package pr.iceworld.fernando.fruit;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pr.iceworld.fernando.fruit.service.Fruit;

@SpringBootApplication
public class FruitMainApplication {
    public static void main(String[] args) {
        SpringApplication.run(FruitMainApplication.class, args);
    }
	/**
	 * 使用`Fruit` bean 注入
	 */
    @Bean
    public CommandLineRunner commandLineRunner(Fruit fruit) {
        return args -> { fruit.eat(); };
    }
    
    /**
    // 如果此服务申明了`Fruit`bean，单没有使用@Primary做修饰，也可在使用`Fruit`bean地方指明需要使用哪一个
    @Bean
    public CommandLineRunner commandLineRunner(@Qualifier("pear") Fruit fruit) {
        return args -> { fruit.eat(); };
    }
    */
}
