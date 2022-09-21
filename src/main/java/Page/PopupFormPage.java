package Page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class PopupFormPage extends BasePage {

    protected static Logger logger = LoggerFactory.getLogger(PopupFormPage.class);

    public PopupFormPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "div:nth-of-type(2) > .awe-btn")
    private WebElement createBtn;

    @FindBy(id = "createDinnerName-awed")
    private WebElement nameInput;

    @FindBy()
    private List<WebElement> calendar;

    @FindBy(css = "#createDinnerBonusMealId-dropmenu .o-itsc")
    private WebElement dropdownList;

    @FindBy(css = "#createDinnerBonusMealId-dropmenu li")
    private List<WebElement> dropDownItems;

    @FindBy(css = "button#createDinnerBonusMealId-awed")
    private WebElement dropdownBtn;

    public PopupFormPage clickOnDropdownList() {
        click(dropdownBtn);
        logger.info("Click on Dropdown");
        return this;
    }

    public void selectRandomBonusMeal() {
        waitToBeVisible(dropdownList);
        WebElement item = getRandomElement(dropDownItems);
        click(item);
        String bonusMeal = dropdownBtn.getText();
        logger.info("Bonus meal selected " + bonusMeal);
    }

    public PopupFormPage setUpName() {
        waitToBeVisible(nameInput);
        String name = "test";
        sendKeys(nameInput, name);
        logger.info("Entering input " + name);
        return this;
    }

    public PopupFormPage openPopup() {
        click(createBtn);
        return this;
    }
}
