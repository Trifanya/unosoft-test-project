package dev.trifanya;

import java.util.*;
import java.util.stream.Collectors;

public class Task {
    public static void solve(String inputFile, String outputFile) {
        Set<String[]> uniqueFileLines = FileProcessor.readAndValidateFile(inputFile);
        Set<Group> groups = GroupManager.formGroups(uniqueFileLines);
        List<String> result = generateResult(groups);
        FileProcessor.writeToFile(outputFile, result);
    }

    private static List<String> generateResult(Set<Group> groups) {
        List<String> result = new ArrayList<>();
        int multilineGroupCount = groups.stream()
                .filter(group -> group.size() > 1)
                .collect(Collectors.toSet())
                .size();
        result.add("Количество групп с более, чем одним элементом: " + multilineGroupCount + "\n\n");
        List<Group> groupList = new ArrayList<>(groups);
        groupList.sort(Comparator.comparingInt(Group::size).reversed());
        for (Group group : groupList) {
            result.add("Группа " + result.size() + "\n" + group + "\n\n");
        }
        return result;
    }
}