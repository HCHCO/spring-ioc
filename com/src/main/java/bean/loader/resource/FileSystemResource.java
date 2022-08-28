package bean.loader.resource;

import bean.loader.resource.Resource;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileSystemResource implements Resource{
    private final File file;
    private final String path;

    public FileSystemResource(File file) {
        this.file = file;
        this.path=file.getPath();
    }

    public FileSystemResource(String path) {
        this.path = path;
        file = new File(path);
    }

    public String getPath() {
        return path;
    }

    @Override
    public InputStream getIntputStream() throws IOException {
        return new FileInputStream(this.file);
    }
}
