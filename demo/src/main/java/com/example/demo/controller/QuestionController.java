package com.example.demo.controller;

import com.example.demo.model.CatalogQuestionsModel;
import com.example.demo.service.QuestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

//@Api
@RestController
@RequestMapping("/question")
@Slf4j
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @PostMapping("/askQuestion")
    public ResponseEntity askQuestion(@RequestParam("file") MultipartFile file){
        Object object = questionService.askQuestion(file);
        log.info(" returned object = "+ object);
        ResponseEntity responseEntity = new ResponseEntity(object, HttpStatus.OK);
        log.info(" response entity = "+ responseEntity);
        return responseEntity;
    }

    @PostMapping("/viewQuestion")
    public ResponseEntity viewQuestion( @RequestParam  Long id) {
        Object object = questionService.viewQuestion(id);

        ResponseEntity responseEntity = new ResponseEntity(object, HttpStatus.OK);
        return responseEntity;
    }

    @PostMapping("/addCatalogQuestion")
    public ResponseEntity addCatalogQuestion(@RequestBody CatalogQuestionsModel model){
        Object object = questionService.addCatalogQuestion(model);
        ResponseEntity responseEntity = new ResponseEntity(object, HttpStatus.OK);
        return responseEntity;
    }
}
