package tests.components;

import com.codeborne.selenide.Configuration;
import data.Language;
import data.RunTags;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import tests.TestBase;

import java.util.List;
import java.util.stream.Stream;

import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Check top menu options tests")
@Tag(RunTags.MENU)
public class TopMenuTest extends TestBase {

    @BeforeEach
    void openPaymentPageByUrl() {
        step("Open Main page by Url", () ->
                mainPage.openMainPage());
        acceptCookiesIfNeeded();
    }

    public static Stream<Arguments> getTopMenuOptionsInAllLanguages() {
        return Stream.of(
                Arguments.of(Language.EN, List.of("Banking services", "Loans", "E-commerce", "Investments", "Contacts and support", "For investors")),
                Arguments.of(Language.LV, List.of("Bankas pakalpojumi", "Kreditēšana", "E-komercija", "Investīcijas", "Kontakti un atbalsts", "Investoriem")),
                Arguments.of(Language.RU, List.of("Банковские услуги", "Кредитование", "Э-коммерция", "Инвестиции", "Поддержка и контакты", "Инвесторам"))
        );
    }

    @MethodSource("getTopMenuOptionsInAllLanguages")
    @DisplayName("Check available top menu options")
    @ParameterizedTest(name = "Set language {0} and check top menu options are {1}")
    void checkTopMenuOptionsInAvailableLanguagesTest(Language language, List<String> topMenuButtons) {
        step("Select language", () ->
                mainPage.selectLanguage(language.getLanguage()));
        step("Verify top menu options", () ->
                mainPage.verifyTopMenuOptions(topMenuButtons));
    }

    private static Stream<Arguments> getTopMenuLogoHrefInAllLanguages() {
        return Stream.of(
                Arguments.of(Language.EN, Configuration.baseUrl + "/en"),
                Arguments.of(Language.LV, Configuration.baseUrl + "/lv"),
                Arguments.of(Language.RU, Configuration.baseUrl + "/ru")
        );
    }

    @MethodSource("getTopMenuLogoHrefInAllLanguages")
    @DisplayName("Check top menu logo link")
    @ParameterizedTest(name = "Set language {0} and check logo href is {1}")
    void checkTopMenuLogoIsDisplayedAndVerifyHrefInAvailableLanguagesTest(Language language, String logoHref) {
        step("Select language", () -> mainPage.selectLanguage(language.getLanguage()));
        step("Verify top menu options", () ->
                assertEquals(logoHref, mainPage.getTopMenuLogo().$("a").getAttribute("href")));
    }


    private static Stream<Arguments> getTopMenuInternetBankLoginButtonInAvailableLanguages() {
        return Stream.of(
                Arguments.of(Language.EN, "Internet bank", "https://ib.bluorbank.lv/x/login?language=en"),
                Arguments.of(Language.LV, "Internetbanka", "https://ib.bluorbank.lv/x/login?language=lv"),
                Arguments.of(Language.RU, "Интернет-Банк", "https://ib.bluorbank.lv/x/login?language=ru")
        );
    }

    @MethodSource("getTopMenuInternetBankLoginButtonInAvailableLanguages")
    @DisplayName("Check top menu Internet bank button link")
    @ParameterizedTest(name = "Set language {0} and check login to internet bank button name is {1} and href is {2}")
    void checkTopMenuInternetBankLoginButtonIsDisplayedAndVerifyItHrefAndNameInAvailableLanguagesTest(Language language, String name, String ibHref) {
        step("Select language", () -> mainPage.selectLanguage(language.getLanguage()));
        step("Verify top menu options", () -> {
            assertEquals(ibHref, mainPage.getInternetBankLoginButton().getAttribute("href"));
            assertEquals(name, mainPage.getInternetBankLoginButton().getText());
        });
    }
}