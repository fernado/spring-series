package pr.iceworld.fernando.springboot.read.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = "classpath:application.properties")
public class ClazzA {

    @Value("${lastName}")
    private String lastName;

    @Autowired
    private ClazzB clazzB;

    public void setClazzB(ClazzB clazzB) {
        this.clazzB = clazzB;
    }

    public String getLastName() {
        return lastName;
    }
}
