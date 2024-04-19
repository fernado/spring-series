package pr.iceworld.fernando.springboot.read.component;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

@Component
public class FactoryBeanClazzE implements FactoryBean<ClazzE> {

    @Override
    public ClazzE getObject() {
        ClazzE clazzE = new ClazzE();
        clazzE.setHeight("10.0");
        clazzE.setWidth("20.0");
        return clazzE;
    }

    @Override
    public Class<?> getObjectType() {
        return ClazzE.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
