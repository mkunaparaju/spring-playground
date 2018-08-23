package com.example.springplayground.Service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class WordCounter {


    public Map<String, Integer> count (String sentence) {
        String [] words = sentence.split(" ");
        Map<String, Integer> wordCount = new HashMap<String, Integer>();

        for (int i = 0 ; i < words.length; i++) {
            String word = words[i];

            if(wordCount.containsKey(word)){
                int count = wordCount.get(word);
                count ++;
                wordCount.put(word,count);
            }
            else {
                wordCount.put(word, 1);
            }
        }
        return wordCount;
    }
}
