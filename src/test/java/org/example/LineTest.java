package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LineTest {

    @Test
    public void hasIntersection_whenLinesHaveEqualElementsWithTheSameIndex_shouldReturnTrue() {
        // Given
        Line firstLine = new Line("\"123\";\"345\";\"678\"");
        Line secondLine = new Line("\"231\";\"345\";\"963\"");

        // When
        boolean result = firstLine.hasIntersection(secondLine);

        //Then
        Assertions.assertTrue(result);
    }

    @Test
    public void hasIntersection_whenLinesHaveEqualElementsWithDifferentIndices_shouldReturnFalse() {
        // Given
        Line firstLine = new Line("\"123\";\"345\";\"678\"");
        Line secondLine = new Line("\"231\";\"123\";\"963\"");

        // When
        boolean result = firstLine.hasIntersection(secondLine);

        //Then
        Assertions.assertFalse(result);
    }

    @Test
    public void hasIntersection_whenLinesHaveEmptyStringsWithSameIndex_shouldReturnFalse() {
        // Given
        Line firstLine = new Line("\"123\";\"\";\"678\"");
        Line secondLine = new Line("\"231\";\"\";\"963\"");

        // When
        boolean result = firstLine.hasIntersection(secondLine);

        //Then
        Assertions.assertFalse(result);
    }

    @Test
    public void hasIntersection_whenLinesDontHaveEqualElements_shouldReturnFalse() {
        // Given
        Line firstLine = new Line("\"123\";\"345\";\"678\"");
        Line secondLine = new Line("\"231\";\"554\";\"963\"");

        // When
        boolean result = firstLine.hasIntersection(secondLine);

        //Then
        Assertions.assertFalse(result);
    }
}
