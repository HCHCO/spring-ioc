package com.resource.Loader;

import cn.hutool.core.lang.Assert;
import com.resource.ClassPathResource;
import com.resource.FileSystemResource;
import com.resource.Resource;
import com.resource.UrlResource;

import java.net.MalformedURLException;
import java.net.URL;

public class DefaultResourceLoader implements ResourceLoader{
    @Override
    public Resource getResource(String location) {
        Assert.notNull(location,"Location must not be null");
        if(location.startsWith(CLASSPATH_URL_PREFIX)){
            String test = location.substring(CLASSPATH_URL_PREFIX.length());
            return new ClassPathResource(test);
        }else {
            try{
                URL url = new URL(location);
                return new UrlResource(url);
            }catch (MalformedURLException e){
                return new FileSystemResource(location);
            }
        }
    }
}
