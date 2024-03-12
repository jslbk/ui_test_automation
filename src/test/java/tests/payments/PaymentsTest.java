package tests.payments;

import data.RunTags;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.PaymentsPage;
import tests.TestBase;

import static com.codeborne.selenide.Condition.visible;
import static io.qameta.allure.Allure.step;

@DisplayName("Check payments tests")
@Tag(RunTags.PAYMENTS)
public class PaymentsTest extends TestBase {
    private final PaymentsPage paymentsPage = new PaymentsPage();
    private static final String VALID_IBAN = "GB94BARC10201530093459",
            INVALID_IBAN = "GB94BARC20201530093459";

    @BeforeEach
    void openPaymentPageByUrl() {
        step("Open Payments page by Url", () ->
                paymentsPage.openPaymentPage());
    }

    @Test
    @DisplayName("Check invalid IBAN check error note is displayed")
    public void checkIbanIsNotValidTest() {
        step("Set invalid IBAN number and press 'Check' button", () ->
                paymentsPage.setIbanNumber(INVALID_IBAN).clickCheckButton());
        step("Check error note is displayed", () ->
                paymentsPage.getIbanCheckErrorNote().shouldBe(visible));
    }

    @Test
    @DisplayName("Check valid IBAN check success note is displayed")
    public void checkIbanIsValidTest() {
        step("Set valid IBAN number and press 'Check' button", () ->
                paymentsPage.setIbanNumber(VALID_IBAN)).clickCheckButton();
        step("Check success note is displayed", () ->
                paymentsPage.getIbanCheckSuccessNote().shouldBe(visible));
    }

}
