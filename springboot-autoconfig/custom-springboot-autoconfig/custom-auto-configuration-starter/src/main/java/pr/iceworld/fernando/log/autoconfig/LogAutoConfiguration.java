package pr.iceworld.fernando.log.autoconfig;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pr.iceworld.fernando.log.LogApi;
import pr.iceworld.fernando.log.TxtLog;

@Configuration
@ConditionalOnClass(LogApi.class)
public class LogAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public TxtLog txtLog() {
        return new TxtLog();
    }

    //@Bean
    //@ConditionalOnBean
    //@Primary
    //public XmlLog xmlLog() {
    //    return new XmlLog();
    //}

    //@Bean
    //@ConditionalOnBean
    //public HtmlLog htmlLog() {
    //    return new HtmlLog();
    //}
}
