package dev.trifanya;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Set;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;

public class FileProcessor {
    public static Set<String[]> readAndValidateFile(String fileName) {
        try {
            return Files.readAllLines(Path.of(fileName)).stream()
                    .distinct()
                    .map(s -> s.split(";"))
                    .filter(FileProcessor::lineIsValid)
                    .map(FileProcessor::removeQuotes)
                    .collect(Collectors.toSet());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean lineIsValid(String[] line) {
        for (String s : line) {
            if (!s.startsWith("\"") || !s.endsWith("\"")) {
                return false;
            }
            String value = s.substring(1, s.length() - 1);
            if (StringUtils.isNotBlank(value) && value.contains("\"")) {
                return false;
            }
        }
        return true;
    }

    private static String[] removeQuotes(String[] line) {
        return Arrays.stream(line)
                .map(str -> str.substring(1, str.length() - 1))
                .toArray(String[]::new);
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
