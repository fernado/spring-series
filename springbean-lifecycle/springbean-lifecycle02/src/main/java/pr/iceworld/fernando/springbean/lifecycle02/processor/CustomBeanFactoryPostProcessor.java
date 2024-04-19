package pr.iceworld.fernando.springbean.lifecycle02.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

public class CustomBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    public CustomBeanFactoryPostProcessor() {
        super();
        System.out.println("3. invoke constructor of " + BeanFactoryPostProcessor.class.getSimpleName());
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("4. invoke BeanFactoryPostProcessor#postProcessBeanFactory(beanFactory)");
        BeanDefinition bd = beanFactory.getBeanDefinition("person");
        System.out.println("BeanDefinition person's beanClassName " + bd.getBeanClassName());
        System.out.println("BeanDefinition person's educationLevel = " + bd.getPropertyValues().get("educationLevel"));
        bd.getPropertyValues().addPropertyValue("educationLevel", "Small");
        System.out.println("5. as there is no person beanDefinition, add property value 'Small' to person's educationLevel will not be useful");

    }

}