package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.util.List;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class MainPage {

    private final SelenideElement burgerMenuLocator = $("[for=drop-down-cbox]"),
            internetBankButtonLocator = $(".i-internetbank"),
            logoLocator = $(".i-logo"),
            languagePickerLocator = $("#languages"),
            pageTitle = $("h1 span");

    @Step("Open Main page")
    public void openMainPage() {
        open("/en");
        pageTitle.shouldHave(text("Business banking made better"));
    }

    public SelenideElement getTopMenuLanguagePicker() {
        return languagePickerLocator;
    }

    public SelenideElement getInternetBankLoginButton() {
        return internetBankButtonLocator;
    }

    public SelenideElement getTopMenuLogo() {
        return logoLocator;
    }

    public void selectLanguage(String language) {
        if (!getTopMenuLanguagePicker().getText().equals(language)) {
            getTopMenuLanguagePicker().parent().click();
            getTopMenuLanguages().findBy(text(language)).click();
            Selenide.sleep(5000);
        }
    }

    private ElementsCollection getTopMenuOptions() {
        return $$(".root-menu-item a");
    }

    private ElementsCollection getTopMenuLanguages() {
        return $$("[class='dropdown-menu languages'] li a");
    }

    private SelenideElement getBurgerMenuElement() {
        return burgerMenuLocator;
    }

    @Step("Verify top menu options in available languages")
    public void verifyTopMenuOptions(List<String> topMenuButtons) {
        if (getBurgerMenuElement().isDisplayed()) {
            getBurgerMenuElement().click();
        }
        getTopMenuOptions().filter(visible).shouldHave(texts(topMenuButtons));
    }

}