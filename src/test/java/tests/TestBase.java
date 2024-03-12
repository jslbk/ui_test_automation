package tests;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import drivers.WebDriverProvider;
import helpers.Attachments;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.Cookie;
import pages.MainPage;

import static com.codeborne.selenide.Selenide.open;

public class TestBase {

    public final MainPage mainPage = new MainPage();

    @BeforeAll
    static void setUp() {
        WebDriverProvider.setConfig();
    }

    @BeforeEach
    void addListener() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        addBankConsentCookie();
    }

    @Step
    public static void addBankConsentCookie() {
        open("/favicon.ico");

        Cookie consentCookie = new Cookie("Bank.Consent.Cookie", WebDriverProvider.config.getBankConsentCookieValue());
        WebDriverRunner.getWebDriver().manage().addCookie(consentCookie);
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