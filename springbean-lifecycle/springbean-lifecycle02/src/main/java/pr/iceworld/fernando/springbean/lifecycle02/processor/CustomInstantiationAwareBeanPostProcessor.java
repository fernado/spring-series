package pr.iceworld.fernando.springbean.lifecycle02.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import pr.iceworld.fernando.springbean.lifecycle02.model.Person;

public class CustomInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {

    public CustomInstantiationAwareBeanPostProcessor() {
        super();
        System.out.println("7. invoke constructor of " + InstantiationAwareBeanPostProcessor.class.getSimpleName());
    }

    // do before instantiating bean
    @Override
    public Object postProcessBeforeInstantiation(Class beanClass, String beanName) throws BeansException {
        System.out.println("8. invoke InstantiationAwareBeanPostProcessor#postProcessBeforeInstantiation(beanClass, beanName)");
        return null;
    }

    // do after initialized bean
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName)
            throws BeansException {
        System.out.println("21. invoke InstantiationAwareBeanPostProcessor#postProcessAfterInitialization(bean, beanName)");
        System.out.println(bean);
        ((Person)bean).setEducationLevel("University");
        System.out.println("setEducationLevel to 'University'");
        return bean;
    }

    // when populate property
    @Override
    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        System.out.println("12. invoke InstantiationAwareBeanPostProcessor#postProcessPropertyValues(pvs, bean, beanName)");
        return pvs;
    }

}