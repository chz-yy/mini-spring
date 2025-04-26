package core.io;

import cn.hutool.core.io.IoUtil;
import junit.framework.TestCase;
import org.junit.Test;

import java.io.InputStream;

public class DefaultResourceLoaderTest extends TestCase {
    @Test
    public void testResourceLoader() throws Exception {
        DefaultResourceLoader defaultResourceLoader = new DefaultResourceLoader();

        Resource resource = defaultResourceLoader.getResource("classpath:hello.txt");
        InputStream inputStream = resource.getInputStream();
        String s = IoUtil.readUtf8(inputStream);
        System.out.println(s);

        resource = defaultResourceLoader.getResource("src/main/resources/hello.txt");
        inputStream = resource.getInputStream();
         s = IoUtil.readUtf8(inputStream);
        System.out.println(s);

        //加载url资源
        resource = defaultResourceLoader.getResource("https://www.baidu.com");
        inputStream = resource.getInputStream();
        s = IoUtil.readUtf8(inputStream);
        System.out.println(s);
    }

}