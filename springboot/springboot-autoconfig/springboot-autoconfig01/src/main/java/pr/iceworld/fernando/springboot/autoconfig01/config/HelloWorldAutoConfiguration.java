package pr.iceworld.fernando.springboot.autoconfig01.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import pr.iceworld.fernando.springboot.autoconfig01.annotation.EnableHelloWorld;

@Configuration
@EnableHelloWorld
@ConditionalOnProperty(name = "hello world", havingValue = "true")
public class HelloWorldAutoConfiguration {

}