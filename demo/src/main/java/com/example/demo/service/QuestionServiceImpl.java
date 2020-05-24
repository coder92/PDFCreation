package com.example.demo.service;

import com.example.demo.model.CatalogQuestionsModel;
import com.example.demo.model.UserAskedQuestionModel;
import com.example.demo.repository.CatalogQuestionsRepository;
import com.example.demo.repository.UserAskedQuestionRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalTime;
import java.util.zip.Deflater;

@Service
@Slf4j
public class QuestionServiceImpl implements  QuestionService{

    static String currentData =null;

    private static boolean isStartTimer = true;

    private static long prevTime;

    @Autowired
    UserAskedQuestionRepository userAskedQuestionRepository;

    @Autowired
    CatalogQuestionsRepository catalogQuestionsRepository;

    public Object recommendQuestions() {

        try {
            //some algo for recommendations
            //rigt now some random value to pick

            return catalogQuestionsRepository.findAll();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Object askQuestion(MultipartFile questionImage) {
        try{
            if(questionImage == null )
                return null;

            byte[] compressedBytes = compressBytes(questionImage.getBytes());

            UserAskedQuestionModel userAskedQuestion = new UserAskedQuestionModel();
            userAskedQuestion.setImageBytes( compressedBytes );
            userAskedQuestionRepository.save( userAskedQuestion );

            //recommendations on the basis of this
            return recommendQuestions();

        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Object viewQuestion(Long id) {
        //give recomondations based on some criteria

        log.info(" viewQuestions() ");
        log.info(" id = "+ id);
        CatalogQuestionsModel catalogQuestionsModel = catalogQuestionsRepository.findById(id).orElse(null);
        if( catalogQuestionsModel == null)
            return null;

        String data = catalogQuestionsModel.getMetaData();
       // pdfCreation(data);

        startTimer(data);

       //File file = new File("JSONData.pdf");
       //log.info(" returning file = "+ file);
       return null;
    }

    @Override
    public Object addCatalogQuestion(CatalogQuestionsModel model) {
        return catalogQuestionsRepository.save(model);
    }


    private void startTimer(String data) {
        log.info("startTimer()");
        log.info(" data = "+ data);
        log.info(" currentTime = "+ LocalTime.now());

        if(isStartTimer){
            isStartTimer = false;
            prevTime=System.currentTimeMillis();
            Thread thread = new Thread(()->{
                try {
                    log.info("Thread : "+ Thread.currentThread().getName() + "  is going to sleep.");
                    //Thread.sleep(300000l);
                    while(true){
                        long currentTime = System.currentTimeMillis();
                        //System.out.println("currentTime = "+currentTime + " | prevTime = "+ prevTime);
                        long diff = currentTime-prevTime;
                        //System.out.println(" diff = "+ diff);
                        if(diff>= 300000)
                            break;
                        //Thread.sleep(10);
                    }
                    log.info(" 5 min wait time is finished ."+"Thread : "+ Thread.currentThread().getName()+" is going to created pdf.");
                    pdfCreation(currentData);
                    isStartTimer = true;
                } catch (Exception e) {
                    e.printStackTrace();
                    log.info("thread intrupted.");
                }
            });

            thread.setName("PdfCreationThread-"+LocalTime.now());

            thread.start();
        }else{
            log.info("updated thread details.");
            prevTime = System.currentTimeMillis();
        }
    }

    private byte[] compressBytes(byte[] bytes) {
        try{
            Deflater deflater = new Deflater();
            deflater.setInput(bytes);
            deflater.finish();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(bytes.length);
            byte[] buffer = new byte[1024];

            while (!deflater.finished()){
                int count = deflater.deflate(buffer);
                byteArrayOutputStream.write(buffer,0,count);
            }
            byteArrayOutputStream.close();
            return byteArrayOutputStream.toByteArray();
        }catch (Exception e){
            e.printStackTrace();
        }
        return bytes;
    }

    public void pdfCreation(String data) {

        try {
            log.info(" pdfCreation() ");
            log.info(" data = "+ data);
            //creating pdf from json object
            PDDocument document = new PDDocument();
            PDPage page = new PDPage();

            document.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(document, page);


            contentStream.beginText();
            contentStream.setFont(PDType1Font.COURIER,12);
            contentStream.newLineAtOffset(0,700);
            contentStream.setLeading(15);

            JSONObject jsonObject = new JSONObject(data);

            contentStream.showText("Suggested Video List:-");
            jsonObject.keySet().forEach(x-> {
                try {
                    contentStream.newLine();
                    contentStream.showText("-"+jsonObject.getString(x));

                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
           // contentStream.showText(data);

            contentStream.endText();
            contentStream.close();

            document.save("JSONData.pdf");
            log.info("pdf doc (JSONData.pdf) has been created.");
            document.close();
            isStartTimer = true;
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
