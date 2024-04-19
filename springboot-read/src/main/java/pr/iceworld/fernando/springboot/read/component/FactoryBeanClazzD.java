package pr.iceworld.fernando.springboot.read.component;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

@Component
public class FactoryBeanClazzD implements FactoryBean<ClazzD> {

    @Override
    public ClazzD getObject() {
        ClazzD clazzD = new ClazzD();
        clazzD.setHeight("10.0");
        clazzD.setWidth("20.0");
        return clazzD;
    }

    @Override
    public Class<?> getObjectType() {
        return ClazzD.class;
    }

}
