package com.example.springplayground.Config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.Matchers.contains;

@TestPropertySource(properties = {
        "wordcount.caseSensitive=false",
        "wordcount.skip[0]=the",
        "wordcount.skip[1]=an",
        "wordcount.skip[2]=a",
})

@RunWith(SpringRunner.class)
@SpringBootTest
public class WordConfigTest {

    @Autowired
    private WordConfig config;

    @Test
    public void testPropertiesAreMappedCorrectly() {
        assertThat(config.isCaseSensitive(), equalTo(false));
        assertThat(config.getSkip(), contains("the", "an", "a"));
    }
}