package stepdefinitions;

import base.BaseTest;
import io.cucumber.java.en.*;
import org.testng.Assert;
import pages.AmazonHomePage;
import pages.SearchResultsPage;
import utils.ExcelUtil;
import utils.StepLogger;

import java.util.List;

public class SearchExcelSteps extends BaseTest {

	AmazonHomePage homePage;
	SearchResultsPage searchPage;

	@Then("user searches all products from Excel and validates results")
	public void user_searches_all_products_from_excel() {
		String step = "Then user searches and validates Excel-driven products";
		try {
			String excelPath = "src/test/resources/testdata/AmazonSearchData1.xlsx";
			List<String> products = ExcelUtil.getProductsFromExcel(excelPath, "Sheet1");

			for (String product : products) {
				homePage.searchProduct(product);
				Assert.assertTrue(searchPage.getSearchResultText().toLowerCase().contains(product.toLowerCase()),
						" Search result validation failed for: " + product);
				StepLogger.logPass("🔍 Verified search result for: " + product);
			}

			StepLogger.logPass(step);
		} catch (Exception e) {
			StepLogger.logFail(step, e, driver);
			throw e;
		}
	}
}
