package pr.iceworld.fernando.log.autoconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pr.iceworld.fernando.log.Greeter;
import pr.iceworld.fernando.log.GreeterProperties;
import pr.iceworld.fernando.log.GreetingConfig;

import static pr.iceworld.fernando.log.GreeterConfigParams.NIGHT_MESSAGE;
import static pr.iceworld.fernando.log.GreeterConfigParams.USER_NAME;

@Configuration
@ConditionalOnClass(Greeter.class)
@EnableConfigurationProperties(GreeterProperties.class)
public class GreeterAutoConfiguration {

    @Autowired
    private GreeterProperties greeterProperties;

    @Bean
    @ConditionalOnMissingBean
    public GreetingConfig greeterConfig() {

        String userName = greeterProperties.getUserName() == null
          ? System.getProperty("user.name") 
          : greeterProperties.getUserName();

        String nightMessage = greeterProperties.getNightMessage() == null
                ? System.getProperty("night.message")
                : greeterProperties.getNightMessage();

        GreetingConfig greetingConfig = new GreetingConfig();
        greetingConfig.put(USER_NAME, userName);
        // ...
        greetingConfig.put(NIGHT_MESSAGE, nightMessage);
        return greetingConfig;
    }

    @Bean
    @ConditionalOnMissingBean
    public Greeter greeter(GreetingConfig greetingConfig) {
        return new Greeter(greetingConfig);
    }
}