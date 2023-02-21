package com.example.testapps;

import com.example.testapps.case2.CoreTestCase;
import com.example.testapps.ui.ArticlePageObject;
import com.example.testapps.ui.SearchPageObject;

import org.junit.Test;

public class ArticleTests extends CoreTestCase {
    @Test
    public void testCompareArticleTitle() {

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubString("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        String article_title = ArticlePageObject.getArticleTitle();

        assertEquals(
                "We see unexpected title!",
                "Java (programming language)",
                article_title
        );
    }

    @Test
    public void testSwipeArticle() {

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Appium");
        SearchPageObject.clickByArticleWithSubString("Appium");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElementPresent();
        ArticlePageObject.swipeToFooter();
    }

    @Test
    public void testEx6assertElementPresent() {

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubString("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.notWaitTitlePresent();
    }
}
