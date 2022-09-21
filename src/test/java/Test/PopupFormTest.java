package Test;

import Base.BaseTest;
import Page.PopupFormPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PopupFormTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(PopupFormPage.class);

    PopupFormPage popupFormPage = new PopupFormPage(driver);

    @Test
    @DisplayName("Popup Form Test")
    public void fillInPopupForm() {
        popupFormPage.openPopup()
                     .setUpName()
                     .clickOnDropdownList()
                     .selectRandomBonusMeal();
    }

}
