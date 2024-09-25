package dev.trifanya;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

public class GroupManagerTest {

    @Test
    public void formGroups_lineSet1_shouldReturnCorrectGroupSet() {
        Set<String[]> allLines = FileProcessor.readAndValidateFile("src/test/resources/test_input_files/test1_incorrect_lines.txt");
        Set<Integer> expectedSizeSet = new HashSet<>(List.of(1, 1, 1));

        Set<Integer> actualSizeSet = GroupManager.formGroups(allLines).stream()
                .map(Group::size)
                .collect(Collectors.toSet());

        Assertions.assertIterableEquals(expectedSizeSet, actualSizeSet);
    }

    @Test
    public void formGroups_lineSet2_shouldReturnCorrectGroupSet() {
        Set<String[]> allLines = FileProcessor.readAndValidateFile("src/test/resources/test_input_files/test2_10lines.txt");
        Set<Integer> expectedSizeSet = new HashSet<>(List.of(8, 2));

        Set<Integer> actualSizeSet = GroupManager.formGroups(allLines).stream()
                .map(Group::size)
                .collect(Collectors.toSet());

        Assertions.assertIterableEquals(expectedSizeSet, actualSizeSet);
    }

    @Test
    public void formGroups_lineSet3_shouldReturnCorrectGroupSet() {
        Set<String[]> allLines = FileProcessor.readAndValidateFile("src/test/resources/test_input_files/test3_20lines.txt");
        Set<Integer> expectedSizeSet = new HashSet<>(List.of(16, 3, 1));

        Set<Integer> actualSizeSet = GroupManager.formGroups(allLines).stream()
                .map(Group::size)
                .collect(Collectors.toSet());

        Assertions.assertIterableEquals(expectedSizeSet, actualSizeSet);
    }

    @Test
    public void formGroups_lineSet4_shouldReturnCorrectGroupSet() {
        Set<String[]> allLines = FileProcessor.readAndValidateFile("src/test/resources/test_input_files/test4_10nonintersecting_lines.txt");
        Set<Integer> expectedSizeSet = new HashSet<>(List.of(1, 1, 1, 1, 1, 1, 1, 1, 1, 1));

        Set<Integer> actualSizeSet = GroupManager.formGroups(allLines).stream()
                .map(Group::size)
                .collect(Collectors.toSet());

        Assertions.assertIterableEquals(expectedSizeSet, actualSizeSet);
    }

    @Test
    public void formGroups_lineSet5_shouldReturnCorrectGroupSet() {
        Set<String[]> allLines = FileProcessor.readAndValidateFile("src/test/resources/test_input_files/test5_20lines_10unique_lines.txt");
        Set<Integer> expectedSizeSet = new HashSet<>(List.of(8, 2));

        Set<Integer> actualSizeSet = GroupManager.formGroups(allLines).stream()
                .map(Group::size)
                .collect(Collectors.toSet());

        Assertions.assertIterableEquals(expectedSizeSet, actualSizeSet);
    }

    @Test
    public void formGroups_lineSet6_shouldReturnCorrectGroupSet() {
        Set<String[]> allLines = FileProcessor.readAndValidateFile("src/test/resources/test_input_files/test6_20lines.txt");
        Set<Integer> expectedSizeSet = new HashSet<>(List.of(16, 3, 1));

        Set<Integer> actualSizeSet = GroupManager.formGroups(allLines).stream()
                .map(Group::size)
                .collect(Collectors.toSet());

        Assertions.assertIterableEquals(expectedSizeSet, actualSizeSet);
    }

    @Test
    public void formGroup_lineSet7_shouldReturnCorrectGroupSet() {
        Set<String[]> allLines = FileProcessor.readAndValidateFile("src/test/resources/test_input_files/test7_20lines.txt");
        Set<Integer> expectedSizeSet = new HashSet<>(List.of(15, 3, 1, 1));

        Set<Integer> actualSizeSet = GroupManager.formGroups(allLines).stream()
                .map(Group::size)
                .collect(Collectors.toSet());

        Assertions.assertIterableEquals(expectedSizeSet, actualSizeSet);
    }
}

