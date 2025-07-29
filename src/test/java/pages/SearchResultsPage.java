package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchResultsPage {

    private WebDriver driver;

    private By resultText = By.cssSelector("div.s-desktop-width-max span.a-color-state.a-text-bold");
    private By searchResults = By.cssSelector("div.s-main-slot div[data-component-type='s-search-result']");
    private By productTitles = By.cssSelector("div.s-main-slot h2 span");
    private By priceTags = By.cssSelector("span.a-price span.a-offscreen");
    private By ratings = By.cssSelector("div.a-row.a-size-small span.a-icon-alt");
    private By filterSection = By.id("s-refinements");
    private By addToCartButtons = By.xpath("//input[@aria-labelledby[contains(., 'add-to-cart')]]");

    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getSearchResultText() {
        return driver.findElement(resultText).getText().replace("\"", "").trim();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public boolean isFilterSectionVisible() {
        return driver.findElement(filterSection).isDisplayed();
    }

    public boolean isFirstProductVisible() {
        return driver.findElements(searchResults).size() > 0;
    }

    public boolean doAllProductTitlesContain(String keyword) {
        List<WebElement> titles = driver.findElements(productTitles);
        if (titles.isEmpty()) return false;
        for (WebElement title : titles) {
            if (!title.getText().toLowerCase().contains(keyword.toLowerCase())) {
                return false;
            }
        }
        return true;
    }

    public boolean arePricesVisible() {
        return driver.findElements(priceTags).size() > 0;
    }

    public boolean areRatingsVisible() {
        return driver.findElements(ratings).size() > 0;
    }

    public boolean isAddToCartPresent() {
        return driver.findElements(addToCartButtons).size() > 0;
    }
}
