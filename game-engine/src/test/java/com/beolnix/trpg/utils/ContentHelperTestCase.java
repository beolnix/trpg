package com.beolnix.trpg.utils;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Created by beolnix on 30/08/15.
 */
public class ContentHelperTestCase {

    @Test
    public void getContentTest() {
        String expectedContent = "some test content here";
        String content = ContentHelper.getContent("/content/some_test_content.txt");

        assertEquals(expectedContent, content);
    }
}
