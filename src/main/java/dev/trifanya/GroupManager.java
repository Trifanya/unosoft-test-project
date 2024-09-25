package dev.trifanya;

import org.apache.commons.collections4.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

public class GroupManager {

    public static Set<Group> formGroups(Set<String[]> allLines) {
        Map<String, List<String[]>> repeats = countRepeats(allLines);
        Set<Group> groups = new HashSet<>(formSinglelineGroups(allLines, repeats));
        repeats = repeats.entrySet().stream()
                .filter(entry -> entry.getValue().size() != 1)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        groups.addAll(formMultilineGroups(repeats));
        return groups;
    }

    private static Map<String, List<String[]>> countRepeats(Set<String[]> allLines) {
        Map<String, List<String[]>> elementRepeats = new HashMap<>();
        for (String[] line : allLines) {
            for (int i = 0; i < line.length; i++) {
                if (!line[i].equals("\"\"")) {
                    String elementWithIndex = line[i] + "_" + i;
                    if (elementRepeats.containsKey(elementWithIndex)) {
                        elementRepeats.get(elementWithIndex).add(line);
                    } else {
                        elementRepeats.put(elementWithIndex, new ArrayList<>(Collections.singletonList(line)));
                    }
                }
            }
        }
        return elementRepeats;
    }

    private static Set<Group> formSinglelineGroups(Set<String[]> allLines, Map<String, List<String[]>> elementRepeats) {
        Set<Group> singlelineGroups = new HashSet<>();
        for (String[] line : allLines) {
            boolean uniqueElementsOnly = true;
            for (int i = 0; i < line.length; i++) {
                if (!line[i].equals("\"\"") && elementRepeats.get(line[i] + "_" + i).size() != 1) {
                    uniqueElementsOnly = false;
                    break;
                }
            }
            if (uniqueElementsOnly) {
                singlelineGroups.add(Group.of(line));
                for (String element : line) {
                    elementRepeats.remove(element);
                }
            }
        }
        return singlelineGroups;
    }

    private static Set<Group> formMultilineGroups(Map<String, List<String[]>> elementRepeats) {
        Set<Group> multilineGroups = new HashSet<>();
        Set<String> keySet = new HashSet<>(elementRepeats.keySet());
        for (String key : keySet) {
            List<String[]> lineList = null;
            if (elementRepeats.containsKey(key)) {
                lineList = new ArrayList<>(elementRepeats.get(key));
            }
            elementRepeats.remove(key);
            Group newGroup = new Group();
            if (lineList != null && !lineList.isEmpty()) {
                lineList.forEach(line -> {
                    newGroup.addLine(line);
                    getLines(line, elementRepeats).forEach(newGroup::addLine);
                });
                multilineGroups.add(newGroup);
            }
        }
        return multilineGroups;
    }

    private static List<String[]> getLines(String[] line, Map<String, List<String[]>> elementRepeats) {
        List<String[]> lines = new ArrayList<>();
        for (int i = 0; i < line.length; i++) {
            String element = line[i];
            List<String[]> linesWithElement = elementRepeats.get(element + "_" + i);
            if (CollectionUtils.isNotEmpty(linesWithElement)) {
                elementRepeats.remove(element + "_" + i);
                linesWithElement.forEach(line1 -> {
                    lines.add(line1);
                    lines.addAll(getLines(line1, elementRepeats));
                });
            }
        }
        return lines;
    }
}
