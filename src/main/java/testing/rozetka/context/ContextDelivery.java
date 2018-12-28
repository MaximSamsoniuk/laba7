package testing.rozetka.context;

import testing.rozetka.page.PageDelivery;

public final class ContextDelivery {
    private final PageDelivery page;

    public ContextDelivery(PageDelivery page) {
        this.page = page;
    }

    public double getTotalCheckoutAmount() {
        return page.getTotalCheckoutAmount();
    }

    public boolean isButtonEnabled() {
        return true;
    }
}
