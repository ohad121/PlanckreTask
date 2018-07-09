package me.planckre;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.*;

public class DataUtils {

    public ArrayList<String> getPopularWordsFromURL(String URL, int numOfWords) throws IOException {
        ArrayList<String> popularPhrases = new ArrayList<>();
        Set<String> commonUsedEnglishWords = new HashSet<>(Arrays.asList("retrieved", "used", "main", "between", "while", "archived", "while", "trough", "new", "most", "such", "also", "original", "new", "the","of","and","a","to","in","is","you","that","it","he","was","for","on","are","as","with","his","they","I","at","be","this","have","from","or","one","had","by","word","but","not","what","all","were","we","when","your","can","said","there","use","an","each","which","she","do","how","their","if","will","up","other","about","out","many","then","them","these","so","some","her","would","make","like","him","into","time","has","look","two","more","write","go","see","number","no","way","could","people","my","than","first","water","been","call","who","oil","its","now","find","long","down","day","did","get","come","made","may","part"));
        Document doc = Jsoup.connect(URL).get();
        String text = doc.body().text();
        List<String> words = Arrays.asList(text.split(" "));
        HashMap<String, Integer> wordsCount = new HashMap<>();
        for (String word : words) {
            if (word.matches("[a-zA-Z]+")) {
                word = word.toLowerCase();
                if (word.length() > 2 && !commonUsedEnglishWords.contains(word)) {
                    if (wordsCount.containsKey(word)) {
                        wordsCount.put(word, wordsCount.get(word) + 1);
                    } else {
                        wordsCount.put(word, 1);
                    }
                }
            }
        }

        ArrayList<Map.Entry<String, Integer>> list = new ArrayList<>(wordsCount.entrySet());
        //sorting the list with a comparator
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });

        for (int i = 0; i < numOfWords; i++) {
            popularPhrases.add(list.get(i).getKey());
        }

        return popularPhrases;
    }
}
