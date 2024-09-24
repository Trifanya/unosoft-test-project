package dev.trifanya;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileProcessor {
    public static List<String> readAndValidateFile(String fileName) {
        List<String> strings = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (lineIsValid(line)) {
                    strings.add(line);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return strings;
    }

    private static boolean lineIsValid(String line) {
        int quoteCount = line.length() - line.replace("\"", "").length();
        int semicolonCount = line.length() - line.replace(";", "").length();
        if (semicolonCount * 2 + 2 == quoteCount) {
            return true;
        }
        return false;
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
