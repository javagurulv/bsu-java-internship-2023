package lv.javaguru.travel.insurance.rest;

import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Component
public class readDataFromJsonFile {
    public String parseData(String pathToFile){
        try{
            File jsonFile = ResourceUtils.getFile("classpath:" + pathToFile);
            return new String(Files.readAllBytes(jsonFile.toPath()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
