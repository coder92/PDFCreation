package com.example.demo;

import com.example.demo.model.CatalogQuestionsModel;
import com.example.demo.repository.CatalogQuestionsRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

		DemoApplication app = new DemoApplication();
		app.initDB();
	}

	private void initDB() {
		CatalogQuestionsModel model = new CatalogQuestionsModel();
		model.setQuestion("q1");
		model.setVideoURL("src1");
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("q1", "src1");
		jsonObject.put("q2","src2");
		jsonObject.put("q3", "src3");
		jsonObject.put("q4","src4");

		model.setMetaData(jsonObject.toString());

		//catalogQuestionsRepository.save( model );

		CatalogQuestionsModel model2 = new CatalogQuestionsModel(0L,"q2","src2",jsonObject.toString());
		//catalogQuestionsRepository.save(model2);

		CatalogQuestionsModel model3 = new CatalogQuestionsModel(0L,"q3","src3",jsonObject.toString());
		//catalogQuestionsRepository.save(model3);

		CatalogQuestionsModel model4 = new CatalogQuestionsModel(0L,"q4","src4",jsonObject.toString());
		//catalogQuestionsRepository.save(model4);
	}
}
