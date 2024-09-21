package org.example;

import lombok.Getter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class Group {
    private final Set<Line> lines;

    public Group(Line firstLine) {
        lines = new HashSet<>(List.of(firstLine));
    }

    public boolean shouldContainLine(Line line) {
        for (Line groupLine : lines) {
            if (groupLine.hasIntersection(line)) {
                return true;
            }
        }
        return false;
    }

    public void addLine(Line line) {
        lines.add(line);
    }

    public int size() {
        return lines.size();
    }

    @Override
    public String toString() {
        return "Группа " + "\n" +
                lines.stream()
                .map(l -> l.toString())
                .collect(Collectors.joining("\n"))
                + "\n";
    }
}
