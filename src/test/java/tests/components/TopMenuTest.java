package tests.components;

import com.codeborne.selenide.Configuration;
import data.Language;
import data.RunTags;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import tests.TestBase;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.CollectionCondition.texts;
import static io.qameta.allure.Allure.step;

@DisplayName("Check top menu options tests")
@Tag(RunTags.MENU)
public class TopMenuTest extends TestBase {

    public static Stream<Arguments> getTopMenuOptionsInAllLanguages() {
        return Stream.of(
                Arguments.of(Language.EN, List.of("Banking services", "Loans", "E-commerce", "Investments", "Contacts and support", "For investors")),
                Arguments.of(Language.LV, List.of("Bankas pakalpojumi", "Kreditēšana", "E-komercija", "Investīcijas", "Kontakti un atbalsts", "Investoriem")),
                Arguments.of(Language.RU, List.of("Банковские услуги", "Кредитование", "Э-коммерция", "Инвестиции", "Поддержка и контакты", "Инвесторам"))
        );
    }

    @MethodSource("getTopMenuOptionsInAllLanguages")
    @ParameterizedTest(name = "Set language {0} and check top menu options are {1}")
    void checkTopMenuOptionsInAnyLanguage(Language language, List<String> topMenuButtons) {
        step("Select language", () -> {
            mainPage.selectLanguage(language.getLanguage());
        });
        step("Verify top menu options", () -> {
            mainPage.verifyTopMenuOptions(topMenuButtons);
        });
    }

    private static Stream<Arguments> getTopMenuLogoHrefInAllLanguages() {
        return Stream.of(
                Arguments.of(Language.EN, Configuration.baseUrl + "en"),
                Arguments.of(Language.LV, Configuration.baseUrl + "lv"),
                Arguments.of(Language.RU, Configuration.baseUrl + "ru")
        );
    }

    @MethodSource("getTopMenuLogoHrefInAllLanguages")
    @ParameterizedTest(name = "Set language {0} and check logo href is {1}")
    void checkTopMenuLogoIsDisplayedAndVerifyHrefInAnyLanguage(Language language, String logoHref) {
        step("Select language", () -> mainPage.selectLanguage(language.getLanguage()));
        step("Verify top menu options", () -> {
            mainPage.getTopMenuLogo().$("a").getAttribute("href").equals(texts(logoHref));
        });
    }

    private static Stream<Arguments> getTopMenuInternetBankLoginButtonInAllLanguages() {
        return Stream.of(
                Arguments.of(Language.EN, "Internet bank", "htps://ib.bluorbank.lv/x/login?language=en"),
                Arguments.of(Language.LV, "Internetbanka", "https://ib.bluorbank.lv/x/login?language=lv"),
                Arguments.of(Language.RU, "Интернет-Банк", "https://ib.bluorbank.lv/x/login?language=ru")
        );
    }

    @MethodSource("getTopMenuInternetBankLoginButtonInAllLanguages")
    @ParameterizedTest(name = "Set language {0} and check login to internet bank button name is {1} and href is {2}")
    void checkTopMenuInternetBankLoginButtonIsDisplayedAndVerifyItHrefAndNameInAnyLanguage(Language language, String name, String ibHref) {
        step("Select language", () -> mainPage.selectLanguage(language.getLanguage()));
        step("Verify top menu options", () -> {
            mainPage.getInternetBankLoginButton().getAttribute("href").equals(texts(ibHref));
            mainPage.getInternetBankLoginButton().getText().equals(texts(name));
        });
    }
}