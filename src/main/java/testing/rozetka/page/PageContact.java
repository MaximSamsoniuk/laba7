package testing.rozetka.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import testing.rozetka.element.ElementTextField;

import static org.openqa.selenium.support.ui.ExpectedConditions.attributeContains;
import static org.openqa.selenium.support.ui.ExpectedConditions.not;

public final class PageContact extends Page {
    @FindBy(id = "reciever_name")
    private ElementTextField nameInput;

    @FindBy(id = "suggest_locality")
    private ElementTextField cityInput;

    @FindBy(id = "reciever_phone")
    private ElementTextField phoneInput;

    @FindBy(id = "reciever_email")
    private ElementTextField emailInput;

    @FindBy(xpath = "//*[@id=\"step_contacts\"]/div/div[1]/div[2]/div/span/button")
    private WebElement buttonNext;

    public PageContact(WebDriver driver) {
        super(driver, null);
    }

    public void fill(String name, String city, String phone, String email) {
        nameInput.write(name);
        if (!cityInput.getText().equalsIgnoreCase(city)) {
            cityInput.write(city);
        }
        phoneInput.write(phone);
        emailInput.write(email);
    }

    public void goNext() {
        buttonNext.click();
    }

    public void waitUntilLoad() {
        String xpathExpression = "//*[@id=\"step_contacts\"]/div/div[1]/div[2]/div/span";
        WebDriverWait driverWait = new WebDriverWait(driver, TIMEOUT);
        driverWait.until(not(attributeContains(By.xpath(xpathExpression), "class", "disabled")));
    }
}
