package dev.trifanya;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GroupTest {
    @Test
    public void shouldContainLine_whenLineHasIntersectionWithSingleLineGroup_shouldReturnTrue() {
        // Given
        String[] firstLine = new String[]{"\"123\"", "\"345\"", "\"678\""};
        String[] secondLine = new String[]{"\"231\"", "\"345\"", "\"963\""};
        Group group = new Group(firstLine);

        // When
        boolean result = group.shouldContainLine(secondLine);

        // Then
        Assertions.assertTrue(result);
    }

    @Test
    public void shouldContainLine_whenLineHasIntersectionWithMultilineGroup_shouldReturnTrue() {
        // Given
        String[] firstGroupLine = new String[]{"\"123\"", "\"345\"", "\"698\""};
        String[] secondGroupLine = new String[]{"\"123\"", "\"373\"", "\"178\""};
        String[] thirdGroupLine = new String[]{"\"415\"", "\"345\"", "\"992\""};
        String[] line = new String[]{"\"221\"", "\"333\"", "\"992\""};
        Group group = new Group(firstGroupLine);
        group.addLine(secondGroupLine);
        group.addLine(thirdGroupLine);

        // When
        boolean result = group.shouldContainLine(line);

        // Then
        Assertions.assertTrue(result);
    }

    @Test
    public void shouldContainLine_whenLineHasNotIntersectionWithSingleLineGroup_shouldReturnFalse() {
        // Given
        String[] firstLine = new String[]{"\"123\"", "\"345\"", "\"678\""};
        String[] secondLine = new String[]{"\"231\"", "\"554\"", "\"963\""};
        Group group = new Group(firstLine);

        // When
        boolean result = group.shouldContainLine(secondLine);

        // Then
        Assertions.assertFalse(result);
    }

    @Test
    public void shouldContainLine_whenLineHasNotIntersectionWithMultilineGroup_shouldReturnFalse() {
        // Given
        String[] firstGroupLine = new String[]{"\"123\"", "\"345\"", "\"698\""};
        String[] secondGroupLine = new String[]{"\"123\"", "\"373\"", "\"178\""};
        String[] thirdGroupLine = new String[]{"\"415\"", "\"345\"", "\"992\""};
        Group group = new Group(firstGroupLine);
        group.addLine(secondGroupLine);
        group.addLine(thirdGroupLine);
        String[] line = new String[]{"\"221\"", "\"333\"", "\"999\""};

        // When
        boolean result = group.shouldContainLine(line);

        // Then
        Assertions.assertFalse(result);
    }
}
