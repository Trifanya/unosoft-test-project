package org.example;

import lombok.Getter;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Getter
public class Group {
    private final Set<Line> lines = new HashSet<>();

    public Group() {}

    public Group(Line firstLine) {
        lines.add(firstLine);
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
        return "Group{" +
                "lines=" + lines +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Group group)) return false;
        return Objects.equals(lines, group.lines);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lines);
    }
}
