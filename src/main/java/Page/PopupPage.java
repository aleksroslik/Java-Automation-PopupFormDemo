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

    @FindBy(css = "div.awe-multilookup-field.awe-field:nth-child(1) .awe-lkpbtn")
    private WebElement mealsBtn;

    @FindBy(css = ".awe-ajaxlist li button")
    private List<WebElement> meals;

    @FindBy(xpath = "//div[@data-i='createDinnerMeals-awepw']/descendant::button[@class='awe-btn awe-okbtn o-pbtn']")
    private WebElement mealsOKBtn;

    @FindBy(css = "ul.awe-display li")
    private List<WebElement> mealsCaption;

    @FindBy(css = "ul.awe-display")
    private WebElement mealsCaptionList;

    @FindBy(css = ".awe-econt .awe-morebtn")
    private WebElement moreMealsBtn;

    @FindBy(css = "div.awe-multilookup-field.awe-field:nth-child(1) div.awe-empty")
    private WebElement emptyMealCaption;

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
        getChefText(chefCaption);
        return this;
    }

    public PopupPage clickOnMealsSelection() {
        click(mealsBtn);
        logger.info("Click on Meals Selection");
        return this;
    }

    public PopupPage selectRandomMeals(int counter) {
        waitToBeClickable(moreMealsBtn);
        scrollAndClick(moreMealsBtn);
        waitToBeClickable(moreMealsBtn);
        scrollAndClick(moreMealsBtn);
        getRandomMealElementAndClick(meals, counter);
        waitToBeClickable(mealsOKBtn);
        click(mealsOKBtn);
        waitForElementsVisibility(mealsCaption);
        for (int i = 0; i < mealsCaption.size(); i++) {
            waitForInvisibilityOf(emptyMealCaption);
            waitToBeVisible(mealsCaptionList);
            getMealText(mealsCaption.get(i));
        }
        return this;
    }

    public void getRandomElementAndClick(List<WebElement> element) {
        WebElement item = getRandomElement(element);
        waitToBeClickable(item);
        click(item);
    }

    public void getRandomMealElementAndClick(List<WebElement> elements, int counter) {
        for (int i = 0; i < counter; i++) {
            waitForElementsVisibility(elements);
            WebElement item = getRandomElement(elements);
            waitToBeClickable(item);
            click(item);
        }
    }

    public void getChefText(WebElement element) {
        waitForInvisibilityOf(emptyChefCaption);
        String chef = element.getText();
        logger.info("Chef selected: " + chef);
    }

    public void getMealText(WebElement element) {
        String meal = element.getText();
        logger.info("Meal selected: " + meal);
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
