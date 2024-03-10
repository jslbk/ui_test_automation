package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.math.RoundingMode;
import java.text.DecimalFormat;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class ExchangeRatesPage {
    private final SelenideElement pageTitle = $("h1 span"),
            sellAmountField = $("#sellValue"),
            buyAmountField = $("#buyValue"),
            sellCurrencySection = $(".sell-block"),
            buyCurrencySection = $(".buy-block"),
            sellCurrencySelector = sellCurrencySection.$(".dropdown-toggle"),
            buyCurrencySelector = buyCurrencySection.$(".dropdown-toggle"),
            sellSubmitButton = sellCurrencySection.$(".sell-block .btn"),
            buySubmitButton = buyCurrencySection.$(".buy-block .btn"),
            sellResultLocator = $("#sell-result"),
            buyResultLocator = $("#buy-result"),
            transferTable = $("#transfer_table");

    @Step("Open Exchange rates page")
    public void openExchangeRatesPage() {
        open("/en/exchange-rates");
        pageTitle.shouldHave(text("Exchange rates"));
    }

    @Step("Set amount to sell")
    public ExchangeRatesPage setSellAmount(String amount) {
        sellAmountField.setValue(amount);
        return this;
    }

    @Step
    public ExchangeRatesPage setBuyAmount(String amount) {
        buyAmountField.setValue(amount);
        return this;
    }

    @Step("Select currency to sell")
    public ExchangeRatesPage selectSellCurrency(String currency) {
        executeJavaScript("arguments[0].value = arguments[1];", sellCurrencySelector, currency);
        return this;
    }

    @Step("Select currency to buy")
    public ExchangeRatesPage selectBuyCurrency(String currency) {
        executeJavaScript("arguments[0].value = arguments[1];", buyCurrencySelector, currency);
        return this;
    }

    @Step("Click submit button for sell currency calculations")
    public void clickSellSubmitButton() {
        sellSubmitButton.click();
    }

    @Step("Click submit button for buy currency calculations")
    public void clickBuySubmitButton() {
        buySubmitButton.click();
    }

    @Step("Get bank sells rate in EUR")
    public String getBankSellsRateInEurByCurrency(String currency) {
        return transferTable.$$("tr")
                .findBy(text(currency)).$("td", 2).getText();
    }

    @Step("Get bank buys rate in EUR")
    public String getBankBuysRateInEurByCurrency(String currency) {
        return transferTable.$$("tr")
                .findBy(text(currency)).$("td", 1).getText();
    }

    public SelenideElement getSellResult() {
        return sellResultLocator;
    }

    public SelenideElement getBuyResult() {
        return buyResultLocator;
    }

    @Step("Calculate the exchange result")
    public String calculateResult(String sellAmount, String banksSellsRate) {
        double amount = Double.parseDouble(sellAmount);
        double rate = Double.parseDouble(banksSellsRate);
        double calculations = amount / rate;

        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.HALF_UP);
        String result = df.format(calculations);

        return result.replace(",", ".");
    }

}
