package testing.rozetka.element;

import org.openqa.selenium.Keys;

public final class ElementTextField extends Element {
    public ElementTextField() {
    }

    public void clear() {
        base.sendKeys(Keys.CONTROL + "a");
        base.sendKeys(Keys.DELETE);
    }

    public void append(String text) {
        base.sendKeys(text);
    }

    public void write(String text) {
        this.clear();
        this.append(text);
    }
}
