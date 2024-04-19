package pr.iceworld.fernando.first;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = FirstMainApplication.class)
@AutoConfigureMockMvc
public class BaseTest {


    @Test
    public void test01() {
        Assertions.assertEquals("name=zhangsan, gender=male",
                firstCustomAutoConfiguration.firstCustomService().getCustom());
    }

    @Resource
    private WebApplicationContext context;
    //    @Resource
    private MockMvc mockMvc;

    @Resource
    private FirstCustomAutoConfiguration firstCustomAutoConfiguration;

    @BeforeEach
    public void setupMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void testTest0() throws Exception {
        mockMvc.perform(get("/test0").param("aaa", "20"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andReturn();
    }

    @Test
    public void testTest1() throws Exception {
        mockMvc.perform(get("/test1").param("aaa", "20"))
                .andExpect(status().is(HttpStatus.ACCEPTED.value()))
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN))
                .andDo(print())
                .andReturn();
    }
}
