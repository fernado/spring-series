package pr.iceworld.fernando.first;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(FirstCustomConfigurationProperties.class)
public class FirstCustomAutoConfiguration {

	private FirstCustomConfigurationProperties firstCustomConfigurationProperties;

	public FirstCustomAutoConfiguration(FirstCustomConfigurationProperties firstCustomConfigurationProperties) {
		this.firstCustomConfigurationProperties = firstCustomConfigurationProperties;
	}

	static {
		System.out.println("FirstCustomAutoConfiguration init....");
	}
	@Bean
	@ConditionalOnMissingBean
	public FirstCustomService firstCustomService(){
		return new FirstCustomService(firstCustomConfigurationProperties.getName(),
				firstCustomConfigurationProperties.getGender());
	}
}
