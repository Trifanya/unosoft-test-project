package org.example;

import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.stream.Collectors;

@Getter
public class UnoSoftTask {
    public static final Logger logger = LogManager.getLogger(UnoSoftTask.class);

    private int groupCount;
    private int singleLineGroupCount;
    private int maxLinesCount;

    public void solve(String inputFile, String outputFile) {
        logger.info("Программа начала работу.");
        long startTimeMillis = System.currentTimeMillis();

        long startTime0 = System.currentTimeMillis();
        Set<String> uniqueFileLines = new HashSet<>(FileProcessor.readFile(inputFile));
        System.out.println("Время чтения из файла: " + (System.currentTimeMillis() - startTime0) + " мс");

        uniqueFileLines = validateLines(uniqueFileLines);

        Grouper grouper = new Grouper();
        grouper.formGroups(uniqueFileLines);

        computeAnswers(grouper.getGroups());

        List<String> result = generateResult(grouper.getGroups());

        long startTime1 = System.currentTimeMillis();
        FileProcessor.writeToFile(outputFile, result);
        System.out.println("Время записи в файл: " + (System.currentTimeMillis() - startTime1) + " мс");

        logger.info("Программа завершила работу.\n" +
                "Время работы программы: " + (System.currentTimeMillis() - startTimeMillis) / 1000 + " секунд.");
    }

    private Set<String> validateLines(Set<String> lines) {
        return lines.stream()
                .filter(line -> {
                    int quoteCount = line.length() - line.replace("\"", "").length();
                    int semicolonCount = line.length() - line.replace(";", "").length();
                    return semicolonCount * 2 + 2 == quoteCount;
                }).collect(Collectors.toSet());
    }

    private List<String> generateResult(Set<Group> groups) {
        List<String> result = new ArrayList<>();
        result.add("Количество групп с более, чем одним элементом: " + (groupCount - singleLineGroupCount) + "\n\n");

        List<Group> groupList = new ArrayList<>(groups);
        groupList.sort(Comparator.comparingInt(Group::size).reversed());

        for (Group group : groupList) {
            result.add("Группа " + result.size() + "\n" +
                    group.getLines()
                            .stream()
                            .map(Line::toString)
                            .collect(Collectors.joining("\n"))
                    + "\n\n");
        }
        return result;
    }

    private void computeAnswers(Set<Group> groups) {
        groupCount = groups.size();
        singleLineGroupCount = groups.stream()
                .filter(group -> group.size() == 1)
                .collect(Collectors.toSet())
                .size();
        maxLinesCount = groups.stream()
                .max(Comparator.comparingInt(Group::size))
                .orElseThrow(RuntimeException::new).size();
    }

    public static void main(String[] args) {
        String inputFile = "D:\\Программирование на Java\\Тестовые задания\\UnoSoftTestProject\\src\\main\\resources\\main_test_file.txt";
        String outputFile = "D:\\Программирование на Java\\Тестовые задания\\UnoSoftTestProject\\src\\main\\resources\\main_output_file.txt";
        UnoSoftTask unoSoftTask = new UnoSoftTask();
        unoSoftTask.solve(inputFile, outputFile);
    }
}