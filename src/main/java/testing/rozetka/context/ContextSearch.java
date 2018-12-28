package testing.rozetka.context;

import testing.rozetka.page.PageCategory;
import testing.rozetka.page.PageProduct;

import java.util.List;
import java.util.stream.Collectors;

public final class ContextSearch {
    private final PageCategory page;

    public ContextSearch(PageCategory page) {
        this.page = page;
    }

    public void filterByPrice(int min, int max) {
        page.waitUntilLoad();
        page.setPriceRange(min, max);
    }

    public void filterByCountry(String country) {
        page.waitUntilLoad();
        page.setProducingCountry(country);
    }

    public List<String> getFoundProductNames() {
        page.waitUntilLoad();
        return page.getResults().stream().map(PageCategory.Result::getTitle).collect(Collectors.toList());
    }

    public ContextProduct goToFirstResult() {
        page.waitUntilLoad();
        page.clickFirstResult();
        PageProduct pageProduct = new PageProduct(this.page.getDriver());
        return new ContextProduct(pageProduct);
    }
}
