package testing.rozetka.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import testing.rozetka.element.ElementHtmlLabel;
import testing.rozetka.element.ElementTextField;

import java.util.List;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkArgument;
import static org.openqa.selenium.support.ui.ExpectedConditions.invisibilityOf;

public final class PageCategory extends Page {
    @FindBy(id = "price[min]")
    private ElementTextField minPriceInput;

    @FindBy(id = "price[max]")
    private ElementTextField maxPriceInput;

    @FindBy(id = "submitprice")
    private WebElement submitPriceButton;

    @FindBy(xpath = "//*[starts-with(@id, \"filter_strana-proizvoditel\")]/label/a/span/i[1]")
    private List<ElementHtmlLabel> countries;

    @FindBy(xpath = "//*[@id=\"catalog_goods_block\"]/div/div/div[1]/div/div/div/div[3]/a")
    private List<WebElement> results;

    @FindBy(xpath = "/html/body/div[3]")
    protected WebElement progressBar;

    public PageCategory(WebDriver driver, String url) {
        super(driver, url);
    }

    public void waitUntilLoad() {
        if (progressBar == null) return;
        WebDriverWait driverWait = new WebDriverWait(driver, TIMEOUT);
        driverWait.until(invisibilityOf(progressBar));
    }

    public void setPriceRange(int min, int max) {
        checkArgument(min >= 0, "min cannot be negative");
        checkArgument(max > min, "max cannot be less than min");
        minPriceInput.write(String.valueOf(min));
        maxPriceInput.write(String.valueOf(max));
        submitPriceButton.click();
    }

    public void setProducingCountry(String country) {
        for (ElementHtmlLabel label : countries) {
            if (label.getText().equalsIgnoreCase(country)) {
                label.click();
                break;
            }
        }
    }

    public void clickFirstResult() {
        results.get(0).click();
    }

    public List<Result> getResults() {
        return results.stream()
                .map(el -> new Result(el.getText(), el.getAttribute("href")))
                .collect(Collectors.toList());
    }

    public static final class Result {
        private final String title;
        private final String url;

        private Result(String title, String url) {
            this.title = title;
            this.url = url;
        }

        public String getTitle() {
            return title;
        }

        public String getURL() {
            return url;
        }
    }
}
