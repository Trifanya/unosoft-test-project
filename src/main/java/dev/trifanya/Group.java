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
