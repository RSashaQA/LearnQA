package com.example.testapps;

import com.example.testapps.case2.CoreTestCase;
import com.example.testapps.ui.SearchPageObject;

import org.junit.Test;

public class ElementHasTextTests extends CoreTestCase {

    @Test
    public void testElementHasText() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        String expected_value = "Search Wikipedia";
        SearchPageObject.elementHasText(expected_value);
    }
}
