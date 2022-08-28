package bean.loader.resource;

import cn.hutool.core.lang.Assert;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class ClassPathResource implements Resource {
    private final String path;
    private ClassLoader classLoader;

    public ClassPathResource(String path) {
        this(path, new ClassLoader() {});
    }

    public ClassPathResource(String path, ClassLoader classLoader) {
        Assert.notNull(path,"path no null");
        this.path = path;
        this.classLoader = classLoader;
    }

    @Override
    public InputStream getIntputStream() throws IOException {
        InputStream is  =classLoader.getResourceAsStream(path);
        if(is==null){
            throw  new FileNotFoundException(this.path+"no found file");
        }
        return is;
    }
}
