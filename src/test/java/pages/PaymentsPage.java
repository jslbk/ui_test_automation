package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class PaymentsPage {

    private final SelenideElement ibanCheckField = $("#iban_input"),
            ibanCheckButton = $(".col-sm-offset-7 .btn"),
            ibanCheckErrorNote = $(".alert-danger"),
            ibanCheckSuccessNote = $(".alert-success"),
            pageTitle = $("h1 span");

    @Step("Open Payments page")
    public void openPaymentPage() {
        open("/en/payments");
        pageTitle.shouldHave(text("Payments"));
    }

    @Step("Set IBAN number")
    public PaymentsPage setIbanNumber(String iban) {
        ibanCheckField.setValue(iban);
        return this;
    }

    @Step("Click IBAN check button")
    public PaymentsPage clickCheckButton() {
        ibanCheckButton.click();
        return this;
    }

    public SelenideElement getIbanCheckErrorNote() {
        return ibanCheckErrorNote;
    }

    public SelenideElement getIbanCheckSuccessNote() {
        return ibanCheckSuccessNote;
    }
}
