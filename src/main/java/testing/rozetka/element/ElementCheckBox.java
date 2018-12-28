package testing.rozetka.element;

public final class ElementCheckBox extends Element {
    public ElementCheckBox() {
    }

    public void select() {
        if (!base.isSelected()) {
            base.click();
        }
    }
}
