package pr.iceworld.fernando.fruit.service.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pr.iceworld.fernando.fruit.service.Apple;
import pr.iceworld.fernando.fruit.service.Fruit;
import pr.iceworld.fernando.fruit.service.Orange;
import pr.iceworld.fernando.fruit.service.Vegetable;

@Configuration
@ConditionalOnClass(Fruit.class)
public class FruitConfiguration {
	/**
	 *  no bean of type Orange is already contained in the BeanFactory.
	 */
    @Bean
    @ConditionalOnMissingBean
    public Orange orange() {
        System.out.println("loading orange");
        return new Orange();
    }

	/**
	 * a bean of type Apple is already contained in the BeanFactory.
	 */
    @Bean
    @ConditionalOnBean(Vegetable.class)
    public Apple apple() {
        System.out.println("loading apple");
        return new Apple();
    }
}
