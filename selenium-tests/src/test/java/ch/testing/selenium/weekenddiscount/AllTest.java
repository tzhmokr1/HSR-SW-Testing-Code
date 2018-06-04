package ch.testing.selenium.weekenddiscount;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SelectPackages("ch.hsr.testing.selenium.weekendiscount.tests")
public class AllTest {

    public AllTest() {
        System.out.println("Alle Tests ausfuehren");
    }
}
