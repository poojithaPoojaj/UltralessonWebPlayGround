package com.ultralesson.automation.webplayground;

import com.ultralesson.automation.webplayground.models.Item;
import com.ultralesson.automation.webplayground.pages.HomePage;
import com.ultralesson.automation.webplayground.pages.LauncherPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class SearchTest {
    @Test
    public void verifyIfSearchTermShowsRelevantResults() {
        //Arrange
        String searchItem = "Jeans";
        String searchKey = "Jean";
        WebDriver webdriver=null;
        WebDriverManager.chromedriver().setup();
                ChromeOptions  options=new ChromeOptions();
                options.addArguments("--remote-allow-origins=*");
                webdriver= new ChromeDriver(options);
        LauncherPage launcherPage = new LauncherPage(webdriver); // Assume webdriver is created and                                                                      // handy
        launcherPage.navigateTo("https://web-playground.ultralesson.com/");

        //Act
        HomePage homepage = new HomePage(webdriver);
        homepage.search(searchItem);
        List<Item> searchItems = homepage.getSearchItems();

        //Assert
        Assert.assertEquals(4, searchItems.size());
        Assert.assertTrue(searchItems.stream().allMatch(item -> item.getName().contains(searchKey)));
    }

}
