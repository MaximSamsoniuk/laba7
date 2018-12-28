package testing.rozetka.element;

import org.openqa.selenium.WebElement;

public abstract class Element {
    protected WebElement base;

    protected Element() {
    }

    public final void setBase(WebElement base) {
        this.base = base;
    }

    public void click() {
        this.base.click();
    }

    public String getText() {
        String text = base.getText();
        if (text != null && !text.isEmpty()) {
            return text;
        }

        String value = base.getAttribute("value");
        if (value == null) {
            return "";
        }

        return value;
    }
}
