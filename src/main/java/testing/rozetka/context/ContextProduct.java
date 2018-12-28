package testing.rozetka.context;

import testing.rozetka.page.PageContact;
import testing.rozetka.page.PageProduct;

public final class ContextProduct {
    private final PageProduct page;

    public ContextProduct(PageProduct page) {
        this.page = page;
    }

    public double getPrice() {
        return page.getPrice();
    }

    public void purchase() {
        page.doPurchase();
    }

    public ContextContact goCheckout() {
        page.doCheckout();
        PageContact pageContact = new PageContact(page.getDriver());
        return new ContextContact(pageContact);
    }
}
