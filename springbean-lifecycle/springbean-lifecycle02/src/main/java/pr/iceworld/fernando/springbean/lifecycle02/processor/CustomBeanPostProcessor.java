package pr.iceworld.fernando.springbean.lifecycle02.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import pr.iceworld.fernando.springbean.lifecycle02.model.Person;

public class CustomBeanPostProcessor implements BeanPostProcessor {

    public CustomBeanPostProcessor() {
        super();
        System.out.println("6. invoke constructor of " + BeanPostProcessor.class.getSimpleName());
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("20. invoke BeanPostProcessor#postProcessAfterInitialization(), be able to update property of bean");
        System.out.println(bean);
        ((Person)bean).setEducationLevel("High");
        System.out.println("setEducationLevel to 'High'");
        return bean;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("16. invoke BeanPostProcessor#postProcessBeforeInitialization, be able to update property of bean");
        return bean;
    }
}