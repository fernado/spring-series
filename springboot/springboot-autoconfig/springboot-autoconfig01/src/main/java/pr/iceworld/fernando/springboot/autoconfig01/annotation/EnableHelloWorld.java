package pr.iceworld.fernando.springboot.autoconfig01.annotation;

import org.springframework.context.annotation.Import;
import pr.iceworld.fernando.springboot.autoconfig01.config.HelloWorldConfiguration;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(HelloWorldConfiguration.class)
public @interface EnableHelloWorld {
}