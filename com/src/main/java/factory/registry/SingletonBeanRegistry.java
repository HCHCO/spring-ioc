package factory.registry;

public interface SingletonBeanRegistry {
    Object getSingleton(String beanName);
}
