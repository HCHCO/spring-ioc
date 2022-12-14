package factory.reader;

import bean.BeanDefinition;
import bean.BeanReference;
import bean.loader.ResourceLoader;
import bean.loader.resource.Resource;
import bean.property.PropertyValue;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.XmlUtil;
import exception.BeansException;
import factory.registry.BeanDefinitionRegistry;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Documented;

public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader{
    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        super(registry);
    }

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        super(registry, resourceLoader);
    }

    @Override
    public void loadBeanDefinition(Resource resource) throws BeansException {
        try {
            try(InputStream inputStream=resource.getIntputStream()){
                doLoadBeanDefinitions(inputStream);
            }
            }catch (IOException |ClassNotFoundException e){
            throw new BeansException("ioexception during load xml ");
        }
    }

    @Override
    public void loadBeanDefinition(Resource... resources) throws BeansException {
        for(Resource resource:resources){
            loadBeanDefinition(resource);
        }
    }

    @Override
    public void loadBeanDefinition(String location) throws BeansException {
        ResourceLoader resourceLoader= getResourceLoader();
        Resource resource =resourceLoader.getResource(location);
        loadBeanDefinition(resource);
    }
    // v7.0
    public void loadBeanDefinition(String... locations)throws BeansException{
        for(String location: locations){
            loadBeanDefinition(location);
        }
    }
    protected void doLoadBeanDefinitions(InputStream inputStream) throws ClassNotFoundException {
        Document doc = XmlUtil.readXML(inputStream);
        Element root = doc.getDocumentElement();
        NodeList childNodes = root.getChildNodes();

        for (int i = 0; i < childNodes.getLength(); i++) {
            // ????????????
            if (!(childNodes.item(i) instanceof Element)) continue;
            // ????????????
            if (!"bean".equals(childNodes.item(i).getNodeName())) continue;

            // ????????????
            Element bean = (Element) childNodes.item(i);
            String id = bean.getAttribute("id");
            String name = bean.getAttribute("name");
            String className = bean.getAttribute("class");
            // v8.0 ??? initMethod / destoryMethod ??????
            String initMethod = bean.getAttribute("init-method");
            String destroyMethodName = bean.getAttribute("destroy-method");
            // ?????? Class??????????????????????????????
            Class<?> clazz = Class.forName(className);
            // ????????? id > name
            String beanName = StrUtil.isNotEmpty(id) ? id : name;
            // v9.0
            String beanScope = bean.getAttribute("scope");
            if (StrUtil.isEmpty(beanName)) {
                beanName = StrUtil.lowerFirst(clazz.getSimpleName());
            }

            // ??????Bean
            BeanDefinition beanDefinition = new BeanDefinition(clazz);
            // v8.0 ???????????????beanDefinition???
            beanDefinition.setInitMethodName(initMethod);
            beanDefinition.setDestoryMethodName(destroyMethodName);
            // v9.0
            if(StrUtil.isNotEmpty(beanScope)){
                beanDefinition.setScope(beanScope);
            }

            // ?????????????????????
            for (int j = 0; j < bean.getChildNodes().getLength(); j++) {
                if (!(bean.getChildNodes().item(j) instanceof Element)) continue;
                if (!"property".equals(bean.getChildNodes().item(j).getNodeName())) continue;
                // ???????????????property
                Element property = (Element) bean.getChildNodes().item(j);
                String attrName = property.getAttribute("name");
                String attrValue = property.getAttribute("value");
                String attrRef = property.getAttribute("ref");
                // ??????????????????????????????????????????
                Object value = StrUtil.isNotEmpty(attrRef) ? new BeanReference(attrRef) : attrValue;
                // ??????????????????
                PropertyValue propertyValue = new PropertyValue(attrName, value);
                beanDefinition.getPropertyValues().addPorpertyValue(propertyValue);
            }
            if (getRegistry().containsBeanDefinition(beanName)) {
                throw new BeansException("Duplicate beanName[" + beanName + "] is not allowed");
            }
            // ?????? BeanDefinition
            getRegistry().registerBeanDefinition(beanName, beanDefinition);
        }
    }
}

