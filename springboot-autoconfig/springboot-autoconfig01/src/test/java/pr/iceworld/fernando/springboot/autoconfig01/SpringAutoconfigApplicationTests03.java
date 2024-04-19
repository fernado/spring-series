package pr.iceworld.fernando.springboot.autoconfig01;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import pr.iceworld.fernando.springboot.autoconfig01.annotation.EnableHelloWorldBySelector;
import pr.iceworld.fernando.springboot.autoconfig01.config.ProjectConfig;

@EnableHelloWorldBySelector
class SpringAutoconfigApplicationTests03 {


    @Test
    void test_TestService() {
        String[] args = new String[0];
        ConfigurableApplicationContext context = new SpringApplicationBuilder(ProjectConfig.class)
                .web(WebApplicationType.NONE)
                .run(args);
        String hello = context.getBean("hello", String.class);
        Assertions.assertEquals("hello world", hello);
        context.close();
    }

}
