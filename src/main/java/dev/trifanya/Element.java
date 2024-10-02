package dev.trifanya;

import lombok.Getter;

import java.util.Objects;

@Getter
public class Element {
    private final String value;
    private final int index;

    public Element(String value, int index) {
        this.value = value;
        this.index = index;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Element element)) return false;
        return index == element.index && Objects.equals(value, element.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, index);
    }
}
