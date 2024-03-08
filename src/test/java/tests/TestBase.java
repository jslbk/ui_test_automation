package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import drivers.WebDriverProvider;
import helpers.Attachments;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import pages.MainPage;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class TestBase {

    public MainPage mainPage = new MainPage();

    @BeforeAll
    static void beforeAll() {
        WebDriverProvider.setConfig();
    }

    @BeforeEach
    void setUp() {
        step("Open site by url", () -> {
            open(Configuration.baseUrl);
            acceptCookiesIfNeeded();
        });
    }

    protected void acceptCookiesIfNeeded() {
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