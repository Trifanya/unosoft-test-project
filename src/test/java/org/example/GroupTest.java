package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GroupTest {
    @Test
    public void shouldContainLine_whenLineHasIntersectionWithSingleLineGroup_shouldReturnTrue() {
        // Given
        Line firstLine = new Line("\"123\";\"345\";\"678\"");
        Line secondLine = new Line("\"231\";\"345\";\"963\"");
        Group group = new Group(firstLine);

        // When
        boolean result = group.shouldContainLine(secondLine);

        // Then
        Assertions.assertTrue(result);
    }

    @Test
    public void shouldContainLine_whenLineHasIntersectionWithMultilineGroup_shouldReturnTrue() {
        // Given
        Line firstGroupLine = new Line("\"123\";\"345\";\"698\"");
        Line secondGroupLine = new Line("\"123\";\"373\";\"178\"");
        Line thirdGroupLine = new Line("\"415\";\"345\";\"992\"");
        Line line = new Line("\"221\";\"333\";\"992\"");
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
        Line firstLine = new Line("\"123\";\"345\";\"678\"");
        Line secondLine = new Line("\"231\";\"554\";\"963\"");
        Group group = new Group(firstLine);

        // When
        boolean result = group.shouldContainLine(secondLine);

        // Then
        Assertions.assertFalse(result);
    }

    @Test
    public void shouldContainLine_whenLineHasNotIntersectionWithMultilineGroup_shouldReturnFalse() {
        // Given
        Line firstGroupLine = new Line("\"123\";\"345\";\"698\"");
        Line secondGroupLine = new Line("\"123\";\"373\";\"178\"");
        Line thirdGroupLine = new Line("\"415\";\"345\";\"992\"");
        Group group = new Group(firstGroupLine);
        group.addLine(secondGroupLine);
        group.addLine(thirdGroupLine);
        Line line = new Line("\"221\";\"333\";\"999\"");

        // When
        boolean result = group.shouldContainLine(line);

        // Then
        Assertions.assertFalse(result);
    }
}
