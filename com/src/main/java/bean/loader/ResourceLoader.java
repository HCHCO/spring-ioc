package bean.loader;

import bean.loader.resource.Resource;

import java.io.IOException;

public interface ResourceLoader {
    String CLASSPATH_URL_PREFIX="classpath:";
    Resource getResource(String location);
}
