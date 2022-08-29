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
            // 判断元素
            if (!(childNodes.item(i) instanceof Element)) continue;
            // 判断对象
            if (!"bean".equals(childNodes.item(i).getNodeName())) continue;

            // 解析标签
            Element bean = (Element) childNodes.item(i);
            String id = bean.getAttribute("id");
            String name = bean.getAttribute("name");
            String className = bean.getAttribute("class");
            // v8.0 对 initMethod / destoryMethod 获取
            String initMethod = bean.getAttribute("init-method");
            String destroyMethodName = bean.getAttribute("destroy-method");
            // 获取 Class，方便获取类中的名称
            Class<?> clazz = Class.forName(className);
            // 优先级 id > name
            String beanName = StrUtil.isNotEmpty(id) ? id : name;
            if (StrUtil.isEmpty(beanName)) {
                beanName = StrUtil.lowerFirst(clazz.getSimpleName());
            }

            // 定义Bean
            BeanDefinition beanDefinition = new BeanDefinition(clazz);
            // v8.0 额外设置到beanDefinition中
            beanDefinition.setInitMethodName(initMethod);
            beanDefinition.setDestoryMethodName(destroyMethodName);

            // 读取属性并填充
            for (int j = 0; j < bean.getChildNodes().getLength(); j++) {
                if (!(bean.getChildNodes().item(j) instanceof Element)) continue;
                if (!"property".equals(bean.getChildNodes().item(j).getNodeName())) continue;
                // 解析标签：property
                Element property = (Element) bean.getChildNodes().item(j);
                String attrName = property.getAttribute("name");
                String attrValue = property.getAttribute("value");
                String attrRef = property.getAttribute("ref");
                // 获取属性值：引入对象、值对象
                Object value = StrUtil.isNotEmpty(attrRef) ? new BeanReference(attrRef) : attrValue;
                // 创建属性信息
                PropertyValue propertyValue = new PropertyValue(attrName, value);
                beanDefinition.getPropertyValues().addPorpertyValue(propertyValue);
            }
            if (getRegistry().containsBeanDefinition(beanName)) {
                throw new BeansException("Duplicate beanName[" + beanName + "] is not allowed");
            }
            // 注册 BeanDefinition
            getRegistry().registerBeanDefinition(beanName, beanDefinition);
        }
    }
}

