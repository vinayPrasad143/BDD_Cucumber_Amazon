package stepdefinitions;

import base.BaseTest;
import io.cucumber.java.en.*;
import org.testng.Assert;
import pages.AmazonHomePage;
import pages.SearchResultsPage;
import utils.StepLogger;

public class SearchSteps extends BaseTest {

    AmazonHomePage homePage;
    SearchResultsPage searchPage;

    @Given("user launches the Amazon Website")
    public void user_launches_the_amazon_website() {
        String step = "Given user launches the Amazon Website";
        try {
            homePage = new AmazonHomePage(driver);
            searchPage = new SearchResultsPage(driver);
            StepLogger.logPass(step);
        } catch (Exception e) {
            StepLogger.logFail(step, e, driver);
            throw e;
        }
    }

    @When("user searches for {string} using search bar")
    public void user_searches_for_using_search_bar(String product) {
        String step = "When user searches for \"" + product + "\" using search bar";
        try {
            homePage.searchProduct(product);
            StepLogger.logPass(step);
        } catch (Exception e) {
            StepLogger.logFail(step, e, driver);
            throw e;
        }
    }

    @Then("the serach results page should display relevant items for {string}")
    public void the_search_results_page_should_display_relevant_items_for(String product) {
        String step = "Then the search results page should display relevant items for \"" + product + "\"";
        try {
            String actual = searchPage.getSearchResultText().toLowerCase().trim();
            String expected = product.toLowerCase().trim();
            Assert.assertTrue(actual.contains(expected), "Search result mismatch!");
            StepLogger.logPass(step);
        } catch (Exception e) {
            StepLogger.logFail(step, e, driver);
            throw e;
        }
    }

    @Then("the page title should reflect the searched {string}")
    public void the_page_title_should_reflect_the_searched(String product) {
        String step = "Then the page title should reflect the searched \"" + product + "\"";
        try {
            String title = searchPage.getPageTitle().toLowerCase();
            Assert.assertTrue(title.contains(product.toLowerCase()), "Title mismatch!");
            StepLogger.logPass(step);
        } catch (Exception e) {
            StepLogger.logFail(step, e, driver);
            throw e;
        }
    }

    @Then("the browser URL should include the search term {string}")
    public void the_browser_url_should_include_the_search_term(String product) {
        String step = "Then the browser URL should include the search term \"" + product + "\"";
        try {
            String url = searchPage.getCurrentUrl().toLowerCase();
            Assert.assertTrue(url.contains(product.toLowerCase()), "URL mismatch!");
            StepLogger.logPass(step);
        } catch (Exception e) {
            StepLogger.logFail(step, e, driver);
            throw e;
        }
    }

    @Then("filtering options should be available on the left side")
    public void filtering_options_should_be_available_on_the_left_side() {
        String step = "Then filtering options should be available on the left side";
        try {
            Assert.assertTrue(searchPage.isFilterSectionVisible(), "Filter section not found!");
            StepLogger.logPass(step);
        } catch (Exception e) {
            StepLogger.logFail(step, e, driver);
            throw e;
        }
    }

    @Then("a list of product results should be visible to the user")
    public void a_list_of_product_results_should_be_visible_to_the_user() {
        String step = "Then a list of product results should be visible to the user";
        try {
            Assert.assertTrue(searchPage.isFirstProductVisible(), "Product results not visible");
            Assert.assertTrue(searchPage.arePricesVisible(), "Prices missing");
            Assert.assertTrue(searchPage.areRatingsVisible(), "Ratings missing");
            StepLogger.logPass(step);
        } catch (Exception e) {
            StepLogger.logFail(step, e, driver);
            throw e;
        }
    }
}
