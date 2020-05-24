package com.example.demo.service;

import com.example.demo.model.CatalogQuestionsModel;
import org.springframework.web.multipart.MultipartFile;


public interface QuestionService {

    Object askQuestion(MultipartFile questionImage);

    Object viewQuestion(Long id);

    Object addCatalogQuestion(CatalogQuestionsModel model);
}
