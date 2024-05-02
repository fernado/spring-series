package pr.iceworld.fernando.springbean.configuration01;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pr.iceworld.fernando.springbean.configuration01.config.TestProjConfig;

public class TestConfiguration01 {

    @Test
    void test_configuration01() {

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(TestProjConfig.class);

        Clazz0A clazz0A = applicationContext.getBean(Clazz0A.class);

        Clazz0B clazz0B = applicationContext.getBean(Clazz0B.class);

        System.out.println(clazz0A);
        System.out.println(clazz0B.getClazz0A());

    }
}
