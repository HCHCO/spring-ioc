package factory.context;

import factory.DefaultListableBeanFactory;
import factory.reader.XmlBeanDefinitionReader;
/*
v7.0
 */
public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext{
    @Override
    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory){
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        String[] configLocation = getConfigLocation();
        if(null !=configLocation){
            xmlBeanDefinitionReader.loadBeanDefinition(configLocation);
        }
    }
    protected abstract String[] getConfigLocation();
}
