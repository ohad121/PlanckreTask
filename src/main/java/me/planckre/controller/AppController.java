package me.planckre.controller;

import com.google.gson.Gson;
import me.planckre.DataUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@RestController
public class AppController {
    @RequestMapping("/")
    public ResponseEntity getRelatedPhrases(@RequestParam("phrase") String phrase) {
        DataUtils dataUtils = new DataUtils();
        ResponseEntity response;

        try {
            response = ResponseEntity.ok(new Gson().toJson(dataUtils.getPopularWordsFromURL("https://en.wikipedia.org/wiki/" + phrase, 10)));
        } catch (IOException e) {
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error while fetching the data");
        }

        return response;
    }
}