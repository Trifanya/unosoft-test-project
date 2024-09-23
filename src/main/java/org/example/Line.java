package org.example;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;


@Getter
public class Line {
    private final List<String> elements;

    public Line(String line) {
        elements = Arrays.asList(line.split(";"));
    }

    public boolean hasIntersection(Line other) {
        int minSize = Math.min(this.size(), other.size());
        for (int i = 0; i < minSize; i++) {
            if (!elements.get(i).equals("\"\"") && elements.get(i).equals(other.elements.get(i))) {
                return true;
            }
        }
        return false;
    }

    public int size() {
        return elements.size();
    }

    @Override
    public String toString() {
        return String.join(";", elements);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Line line)) return false;
        return toString().equals(line.toString());
    }

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }
}
