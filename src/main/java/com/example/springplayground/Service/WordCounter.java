package com.example.springplayground.Service;

import com.example.springplayground.Config.WordConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WordCounter {

    private WordConfig wordCount;

    @Autowired
    public WordCounter(WordConfig wordCount) {
        this.wordCount = wordCount;
    }

    public Map<String, Integer> count(String sentence) {
        boolean isCaseSensitive = wordCount.isCaseSensitive();
        List<String> skip = wordCount.getSkip();

        sentence = (isCaseSensitive) ? sentence: sentence.toLowerCase();
        String[] words = sentence.split(" ");
        Map<String, Integer> wordMap = new HashMap<String, Integer>();


        for (int i = 0 ; i < words.length; i++) {
            String word = words[i];
            if(skip.contains(word)) continue;

            if(wordMap.containsKey(word)){
                int count = wordMap.get(word);
                count ++;
                wordMap.put(word,count);
            }
            else {
                wordMap.put(word, 1);
            }
        }
        return wordMap;
    }
}
