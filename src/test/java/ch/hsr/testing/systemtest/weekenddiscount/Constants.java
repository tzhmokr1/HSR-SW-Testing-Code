package ch.hsr.testing.systemtest.weekenddiscount;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

public interface Constants {

    public final String HOST = "localhost";

    public final String BASE_URL = "http://" + HOST + ":8080/";

    // TODO: choose your os: either linux, windows or mac
    public final String OS = "mac";

    default String getChromeDriverPath() {
        try {
            URL res = getClass().getClassLoader().getResource("chromedriver/" + OS + "/chromedriver");
            File file = Paths.get(res.toURI()).toFile();
            return file.getAbsolutePath();
        } catch (URISyntaxException | NullPointerException e) {
            throw new IllegalStateException("Path to chromedriver executable could not be established: "
                    + e.getLocalizedMessage());
        }
    }

}


