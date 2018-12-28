package testing.rozetka;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import testing.rozetka.context.ContextContact;
import testing.rozetka.context.ContextDelivery;
import testing.rozetka.context.ContextProduct;
import testing.rozetka.context.ContextSearch;
import testing.rozetka.page.PageCategory;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RozetkaTest {
    @Test
    void doesStuff() {
        final String driverPath = System.getProperty("user.dir") + "\\driver\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", driverPath);

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        final String url = "https://bt.rozetka.com.ua/163904/c163904/";

        ContextSearch contextSearch = new ContextSearch(new PageCategory(driver, url));
        contextSearch.filterByPrice(100, 2000);
        contextSearch.filterByCountry("украина");

        sleep(3000);

        ContextProduct contextProduct = contextSearch.goToFirstResult();
        contextProduct.purchase();

        sleep(3000);

        ContextContact contextContact = contextProduct.goCheckout();
        contextContact.fillDetails("John Smith", "Киев", "+380951234567", "john.smith@example.com");

        sleep(3000);

        ContextDelivery contextDelivery = contextContact.goNext();
        assertTrue(contextDelivery.isButtonEnabled());

        sleep(5000);

        driver.quit();
    }

    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (Throwable t) {
            throw new RuntimeException(t);
        }
    }
}
