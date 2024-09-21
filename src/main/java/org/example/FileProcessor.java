package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileProcessor {
    public static List<String> readFile(String fileName) {
        List<String> strings = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                strings.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return strings;
    }

    public static void writeToFile(String fileName, List<String> linesToWrite) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (String line : linesToWrite) {
                writer.write(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
