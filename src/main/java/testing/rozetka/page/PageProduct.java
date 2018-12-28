package testing.rozetka.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import testing.rozetka.element.ElementHtmlLabel;

public final class PageProduct extends Page {
    private static final String BUTTON_CHECKOUT_XPATH = "//*[@id=\"popup-checkout\"]";

    @FindBy(id = "price_label")
    private ElementHtmlLabel price;

    @FindBy(xpath = "//*[@name=\"topurchases\"]")
    private WebElement buttonPurchase;

    @FindBy(xpath = BUTTON_CHECKOUT_XPATH)
    private WebElement buttonCheckout;

    public PageProduct(WebDriver driver) {
        super(driver, null);
    }

    public double getPrice() {
        return Double.parseDouble(price.getText());
    }

    public void doPurchase() {
        buttonPurchase.click();
    }

    public void doCheckout() {
        waitUntilElement(BUTTON_CHECKOUT_XPATH);
        buttonCheckout.click();
    }
}
