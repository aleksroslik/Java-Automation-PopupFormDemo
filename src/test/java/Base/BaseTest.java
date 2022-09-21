package Base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseTest {

    protected static Logger logger = LoggerFactory.getLogger(BaseTest.class);

    public static WebDriver driver;

    @BeforeAll
    static void setDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        logger.info("Driver started successfully");
        driver.get("https://demo.aspnetawesome.com/PopupFormDemo");
    }

    @AfterEach
    public void close() {
        driver.quit();
        logger.info("Driver closed");
    }
}
