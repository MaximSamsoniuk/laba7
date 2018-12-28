package testing.rozetka.context;

import testing.rozetka.page.PageContact;
import testing.rozetka.page.PageDelivery;

public final class ContextContact {
    private final PageContact page;

    public ContextContact(PageContact page) {
        this.page = page;
    }

    public void fillDetails(String name, String city, String phone, String email) {
        page.fill(name, city, phone, email);
    }

    public ContextDelivery goNext() {
        page.waitUntilLoad();
        page.goNext();
        PageDelivery pageDelivery = new PageDelivery(this.page.getDriver());
        return new ContextDelivery(pageDelivery);
    }
}
