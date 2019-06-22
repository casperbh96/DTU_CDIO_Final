import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import java.io.File;

public class main{

    private static Tomcat tomcat;

    public static void main(String[] args) throws LifecycleException {
        tomcat = new Tomcat();
        tomcat.setBaseDir("temp");
        tomcat.setPort(8080);
        tomcat.getConnector();

        tomcat.addWebapp("",new File("WebContent").getAbsolutePath());

        tomcat.start();

        tomcat.getServer().await();
    }
}