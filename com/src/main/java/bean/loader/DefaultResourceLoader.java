package bean.loader;

import bean.loader.resource.ClassPathResource;
import bean.loader.resource.FileSystemResource;
import bean.loader.resource.Resource;
import bean.loader.resource.UrlResource;
import cn.hutool.core.lang.Assert;

import java.net.MalformedURLException;
import java.net.URL;

public class DefaultResourceLoader implements ResourceLoader{
    @Override
    public Resource getResource(String location) {
        Assert.notNull(location,"locatin not nul");
        if(location.startsWith(CLASSPATH_URL_PREFIX)){
            return  new ClassPathResource(location.substring(CLASSPATH_URL_PREFIX.length()));
        }else{
            try{
                URL url = new URL(location);
                return  new UrlResource(url);
            }catch (MalformedURLException e){
                return new FileSystemResource(location);
            }
        }
    }
}
