package org.example;

import lombok.Getter;

import java.util.*;
import java.util.stream.Collectors;

@Getter
public class Grouper {
    private final Set<Group> groups = new HashSet<>();
    private Set<Line> lineSet;

    public void formGroups(Set<String> stringSet) {
        lineSet = stringSet.stream()
                .map(Line::new)
                .collect(Collectors.toSet());
        formSingleLineGroups();
        while (!lineSet.isEmpty()) {
            formNewGroup();
        }
    }

    private void formSingleLineGroups() {
        Map<String, Integer> repeats = countRepeats();
        Set<Line> linesToBeRemoved = new HashSet<>();
        for (Line line : lineSet) {
            boolean uniqueElementsOnly = true;
            List<String> lineElements = line.getElements();
            for (int i = 0; i < lineElements.size(); i++) {
                String element = lineElements.get(i);
                if (!element.equals("\"\"") && repeats.get(element + "_" + i) != 1) {
                    uniqueElementsOnly = false;
                    break;
                }
            }
            if (uniqueElementsOnly) {
                groups.add(new Group(line));
                linesToBeRemoved.add(line);
            }
        }
        lineSet.removeAll(linesToBeRemoved);
    }

    private void formNewGroup() {
        Group newGroup = new Group(lineSet.iterator().next());
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

            /* Если после полного прохода по всем строкам размер группы
            остался прежним, то формирование группы завершено. */
            if (newGroup.size() == initialGroupSize) {
                continueSearching = false;
            }
        }
    }

    private Map<String, Integer> countRepeats() {
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
    }
}
