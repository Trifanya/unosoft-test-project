package org.example;

import lombok.Getter;
import lombok.Setter;

import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
public class Grouper {
    private final Set<Group> groups = new HashSet<>();
    private Set<Line> lineSet;
    private Map<String, List<Line>> repeats;

    public void formGroups(Set<String> stringSet) {
        lineSet = stringSet.stream()
                .map(Line::new)
                .collect(Collectors.toSet());

        long startTime0 = System.currentTimeMillis();
        repeats = countRepeats2();
        System.out.println("Время подсчета повторов: " + (System.currentTimeMillis() - startTime0) + " мс");

        long startTime1 = System.currentTimeMillis();
        formSingleLineGroups();
        System.out.println("Время формирования однострочных групп: " + (System.currentTimeMillis() - startTime1) + " мс");

        long startTime2 = System.currentTimeMillis();
        repeats = repeats.entrySet().stream()
                .filter(entry -> entry.getValue().size() != 1)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        System.out.println("Время фильтрации мапы повторов: " + (System.currentTimeMillis() - startTime2) + " мс");

        long startTime3 = System.currentTimeMillis();
        formMultilineGroups();
        System.out.println("Время формирования многострочных групп: " + (System.currentTimeMillis() - startTime3) + " мс");
    }

    private Map<String, List<Line>> countRepeats2() {
        repeats = new HashMap<>();
        for (Line line : lineSet) {
            List<String> elements = line.getElements();
            for (int i = 0; i < elements.size(); i++) {
                String current = elements.get(i);
                if (current.equals("\"\"")) {
                    continue;
                }
                String currentWithIndex = current + "_" + i;
                if (repeats.containsKey(currentWithIndex)) {
                    List<Line> lineList = repeats.get(currentWithIndex);
                    lineList.add(line);
                    repeats.put(currentWithIndex, lineList);
                } else {
                    repeats.put(currentWithIndex, new ArrayList<>(List.of(line)));
                }
            }
        }
        return repeats;
    }

    private void formSingleLineGroups() {
        Set<Line> linesToBeRemoved = new HashSet<>();
        for (Line line : lineSet) {
            boolean uniqueElementsOnly = true;
            List<String> lineElements = line.getElements();
            for (int i = 0; i < lineElements.size(); i++) {
                String element = lineElements.get(i);
                if (!element.equals("\"\"") && repeats.get(element + "_" + i).size() != 1) {
                    uniqueElementsOnly = false;
                    break;
                }
            }
            if (uniqueElementsOnly) {
                groups.add(new Group(line));
                linesToBeRemoved.add(line);
                for (String element : lineElements) {
                    repeats.remove(element);
                }
            }
        }
        lineSet.removeAll(linesToBeRemoved);
    }

    private void formMultilineGroups() {
        Set<String> keySet = new HashSet<>(repeats.keySet());
        for (String key : keySet) {
            List<Line> lineList = null;
            if (repeats.containsKey(key)) {
                lineList = new ArrayList<>(repeats.get(key));
            }
            repeats.remove(key);
            Group newGroup = new Group();
            if (lineList != null && !lineList.isEmpty()) {
                lineList.forEach(line -> {
                    newGroup.addLine(line);
                    lineSet.remove(line);
                    getLines(line).forEach(line1 -> {
                        newGroup.addLine(line1);
                        lineSet.remove(line1);
                    });
                });
                groups.add(newGroup);
            }
        }
    }

    private List<Line> getLines(Line line) {
        List<Line> lines = new ArrayList<>();
        List<String> elements = line.getElements();
        for (int i = 0; i < elements.size(); i++) {
            String element = elements.get(i);
            List<Line> linesWithElement = repeats.get(element + "_" + i);
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
}
