package bean.loader.resource;

import cn.hutool.core.lang.Assert;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class UrlResource implements Resource{
    private final URL url;

    public UrlResource(URL url) {
        Assert.notNull(url,"url not null");
        this.url = url;
    }

    @Override
    public InputStream getIntputStream() throws IOException {
        URLConnection con = this.url.openConnection();
        try{
            return con.getInputStream();
        }catch (IOException e){
            if(con instanceof HttpURLConnection){
                ((HttpURLConnection) con).disconnect();
            }
            throw e;
        }
    }



}
