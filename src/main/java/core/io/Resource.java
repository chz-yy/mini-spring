package core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * 资源访问接口
 */
public interface Resource {
    InputStream getInputStream() throws IOException;
}
