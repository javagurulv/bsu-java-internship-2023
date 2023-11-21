package lv.javaguru.travel.insurance.rest;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class JsonReader {

    public static String read(String fileName) throws IOException {

        StringBuilder result = new StringBuilder();

        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line = reader.readLine();

        while (line != null) {
            result.append(line).append('\n');
            line = reader.readLine();
        }
        reader.close();

        return result.substring(0, result.length() - 1);
    }
}
