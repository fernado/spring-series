package pr.iceworld.fernando.springbean.lifecycle02;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pr.iceworld.fernando.springbean.lifecycle02.config.TestProjConfig;
import pr.iceworld.fernando.springbean.lifecycle02.model.Person;

public class TestSpringBeanLifeCycle {

    @Test
    void test_springBean_lifeCycle() {
        System.out.println("1. application start");
        System.out.println("2. applicationContext instantiating start");
        System.out.println("3. is going to scan config file (annotated configuration class), and register all bean to beanDefinition");
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(TestProjConfig.class);
        System.out.println("22. applicationContext instantiated end");
        Person person = applicationContext.getBean(Person.class);
        System.out.println("23. created Person " + person);

        // ((AnnotationConfigApplicationContext)applicationContext).registerShutdownHook();
        // System.out.println("24. registerShutdownHook");

        ((AnnotationConfigApplicationContext) applicationContext).close();
        System.out.println("24. applicationContext close invoked, but there are some left work need to do");

        Assertions.assertNotNull(person);
    }
}
