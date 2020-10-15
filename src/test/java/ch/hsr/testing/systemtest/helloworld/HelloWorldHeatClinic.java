/*
 * HSR Hochschule fuer Technik Rapperswil
 * Master of Advanced Studies in Software Engineering
 * Module Software Testing
 *
 * Thomas Briner, thomas.briner@gmail.com
 */
package ch.hsr.testing.systemtest.helloworld;

import ch.hsr.testing.systemtest.weekenddiscount.Constants;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

/**
 * The Class HelloWorldHeatClinic. This class can be used for first experiments
 * with selenium against the system under test.
 * <p>
 * Make sure the system under test is running! (Ant View --> site -->
 * jetty-demo-no-db)
 */
public class HelloWorldHeatClinic implements Constants {

    private WebDriver driver;

    @BeforeEach
    public void setup() {
        System.setProperty("webdriver.chrome.driver", getChromeDriverPath());
        driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @AfterEach
    public void tearDown() {
        driver.close();
    }

    @Test
    public void testPriceOfSauceGreenGhost() throws InterruptedException {

        driver.get("http://localhost:8080");

        // check if the home page is loaded
        MatcherAssert.assertThat("Should be home page of heat clinic", driver.getPageSource(),
                Matchers.containsString("Hot Sauces"));

        // now go to "Hot Sauces"
        WebElement navigation = driver.findElement(By.xpath("//div[@id='left-nav']"));
        navigation.findElement(By.partialLinkText("HOT")).click();
        MatcherAssert.assertThat(driver.getTitle(), Matchers.containsString("Hot Sauces"));

        // jump to the green ghost sauce detail page
        WebElement sauce = driver.findElement(By.xpath("//a[div/img[contains(@src,'Green-Ghost')]]"));
        System.out.println(sauce.getText());
        sauce.click();
        MatcherAssert.assertThat(driver.getTitle(), Matchers.containsString("Green Ghost"));

        // and check the price of the green ghost sauce: should be $9.99
        String price = driver.findElement(By.className("price-new")).getText();
        MatcherAssert.assertThat(price, Matchers.is("$9.99"));
    }

}
