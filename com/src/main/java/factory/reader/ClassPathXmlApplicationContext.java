package factory.reader;

import exception.BeansException;
import factory.ConfigurableListableBeanFactory;
import factory.context.AbstractApplicationContext;
import factory.context.AbstractXmlApplicationContext;

/*
v7.0
 */
public class ClassPathXmlApplicationContext extends AbstractXmlApplicationContext {
    private  String[] configLocations;

    public ClassPathXmlApplicationContext() {
    }

    public ClassPathXmlApplicationContext(String  configLocation) {
        this(new String[]{configLocation});
    }

    public ClassPathXmlApplicationContext(String[] configLocations) {
        this.configLocations = configLocations;
        refresh();
    }


    @Override
    protected String[] getConfigLocation() {
        return configLocations;
    }
}
