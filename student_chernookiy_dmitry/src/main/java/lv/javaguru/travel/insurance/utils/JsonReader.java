package lv.javaguru.travel.insurance.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;

public class JsonReader {

    public static String readJson(String filePath) {
        File file = new File(filePath);
        StringBuilder resultJson = new StringBuilder();
        if (file.getName().endsWith(".json")) {
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    resultJson.append(line);
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return resultJson.toString();

    }

    public static boolean compareJsonString(String json1, String json2) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode tree1 = mapper.readTree(json1);
        JsonNode tree2 = mapper.readTree(json2);

        return tree1.equals(tree2);
    }
}


