package pr.iceworld.fernando.springbean.lifecycle02.model;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;

public class Person implements BeanFactoryAware, BeanNameAware, BeanClassLoaderAware,
        InitializingBean, DisposableBean {

    private String name;
    private String educationLevel;

    private BeanFactory beanFactory;
    private String beanName;
    private ClassLoader classLoader;

    public Person() {
        System.out.println("9. invoke person's constructor");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println("10. populate property of name");
        this.name = name;
    }

    public String getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(String educationLevel) {
        System.out.println("11. populate property of educationLevel = " + educationLevel);
        this.educationLevel = educationLevel;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", educationLevel='" + educationLevel + '\'' +
                '}';
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("17. invoke postConstruct() of " + Person.class.getSimpleName());
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("25. invoke preDestroy() of " + Person.class.getSimpleName());
    }

    // BeanFactoryAware
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("15. invoke BeanFactoryAware#setBeanFactory(beanFactory)");
        this.beanFactory = beanFactory;
    }

    // BeanNameAware
    @Override
    public void setBeanName(String beanName) {
        System.out.println("13. invoke BeanNameAware#setBeanName(beanName)");
        this.beanName = beanName;
    }

    // InitializingBean
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("18. invoke InitializingBean#afterPropertiesSet()");
    }

    // DisposableBean
    @Override
    public void destroy() throws Exception {
        System.out.println("26. invoke DisposableBean#destroy()");
    }

    // init-method in bean's annotation
    public void customInitMethod() {
        System.out.println("19. invoke bean of " + Person.class.getSimpleName() + "'s init-method");
    }

    // destroy-method in bean's annotation
    public void customDestroyMethod() {
        System.out.println("27. invoke bean of " + Person.class.getSimpleName() + "'s destroy-method");
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        System.out.println("14. invoke BeanNameAware#setBeanClassLoader(classLoader)");
        this.classLoader = classLoader;
    }
}