package tests.exchange;

import data.RunTags;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.ExchangeRatesPage;
import tests.TestBase;

import static com.codeborne.selenide.Condition.text;
import static io.qameta.allure.Allure.step;

@DisplayName("Check currency calculator tests")
@Tag(RunTags.EXCHANGE)
public class ExchangeTest extends TestBase {
    private final ExchangeRatesPage exchangePage = new ExchangeRatesPage();

    @BeforeEach
    void openPaymentPageByUrl() {
        step("Open Exchange rates page by Url", exchangePage::openExchangeRatesPage);
        acceptCookiesIfNeeded();
    }

    @Test
    @DisplayName("Check exchange SELL calculations are correct")
    public void exchangeCalculationsSellTest() {
        String bankSellsRate = exchangePage.getBankSellsRateInEurByCurrency("GBP");
        String sellAmount = "123.45";

        step("Set currency to SELL and click submit button", () ->
                exchangePage.setSellAmount(sellAmount)
                        .selectSellCurrency("GBP")
                        .clickSellSubmitButton());
        step("Verify calculated result", () ->
                exchangePage.getSellResult().shouldHave(text(exchangePage.calculateResult(sellAmount, bankSellsRate))));
    }

    @Test
    @DisplayName("Check exchange BUY calculations are correct")
    public void exchangeCalculationsBuyTest() {
        String bankBuysRate = exchangePage.getBankBuysRateInEurByCurrency("CZK");
        String buyAmount = "123.45";

        step("Set currency to BUY and click submit button", () ->
                exchangePage.setBuyAmount(buyAmount)
                        .selectBuyCurrency("CZK")
                        .clickBuySubmitButton());
        step("Verify calculated result", () ->
                exchangePage.getBuyResult().shouldHave(text(exchangePage.calculateResult(buyAmount, bankBuysRate))));
    }

}
