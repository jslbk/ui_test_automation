package tests.components;

import data.RunTags;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.TestBase;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Check scroll to top element test")
@Tag(RunTags.MAIN)
public class ScrollToTopElementTest extends TestBase {

    @BeforeEach
    void openPaymentPageByUrl() {
        step("Open Main page by Url", mainPage::openMainPage);
        acceptCookiesIfNeeded();
    }
    @Test
    void scrollDownAndCheckBackToTopElementIsDisplayed() {
        step("Scroll down and check element is visible", () ->
                mainPage.scrollDown());
        step("Check that 'Back to top' element is displayed", () ->
                assertThat(mainPage.getBackToTopElement().isDisplayed()));
    }

}