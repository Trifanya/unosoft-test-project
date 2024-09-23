package org.example;

import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
public class Grouper {
    public static Logger logger = LogManager.getLogger(Grouper.class);

    private final Set<Group> groups = new HashSet<>();
    private Set<Line> lineSet;
    private Map<String, List<Line>> repeats;

    public void formGroups(Set<String> stringSet) {
        lineSet = stringSet.stream()
                .map(Line::new)
                .collect(Collectors.toSet());
        repeats = countRepeats2();
        formSingleLineGroups();
        repeats = repeats.entrySet().stream()
                .filter(entry -> entry.getValue().size() != 1)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        formMultilineGroups();
        /*while (!lineSet.isEmpty()) {
            formNewGroup();
        }*/
    }

    public Map<String, List<Line>> countRepeats2_public() {
        return countRepeats2();
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

    /*private Map<String, Integer> countRepeats() {
        Map<String, Integer> repeats = new HashMap<>();
        for (Line line : lineSet) {
            List<String> elements = line.getElements();
            for (int i = 0; i < elements.size(); i++) {
                String current = elements.get(i);
                if (current.equals("\"\"")) {
                    continue;
                }
                String currentWithIndex = current + "_" + i;
                if (repeats.containsKey(currentWithIndex)) {
                    repeats.put(currentWithIndex, repeats.get(currentWithIndex) + 1);
                } else {
                    repeats.put(currentWithIndex, 1);
                }
            }
        }
        return repeats;
    }*/

    public void formSingleLineGroups_public() {
        formSingleLineGroups();
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

    public void formMultilineGroups_public() {
        formMultilineGroups();
    }

    private void formMultilineGroups() {
        Set<String> keySet = new HashSet<>(repeats.keySet());
        for (String key : keySet) {
            //System.out.println("Элемент: " + key);
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
            //System.out.println("Группа: " + newGroup);
        }
    }

    private List<Line> getLines(Line line) {
        //System.out.println("Line: " + line);
        List<Line> lines = new ArrayList<>();
        List<String> elements = line.getElements();
        for (int i = 0; i < elements.size(); i++) {
            String element = elements.get(i);
            //System.out.println("Element: " + element + "_" + i);
            List<Line> linesWithElement = repeats.get(element + "_" + i);
            //System.out.println("Element lines: " + linesWithElement);
            if (linesWithElement != null && !linesWithElement.isEmpty()) {
                repeats.remove(element + "_" + i);
                linesWithElement.forEach(line1 -> {
                    lines.add(line1);
                    lines.addAll(getLines(line1));
                });
            }
        }
        //System.out.println("Lines: " + lines);
        return lines;
    }

    /*private void formNewGroup() {
        Group newGroup = new Group(lineSet.stream().findFirst().get());
        groups.add(newGroup);
        boolean continueSearching = true;
        while (continueSearching) { // попробовать переписать
            int initialGroupSize = newGroup.size();
            lineSet = lineSet.stream()
                    .filter(line -> {
                        if (newGroup.shouldContainLine(line)) {
                            newGroup.addLine(line);
                            return false;
                        }
                        return true;
                    }).collect(Collectors.toSet());

            *//* Если после полного прохода по всем строкам размер группы
            остался прежним, то формирование группы завершено. *//*
            if (newGroup.size() == initialGroupSize) {
                continueSearching = false;
            }
        }
    }*/
}
