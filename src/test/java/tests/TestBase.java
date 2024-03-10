package tests;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import drivers.WebDriverProvider;
import helpers.Attachments;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import pages.MainPage;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class TestBase {

    public final MainPage mainPage = new MainPage();

    @BeforeAll
    static void beforeAll() {
        WebDriverProvider.setConfig();
    }

    @BeforeEach
    void addListener() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @Step
    public void acceptCookiesIfNeeded() {
        try {
            $("#GDPR-modal").shouldBe(visible, Duration.ofSeconds(10));
            $("#allowAllGDPR").click();
        } catch (NoSuchElementException | TimeoutException e) {
            System.out.println("Modal pop-up not found or did not appear within the specified time.");
        }
    }

    @AfterEach
    void afterEach() {
        Attachments.screenshotAs("Screenshot");
        Attachments.pageSource();
        Attachments.browserConsoleLogs();
        Attachments.addVideo();

        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
        Selenide.closeWebDriver();
    }
}