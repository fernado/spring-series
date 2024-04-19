package pr.iceworld.fernando.springboot.autoconfig01;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pr.iceworld.fernando.springboot.autoconfig01.config.ProjectConfig;
import pr.iceworld.fernando.springboot.autoconfig01.service.TestService;

class SpringAutoconfigApplicationTests {

    @Test
    void test_TestService() {
        String[] args = new String[0];
        ConfigurableApplicationContext context = new SpringApplicationBuilder(ProjectConfig.class)
                .web(WebApplicationType.NONE)
                .run(args);
        TestService testService = context.getBean("testService", TestService.class);
        System.out.println("TestService Bean: " + testService);
        context.close();
    }

}
