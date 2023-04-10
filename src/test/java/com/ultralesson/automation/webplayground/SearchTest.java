package com.ultralesson.automation.webplayground;

import com.ultralesson.automation.webplayground.models.Item;
import com.ultralesson.automation.webplayground.pages.HomePage;
import com.ultralesson.automation.webplayground.pages.LauncherPage;
import org.openqa.selenium.WebDriver;
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
