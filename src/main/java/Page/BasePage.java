package Page;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class BasePage {

    public WebDriver driver;
    public Actions actions;
    public WebDriverWait wait;

    public BasePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        actions = new Actions(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void click(WebElement element) {
        element.click();
    }

    public void takeScreenshot(WebDriver driver) throws IOException {
        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshotFile, new File(".//src/main/resources/screenshot/screen.png"));
    }

    public void sendKeys(WebElement element, String text) {
        element.sendKeys(text);
    }

    public void sendKeysWithClear(WebElement element, String text) {
        element.clear();
        if(element.getText().length() !=0) {
            element.sendKeys(Keys.ALT + "a" + Keys.DELETE);
        }
        sendKeys(element, text);
    }

    public WebElement getRandomElement(List<WebElement> elements) {
        Random random = new Random();
        int randomIndexOfList = random.nextInt(elements.size());
        return elements.get(randomIndexOfList);
    }

    public <T> Object getRandom(List<T> elements) {
        return elements.get(new Random().nextInt(elements.size()));
    }

    public void waitToBeClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitToBeVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForElementsVisibility(List<WebElement> elements) {
        wait.until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    public void waitForUrlToBe(String url) {
        wait.until(ExpectedConditions.urlToBe(url));
    }

    public void moveToElement(WebElement element) {
        actions.moveToElement(element).perform();
    }

    static ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();

    static public void scheduleWait(long ms){
        try{
            CountDownLatch l = new CountDownLatch(1);
            ses.schedule(l::countDown, ms, TimeUnit.MILLISECONDS);
            l.await();
        } catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}

