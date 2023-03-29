import config.MainConfig;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class MainTest {
    protected WebDriver driver;

    private final MainConfig config = ConfigFactory.create(MainConfig.class, System.getenv());

    @BeforeMethod
    public void setUp() {
        System.setProperty(config.slf4JFixProperty(), config.slf4JFix());
        System.setProperty(config.driverProperty(), config.driverPath());
        driver = new ChromeDriver();
        driver.get(config.url());
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
    }

    @AfterMethod
    public void tearDown() {
        driver.close();
    }
}
