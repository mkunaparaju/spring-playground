package com.example.springplayground.Service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WordCounterTest {

    @Autowired
    private WordCounter wordCounter;

    @Test
    public void testGetWordCount(){
        String inputString = "test this test of a service";


        Map<String, Integer> expected = new HashMap<>();
        expected.put("test", 2);
        expected.put("this", 1);
        expected.put("a", 1);
        expected.put("of", 1);
        expected.put("service", 1);

        Map<String, Integer> actual = wordCounter.count(inputString);

        assertTrue(expected.entrySet().containsAll(actual.entrySet()));

    }
}