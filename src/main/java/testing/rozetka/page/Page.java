package testing.rozetka.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.FieldDecorator;
import org.openqa.selenium.support.ui.WebDriverWait;
import testing.rozetka.element.decorator.SimpleFieldDecorator;

import javax.annotation.Nullable;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public abstract class Page {
    public static final int TIMEOUT = 10;

    protected final WebDriver driver;

    protected Page(WebDriver driver, @Nullable String url) {
        this.driver = driver;
        inject(url);
    }

    private void inject(@Nullable String url) {
        if (url != null) this.driver.get(url);
        FieldDecorator decorator = new SimpleFieldDecorator(driver);
        PageFactory.initElements(decorator, this);
    }

    public final void waitUntilElement(String xpathExpression) {
        WebDriverWait driverWait = new WebDriverWait(driver, TIMEOUT);
        driverWait.until(presenceOfElementLocated(By.xpath(xpathExpression)));
    }

    public WebDriver getDriver() {
        return driver;
    }
}
