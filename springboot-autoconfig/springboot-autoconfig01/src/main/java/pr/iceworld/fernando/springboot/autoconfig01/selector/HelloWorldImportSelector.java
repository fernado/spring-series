package pr.iceworld.fernando.springboot.autoconfig01.selector;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import pr.iceworld.fernando.springboot.autoconfig01.config.HelloWorldConfiguration;

public class HelloWorldImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{HelloWorldConfiguration.class.getName()};
    }
}