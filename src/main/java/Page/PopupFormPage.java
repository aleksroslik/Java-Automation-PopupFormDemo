package Page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PopupFormPage extends BasePage {

    protected static Logger logger = LoggerFactory.getLogger(PopupFormPage.class);

    public PopupFormPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "div:nth-of-type(2) > .awe-btn")
    private WebElement createBtn;

    public void openPopup() {
        click(createBtn);
        logger.info("Click on 'Create' button");
    }
}
