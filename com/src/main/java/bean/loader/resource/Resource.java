package bean.loader.resource;

import java.io.IOException;
import java.io.InputStream;

public interface Resource {
    InputStream getIntputStream() throws IOException;
}
