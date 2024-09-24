package dev.trifanya;

import lombok.Getter;
import lombok.Setter;

import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
public class GroupManager {
    private final Set<Group> groups = new HashSet<>();
    private Set<String[]> allLines = new HashSet<>();
    private Map<String, List<String[]>> repeats;

    public void formGroups(Set<String> stringSet) {
        allLines = stringSet.stream()
                .map(str -> str.split(";"))
                .collect(Collectors.toSet());
        repeats = countRepeats();
        formSinglelineGroups();
        repeats = repeats.entrySet().stream()
                .filter(entry -> entry.getValue().size() != 1)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        formMultilineGroups();
    }

    private Map<String, List<String[]>> countRepeats() {
        repeats = new HashMap<>();
        for (String[] line : allLines) {
            for (int i = 0; i < line.length; i++) {
                if (!line[i].equals("\"\"")) {
                    String elementWithIndex = line[i] + "_" + i;
                    if (repeats.containsKey(elementWithIndex)) {
                        List<String[]> linesWithElement = repeats.get(elementWithIndex);
                        linesWithElement.add(line);
                        repeats.put(elementWithIndex, linesWithElement);
                    } else {
                        repeats.put(elementWithIndex, new ArrayList<>(Collections.singletonList(line)));
                    }
                }
            }
        }
        return repeats;
    }

    private void formSinglelineGroups() {
        for (String[] line : allLines) {
            boolean uniqueElementsOnly = true;
            for (int i = 0; i < line.length; i++) {
                if (!line[i].equals("\"\"") && repeats.get(line[i] + "_" + i).size() != 1) {
                    uniqueElementsOnly = false;
                    break;
                }
            }
            if (uniqueElementsOnly) {
                groups.add(new Group(line));
                for (String element : line) {
                    repeats.remove(element);
                }
            }
        }
    }

    private void formMultilineGroups() {
        Set<String> keySet = new HashSet<>(repeats.keySet());
        for (String key : keySet) {
            List<String[]> lineList = null;
            if (repeats.containsKey(key)) {
                lineList = new ArrayList<>(repeats.get(key));
            }
            repeats.remove(key);
            Group newGroup = new Group();
            if (lineList != null && !lineList.isEmpty()) {
                lineList.forEach(line -> {
                    newGroup.addLine(line);
                    getLines(line).forEach(line1 -> {
                        newGroup.addLine(line1);
                    });
                });
                groups.add(newGroup);
            }
        }
    }

    private List<String[]> getLines(String[] line) {
        List<String[]> lines = new ArrayList<>();
        for (int i = 0; i < line.length; i++) {
            String element = line[i];
            List<String[]> linesWithElement = repeats.get(element + "_" + i);
            if (linesWithElement != null && !linesWithElement.isEmpty()) {
                repeats.remove(element + "_" + i);
                linesWithElement.forEach(line1 -> {
                    lines.add(line1);
                    lines.addAll(getLines(line1));
                });
            }
        }
        return lines;
    }

    /* Для тестов.
    public Map<String, List<String[]>> countRepeats_public() {
        return countRepeats();
    }

    public void formSinglelineGroups_public() {
        formSinglelineGroups();
    }

    public void formMultilineGroups_public() {
        formMultilineGroups();
    }*/
}
