package ph.edu.tip.mamamoo.Utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DotEnvUtility {
    private String directory = System.getProperty("user.dir");
    private String filename = "\\.env";
    private static Properties appProps = new Properties();

    private DotEnvUtility() { }

    public static DotEnvUtility configure() {
        return new DotEnvUtility();
    }

    public String get(String property) {
        return appProps.getProperty(property);
    }

    public DotEnvUtility directory(String directory) {
        this.directory = directory;
        return this;
    }

    public DotEnvUtility filename(String filename) {
        this.filename = filename;
        return this;
    }

    public DotEnvUtility load() {
        try {
            appProps.load(new FileInputStream(directory + filename));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return this;
    }
}
