package pr.iceworld.fernando.springbean.lifecycle02.config;

import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import pr.iceworld.fernando.springbean.lifecycle02.model.Person;
import pr.iceworld.fernando.springbean.lifecycle02.processor.CustomBeanFactoryPostProcessor;
import pr.iceworld.fernando.springbean.lifecycle02.processor.CustomBeanPostProcessor;
import pr.iceworld.fernando.springbean.lifecycle02.processor.CustomInstantiationAwareBeanPostProcessor;

@Configuration
@ComponentScan("pr.iceworld.fernando.springbean.lifecycle02")
public class TestProjConfig {

    @Bean(initMethod = "customInitMethod", destroyMethod = "customDestroyMethod")
    public Person person() {
        System.out.println("Load configuration bean of " + Person.class.getSimpleName());
        Person person = new Person();
        person.setName("Fernando");
        person.setEducationLevel("Middle");
        System.out.println("setEducationLevel to 'Middle'");
        return person;
    }

    @Bean
    public BeanFactoryPostProcessor beanFactoryPostProcessor() {
        return new CustomBeanFactoryPostProcessor();
    }

    @Bean
    public BeanPostProcessor beanPostProcessor() {
        return new CustomBeanPostProcessor();
    }

    @Bean
    public InstantiationAwareBeanPostProcessor instantiationAwareBeanPostProcessor() {
        return new CustomInstantiationAwareBeanPostProcessor();
    }
}
