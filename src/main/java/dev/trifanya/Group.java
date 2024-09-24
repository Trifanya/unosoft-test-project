package dev.trifanya;

import lombok.Getter;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Getter
public class Group {
    private final Set<String[]> lines = new HashSet<>();

    public Group() {}

    public Group(String[] firstLine) {
        lines.add(firstLine);
    }

    public boolean shouldContainLine(String[] line) {
        for (String[] groupLine : lines) {
            if (hasIntersection(groupLine, line)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasIntersection(String[] firstLine, String[] secondLine) {
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

    public void addAllLines(List<String[]> lines) {
        this.lines.addAll(lines);
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
