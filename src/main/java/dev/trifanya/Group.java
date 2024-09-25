package dev.trifanya;

import lombok.Getter;

import java.util.*;
import java.util.stream.Collectors;

@Getter
public class Group {
    private final Set<String[]> lines = new HashSet<>();

    public static Group of(String[] firstLine) {
        Group group = new Group();
        group.lines.add(firstLine);
        return group;
    }

    public boolean shouldContainLine(String[] line) {
        return lines.stream()
                .anyMatch(l -> linesHasIntersection(l, line));
    }

    private boolean linesHasIntersection(String[] firstLine, String[] secondLine) {
        int minSize = Math.min(firstLine.length, secondLine.length);
        for (int i = 0; i < minSize; i++) {
            if (!firstLine[i].equals("\"\"") && firstLine[i].equals(secondLine[i])) {
                return true;
            }
        }
        return false;
    }

    public void addLine(String[] line) {
        lines.add(line);
    }

    public int size() {
        return lines.size();
    }

    @Override
    public String toString() {
        return lines.stream()
                .map(arr -> String.join(";", arr))
                .collect(Collectors.joining("\n"));
    }
}
