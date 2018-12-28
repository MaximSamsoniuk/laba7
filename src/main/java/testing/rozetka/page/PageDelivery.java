package testing.rozetka.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import testing.rozetka.element.ElementHtmlLabel;

public final class PageDelivery extends Page {
    @FindBy(id = "total_checkout_amount")
    private ElementHtmlLabel priceLabel;

    @FindBy(id = "make-order")
    private WebElement buttonConfirm;

    public PageDelivery(WebDriver driver) {
        super(driver, null);
    }

    public double getTotalCheckoutAmount() {
        return Double.parseDouble(priceLabel.getText().replaceAll("\\s+", ""));
    }

    public void doConfirm() {
        /* We don't really want to do this, right? :) */
//        buttonConfirm.click();
    }

    public boolean isConfirmButtonEnabled() {
        return buttonConfirm.isEnabled();
    }
}
