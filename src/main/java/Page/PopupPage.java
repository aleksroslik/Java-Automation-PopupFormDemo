package Page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class PopupPage extends BasePage {

    protected static Logger logger = LoggerFactory.getLogger(PopupFormPage.class);

    public PopupPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "createDinnerName-awed")
    private WebElement nameInput;

    @FindBy()
    private List<WebElement> calendar;

    @FindBy(css = "#createDinnerBonusMealId-dropmenu .o-itsc")
    private WebElement dropdownList;

    @FindBy(css = "#createDinnerBonusMealId-dropmenu li")
    private List<WebElement> dropDownItems;

    @FindBy(css = "div.awe-lookup-field.awe-field:nth-child(1) .awe-lkpbtn")
    private WebElement chefBtn;

    @FindBy(css = ".awe-ajaxlist li")
    private List<WebElement> chefs;

    @FindBy(xpath = "//div[@data-i='createDinnerChef-awepw']/descendant::button[@class='awe-btn awe-okbtn o-pbtn']")
    private WebElement chefOKBtn;

    @FindBy(css = "button#createDinnerBonusMealId-awed")
    private WebElement dropdownBtn;

    @FindBy(className = "awe-caption")
    private WebElement chefCaption;

    @FindBy(css = "div.awe-lookup-field.awe-field:nth-child(1) div.awe-empty")
    private WebElement emptyChefCaption;

    @FindBy(className = "awe-morebtn")
    private WebElement moreBtn;

    public PopupPage clickOnDropdownList() {
        click(dropdownBtn);
        logger.info("Click on Dropdown");
        return this;
    }

    public PopupPage clickOnChefSelection() {
        click(chefBtn);
        logger.info("Click on Chef Selection");
        return this;
    }

    public PopupPage selectRandomChef() {
        waitToBeClickable(moreBtn);
        scrollAndClick(moreBtn);
        getRandomElementAndClick(chefs);
        waitToBeClickable(chefOKBtn);
        click(chefOKBtn);
        getText(chefCaption);
        return this;
    }

    public void getRandomElementAndClick(List<WebElement> element) {
        WebElement item = getRandomElement(element);
        waitToBeClickable(item);
        click(item);
    }

    public void getText(WebElement element) {
        waitForInvisibilityOf(emptyChefCaption);
        String chef = element.getText();
        logger.info("Chef selected: " + chef);
    }

    public void selectRandomBonusMeal() {
        waitToBeVisible(dropdownList);
        WebElement item = getRandomElement(dropDownItems);
        click(item);
        String bonusMeal = dropdownBtn.getText();
        logger.info("Bonus meal selected: " + bonusMeal);
    }

    public PopupPage setUpName() {
        waitToBeVisible(nameInput);
        String name = "Meal of The Day";
        sendKeys(nameInput, name);
        logger.info("Entering input: " + name);
        return this;
    }

    public void scrollAndClick(WebElement element) {
        scrollTo(element);
        click(element);
    }
}
