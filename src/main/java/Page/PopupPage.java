package Page;

import org.openqa.selenium.Alert;
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

    @FindBy(css = "#createDinnerBonusMealId-dropmenu .o-itsc")
    private WebElement dropdownList;

    @FindBy(css = "#createDinnerBonusMealId-dropmenu li")
    private List<WebElement> dropDownItems;

    @FindBy(css = "div.awe-lookup-field.awe-field:nth-child(1) .awe-lkpbtn")
    private WebElement chefDropdownBtn;

    @FindBy(css = ".awe-ajaxlist li")
    private List<WebElement> chefs;

    @FindBy(xpath = "//div[@data-i='createDinnerChef-awepw']/descendant::button[@class='awe-btn awe-okbtn o-pbtn']")
    private WebElement chefOKBtn;

    @FindBy(css = "div.awe-multilookup-field.awe-field:nth-child(1) .awe-lkpbtn")
    private WebElement mealsDropdownBtn;

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
    private WebElement bonusMealDropdownBtn;

    @FindBy(className = "awe-caption")
    private WebElement chefCaption;

    @FindBy(css = "div.awe-lookup-field.awe-field:nth-child(1) div.awe-empty")
    private WebElement emptyChefCaption;

    @FindBy(className = "awe-morebtn")
    private WebElement moreChefBtn;

    @FindBy(className = "awe-dpbtn")
    private WebElement dateBtn;

    @FindBy(css = ".awe-dtp.awe-display.awe-val.awe-txt")
    private WebElement dateInput;

    @FindBy(id = "createDinnerDatecy-awed")
    private WebElement datePickerYear;

    @FindBy(id = "createDinnerDatecm-awed")
    private WebElement datePickerMonth;

    @FindBy(css = "#createDinnerDatecy-dropmenu .o-ditm")
    private List<WebElement> dpDropdownYears;

    @FindBy(css = "#createDinnerDatecm-dropmenu .o-ditm")
    private List<WebElement> dpDropdownMonths;

    @FindBy(css = ".o-mday.o-enb")
    private List<WebElement> dpDaysInMonth;

    @FindBy(css = ".awe-okbtn")
    private WebElement submitBtn;

    public PopupPage clickOnBonusMealDropdownList() {
        click(bonusMealDropdownBtn);
        logger.info("Click on Bonus Meal Dropdown");
        return this;
    }

    public PopupPage clickOnChefSelection() {
        waitToBeClickable(chefDropdownBtn);
        click(chefDropdownBtn);
        logger.info("Click on Chef Selection");
        return this;
    }

    public PopupPage selectRandomChef() {
        waitToBeClickable(moreChefBtn);
        scrollAndClick(moreChefBtn);
        getRandomElementAndClick(chefs);
        waitToBeClickable(chefOKBtn);
        click(chefOKBtn);
        getChefText(chefCaption);
        return this;
    }

    public PopupPage clickOnMealsSelection() {
        click(mealsDropdownBtn);
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
        waitToBeVisible(mealsCaptionList);
        String meal = element.getText();
        logger.info("Meal selected: " + meal);
    }

    public PopupPage selectRandomBonusMeal() {
        waitToBeVisible(dropdownList);
        WebElement item = getRandomElement(dropDownItems);
        click(item);
        String bonusMeal = bonusMealDropdownBtn.getText();
        logger.info("Bonus meal selected: " + bonusMeal);
        return this;
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

    public PopupPage clickOnDatePickerBtn() {
        click(dateBtn);
        logger.info("Click on date button selection");
        return this;
    }

    private void clickOnYearBtn() {
        click(datePickerYear);
        logger.info("Click on year button selection");
    }

    public void selectYear() {
        clickOnYearBtn();
        getRandomElementAndClick(dpDropdownYears);
        logger.info("Year selected");
    }

    public void clickOnMonthBtn() {
        click(datePickerMonth);
        logger.info("Click on month button selection");
    }

    public void selectMonth() {
        clickOnMonthBtn();
        getRandomElementAndClick(dpDropdownMonths);
        logger.info("Month selected");
    }

    public void selectDay() {
        getRandomElementAndClick(dpDaysInMonth);
        logger.info("Day selected");
    }

    public PopupPage selectDate() {
        selectYear();
        selectMonth();
        selectDay();
        String date = dateInput.getAttribute("value");
        logger.info("Date selected: " + date);
        return this;
    }

    public PopupPage clickSubmitBtn() {
        waitToBeClickable(submitBtn);
        click(submitBtn);
        waitForAlert();
        return this;
    }

    public String getTextFromAlert() {
        Alert alert = driver.switchTo().alert();
        String text = alert.getText();
        alert.accept();
        return text;
    }
}
