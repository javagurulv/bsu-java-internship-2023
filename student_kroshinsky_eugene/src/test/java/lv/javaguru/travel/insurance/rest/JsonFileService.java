package lv.javaguru.travel.insurance.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
@Component
public class JsonFileService {
    @Autowired private ResourceLoader loader;
    public  String readJsonFile(String filePath) throws IOException {
        Resource resource = loader.getResource(filePath);
        return new String(Files.readAllBytes(resource.getFile().toPath()));
    }
}
