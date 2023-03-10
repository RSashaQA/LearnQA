package com.example.testapps;

import com.example.testapps.case2.CoreTestCase;
import com.example.testapps.ui.ArticlePageObject;
import com.example.testapps.ui.NavigationUi;
import com.example.testapps.ui.SearchPageObject;

import org.junit.Test;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchTests extends CoreTestCase {

    @Test
    public void testSearch() {

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");

    }

    @Test
    public void testCancelSearch() {

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForCancelButtonToDisappear();
    }

    @Test
    public void testAmountOfNotEmptySearch() {

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        String search_line = "Linkin park Discography";
        SearchPageObject.typeSearchLine(search_line);
        int amount_of_search_results = SearchPageObject.getAmountOfFoundArticles();

        assertTrue("we found too few results!", amount_of_search_results > 0);
    }

    @Test
    public void testAmountOfEmptySearch() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        String search_line = "55654Инна";
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.waitForEmptyResultsLabel();
        SearchPageObject.assertThereIsNoResultOfSearch();
    }

    @Test
    public void testSearchSomeArticle() {

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        String search_line = "Vodka";
        SearchPageObject.typeSearchLine(search_line);

        int my_elements = SearchPageObject.getAmountOfFoundArticles();

        assertTrue("Size of elements is 0", my_elements > 1);

        SearchPageObject.clickCancelSearch();
        SearchPageObject.assertThereIsNoResultOfSearch();
    }

    @Test
    public void testAllOfSearchResultContainsText() {

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        String search_line = "Java";
        SearchPageObject.typeSearchLine(search_line);


        List<WebElement> myElements = SearchPageObject.titleOfFoundedArticles();

        String desired_value = "java";

        for (WebElement myElement : myElements) {
            assertTrue("Some elements do not contains the " + "'" + desired_value + "'",
                    myElement.getText().toLowerCase().contains(desired_value));
        }
    }

    @Test
    public void testTwoArticleToMyListThenDeleteOneOfThem() {

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubString("Java (programming language)");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElementPresent();
        String name_of_folder = "Learning programming";
        ArticlePageObject.addArticleToMyList(name_of_folder);
        ArticlePageObject.closeArticle();

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubString("Java version history");
        ArticlePageObject.waitForTitleElementPresent();
        ArticlePageObject.addSecondArticleToMyList(name_of_folder);
        ArticlePageObject.closeArticle();

        NavigationUi NavigationUi = new NavigationUi(driver);
        NavigationUi.clickMyList();
        NavigationUi.clickMyFolder(name_of_folder);
        NavigationUi.swipeElementToLeftForDeleteArticleInMyList("Java version history");

        String title_text_in_folder = ArticlePageObject.getArticleTitleInMyList();

        NavigationUi.clickToArticleInMyList("Java (programming language)");

        String title_text_inside_article = ArticlePageObject.getArticleTitle();

        assertEquals(
                "Title text in folder and inside article doesn't match",
                title_text_in_folder,
                title_text_inside_article
        );
    }

    @Test
    public void testSearchArticlesByTitleAndDescription() {

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        String search_input = "Java";
        String first_title = "Java";
        String first_description = "Island of Indonesia, Southeast Asia";
        String second_title = "JavaScript";
        String second_description = "High-level programming language";
        String third_title = "Java (programming language)";
        String third_description = "Object-oriented programming language";
        SearchPageObject.typeSearchLine(search_input);
        SearchPageObject.waitForElementByTitleAndDescription(first_title, first_description);
        SearchPageObject.waitForElementByTitleAndDescription(second_title, second_description);
        SearchPageObject.waitForElementByTitleAndDescription(third_title, third_description);
    }
}