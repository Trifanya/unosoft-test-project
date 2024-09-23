package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class GrouperTest {
    private final Set<Line> testLineSet1 = new HashSet<>(List.of(
            new Line("\"1\";\"\";\"\""),
            new Line("\"4\";\"1\";\"4\";\"2\";\"4\""),
            new Line("\"5\";\"4\";\"4\";\"4\";\"\";\"5\""),
            new Line("\"6\";\"0\";\"3\";\"1\""),
            new Line("\"7\";\"\";\"9\";\"7\";\"1\";\"6\""),
            new Line("\"\";\"7\";\"7\";\"6\";\"1\";\"3\";\"\""),
            new Line("\"2\";\"6\";\"1\";\"0\";\"8\""),
            new Line("\"8\";\"0\";\"4\""),
            new Line("\"6\";\"\";\"\";\"\";\"0\";\"4\""),
            new Line("\"7\";\"3\";\"\";\"5\";\"9\""),
            new Line("\"9\";\"2\";\"9\";\"2\""),
            new Line("\"1\";\"4\";\"\";\"\";\"5\";\"2\""),
            new Line("\"1\";\"0\";\"3\";\"7\""),
            new Line("\"3\";\"4\";\"2\""),
            new Line("\"6\";\"8\";\"6\""),
            new Line("\"2\";\"6\";\"1\""),
            new Line("\"0\";\"\";\"5\";\"3\";\"2\""),
            new Line("\"2\";\"6\";\"8\";\"\";\"7\";\"9\""),
            new Line("\"7\";\"3\""),
            new Line("\"9\";\"1\";\"2\"")
    ));

    @Test
    public void formGroups() {

    }

    @Test
    public void countRepeats_shouldReturnCorrectMap() {
        Grouper grouper = new Grouper();
        grouper.setLineSet(new HashSet<>(List.of(
                new Line("\"123\";\"675\";\"199\""),
                new Line("\"444\";\"776\";\"999\""),
                new Line("\"333\";\"675\";\"756\""),
                new Line("\"123\";\"621\";\"199\""),
                new Line("\"123\";\"653\";\"109\"")
        )));
        Map<String, List<Line>> expectedResult = new HashMap<>();
        expectedResult.put("\"123\"_0", List.of(new Line("\"123\";\"675\";\"199\""), new Line("\"123\";\"621\";\"199\""), new Line("\"123\";\"653\";\"109\"")));
        expectedResult.put("\"444\"_0", List.of(new Line("\"444\";\"776\";\"999\"")));
        expectedResult.put("\"333\"_0", List.of(new Line("\"333\";\"675\";\"756\"")));
        expectedResult.put("\"675\"_1", List.of(new Line("\"123\";\"675\";\"199\""), new Line("\"333\";\"675\";\"756\"")));
        expectedResult.put("\"776\"_1", List.of(new Line("\"444\";\"776\";\"999\"")));
        expectedResult.put("\"621\"_1", List.of(new Line("\"123\";\"621\";\"199\"")));
        expectedResult.put("\"653\"_1", List.of(new Line("\"123\";\"653\";\"109\"")));
        expectedResult.put("\"199\"_2", List.of(new Line("\"123\";\"675\";\"199\""), new Line("\"123\";\"621\";\"199\"")));
        expectedResult.put("\"999\"_2", List.of(new Line("\"444\";\"776\";\"999\"")));
        expectedResult.put("\"756\"_2", List.of(new Line("\"333\";\"675\";\"756\"")));
        expectedResult.put("\"109\"_2", List.of(new Line("\"123\";\"653\";\"109\"")));

        Map<String, List<Line>> result = grouper.countRepeats2_public();

        Assertions.assertEquals(expectedResult.size(), result.size());
        Assertions.assertTrue(expectedResult.entrySet().stream()
                .allMatch(entry -> result.get(entry.getKey()) != null));
    }

    @Test
    public void formSingleLineGroups_shouldModifyGroupSetAndLineSet() {
        // Given
        Grouper grouper = new Grouper();
        grouper.setLineSet(new HashSet<>(List.of(
                new Line("\"123\";\"675\";\"199\""), new Line("\"444\";\"776\";\"999\""),
                new Line("\"333\";\"909\";\"756\""), new Line("\"266\";\"621\";\"108\""),
                new Line("\"123\";\"653\";\"109\"")
        )));
        Set<Group> expectedGroupSet = new HashSet<>(List.of(
                new Group(new Line("\"444\";\"776\";\"999\"")),
                new Group(new Line("\"333\";\"909\";\"756\"")),
                new Group(new Line("\"266\";\"621\";\"108\""))
        ));
        Set<Line> expectedLineSet = new HashSet<>(List.of(
                new Line("\"123\";\"675\";\"199\""), new Line("\"123\";\"653\";\"109\"")
        ));

        // When
        grouper.countRepeats2_public();
        grouper.formSingleLineGroups_public();
        Set<Group> resultGroupSet = grouper.getGroups();
        Set<Line> resultLineSet = grouper.getLineSet();

        // Then
        Assertions.assertIterableEquals(expectedGroupSet, resultGroupSet);
        Assertions.assertIterableEquals(expectedLineSet, resultLineSet);

    }

    @Test
    public void formSingleLineGroups_onlyMultilineGroups_shouldNotModifyLineSetAndGroupSet() {
        // Given
        Grouper grouper = new Grouper();
        Set<Line> initialLineSet = new HashSet<>(List.of(
                new Line("\"123\";\"675\";\"199\""),
                new Line("\"444\";\"909\";\"999\""),
                new Line("\"333\";\"909\";\"756\""),
                new Line("\"266\";\"621\";\"756\""),
                new Line("\"123\";\"653\";\"109\"")
        ));
        grouper.setLineSet(initialLineSet);

        // When
        grouper.countRepeats2_public();
        grouper.formSingleLineGroups_public();
        Set<Line> resultLineSet = grouper.getLineSet();
        Set<Group> resultGroupSet = grouper.getGroups();

        // Then
        Assertions.assertIterableEquals(initialLineSet, resultLineSet);
        Assertions.assertTrue(resultGroupSet.isEmpty());
    }

    @Test
    public void formMultilineGroups_shouldModifyGroupSetAndEmptyLineSet() {
        // Given
        Grouper grouper = new Grouper();
        Line line1 = new Line("\"123\";\"675\";\"199\"");
        Line line2 = new Line("\"444\";\"909\";\"999\"");
        Line line3 = new Line("\"333\";\"909\";\"756\"");
        Line line4 = new Line("\"266\";\"621\";\"756\"");
        Line line5 = new Line("\"123\";\"653\";\"109\"");
        Set<Line> initialLineSet = new HashSet<>(List.of(line1, line2, line3, line4, line5));
        grouper.setLineSet(initialLineSet);

        Group group1 = new Group(line1);
        group1.addLine(line5);
        Group group2 = new Group(line2);
        group2.addLine(line3);
        group2.addLine(line4);
        Set<Group> expectedGroupSet = new HashSet<>(List.of(group1, group2));

        Map<String, List<Line>> repeats = new HashMap<>();
        repeats.put("\"123\"_0", List.of(line1, line5));
        repeats.put("\"909\"_1", List.of(line2, line3));
        repeats.put("\"756\"_2", List.of(line3, line4));

        // When
        grouper.countRepeats2_public();
        grouper.setRepeats(repeats);
        grouper.formMultilineGroups_public();
        Set<Group> resultGroupSet = grouper.getGroups();
        Set<Line> resultLineSet = grouper.getLineSet();

        // Then
        Assertions.assertEquals(expectedGroupSet.size(), resultGroupSet.size());
        Assertions.assertIterableEquals(expectedGroupSet, resultGroupSet);
        Assertions.assertTrue(resultLineSet.isEmpty());
    }
}
