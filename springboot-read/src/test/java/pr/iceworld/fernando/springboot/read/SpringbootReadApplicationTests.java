package pr.iceworld.fernando.springboot.read;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pr.iceworld.fernando.springboot.read.component.ClazzA;
import pr.iceworld.fernando.springboot.read.config.ProjConfig;

class SpringbootReadApplicationTests {

    @Test
    void test_bean_loading() {

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ProjConfig.class);
        ClazzA clazzA = applicationContext.getBean(ClazzA.class);
        System.out.println(clazzA.getLastName());
    }

}
