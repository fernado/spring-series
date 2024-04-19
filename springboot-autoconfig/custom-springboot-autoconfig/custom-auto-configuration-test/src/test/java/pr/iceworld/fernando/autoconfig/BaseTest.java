package pr.iceworld.fernando.autoconfig;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pr.iceworld.fernando.config.CustomAutoConfigurationMainApplication;
import pr.iceworld.fernando.config.service.TestService;
import pr.iceworld.fernando.log.Greeter;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CustomAutoConfigurationMainApplication.class)
public class BaseTest {

    @Autowired
    TestService testService;

    @Autowired
    private Greeter greeter;

    @Test
    public void doTest() {
        testService.doAction();
        String expected = "Hello ferna, kaka";
        String actual = greeter.greet(LocalDateTime.of(2023, 2, 11, 21, 0));
        assertEquals(expected, actual);
        System.out.println(actual);
    }
}
