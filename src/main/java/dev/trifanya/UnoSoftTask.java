package dev.trifanya;

import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.stream.Collectors;

@Getter
public class UnoSoftTask {
    public static final Logger logger = LogManager.getLogger(UnoSoftTask.class);

    private int groupCount;
    private int multilineGroupCount;

    public void solve(String inputFile, String outputFile) {
        logger.info("Program started.");
        long startTimeMillis = System.currentTimeMillis();

        Set<String> uniqueFileLines = new HashSet<>(FileProcessor.readAndValidateFile(inputFile));
        GroupManager groupManager = new GroupManager();
        groupManager.formGroups(uniqueFileLines);
        List<String> result = generateResult(groupManager.getGroups());
        FileProcessor.writeToFile(outputFile, result);

        logger.info("Program ended.\n" +
                "Program running time: " + (System.currentTimeMillis() - startTimeMillis) / 1000d + " seconds.");
    }

    private List<String> generateResult(Set<Group> groups) {
        List<String> result = new ArrayList<>();
        groupCount = groups.size();
        multilineGroupCount = groups.stream()
                .filter(group -> group.size() > 1)
                .collect(Collectors.toSet())
                .size();
        result.add("Количество групп с более, чем одним элементом: " + multilineGroupCount + "\n\n");
        List<Group> groupList = new ArrayList<>(groups);
        groupList.sort(Comparator.comparingInt(Group::size).reversed());
        for (Group group : groupList) {
            result.add("Группа " + result.size() + "\n" +
                    group.getLines().stream()
                            .map(arr -> String.join(";", arr))
                            .collect(Collectors.joining("\n"))
                    + "\n\n");
        }
        return result;
    }

    public static void main(String[] args) {
        String outputFile = "src/main/resources/result.txt";
        UnoSoftTask unoSoftTask = new UnoSoftTask();
        unoSoftTask.solve(args[0], outputFile);
        //unoSoftTask.solve("src/main/resources/input.txt", outputFile);
    }
}