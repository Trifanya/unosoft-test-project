package dev.trifanya;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class GroupManagerTest {

    /*@Test
    public void countRepeats_shouldReturnCorrectMap() {
        GroupManager groupManager = new GroupManager();
        groupManager.setAllLines(new HashSet<>(List.of(
                new String[]{"\"123\"","\"675\"","\"199\""},
                new String[]{"\"444\"","\"776\"","\"999\""},
                new String[]{"\"333\"","\"675\"","\"756\""},
                new String[]{"\"123\"","\"621\"","\"199\""},
                new String[]{"\"123\"","\"653\"","\"109\""}
        )));
        Map<String, List<String[]>> expectedResult = new HashMap<>();
        expectedResult.put("\"123\"_0", List.of(new String[]{"\"123\"","\"675\"","\"199\""}, new String[]{"\"123\"","\"621\"","\"199\""}, new String[]{"\"123\"","\"653\"","\"109\""}));
        expectedResult.put("\"444\"_0", new ArrayList<>(Collections.singletonList(new String[]{"\"444\"","\"776\"","\"999\""})));
        expectedResult.put("\"333\"_0", new ArrayList<>(Collections.singletonList(new String[]{"\"333\"","\"675\"","\"756\""})));
        expectedResult.put("\"675\"_1", List.of(new String[]{"\"123\"","\"675\"","\"199\""}, new String[]{"\"333\"","\"675\"","\"756\""}));
        expectedResult.put("\"776\"_1", new ArrayList<>(Collections.singletonList(new String[]{"\"444\"","\"776\"","\"999\""})));
        expectedResult.put("\"621\"_1", new ArrayList<>(Collections.singletonList(new String[]{"\"123\"","\"621\"","\"199\""})));
        expectedResult.put("\"653\"_1", new ArrayList<>(Collections.singletonList(new String[]{"\"123\"","\"653\"","\"109\""})));
        expectedResult.put("\"199\"_2", List.of(new String[]{"\"123\"","\"675\"","\"199\""}, new String[]{"\"123\"","\"621\"","\"199\""}));
        expectedResult.put("\"999\"_2", new ArrayList<>(Collections.singletonList(new String[]{"\"444\"","\"776\"","\"999\""})));
        expectedResult.put("\"756\"_2", new ArrayList<>(Collections.singletonList(new String[]{"\"333\"","\"675\"","\"756\""})));
        expectedResult.put("\"109\"_2", new ArrayList<>(Collections.singletonList(new String[]{"\"123\"","\"653\"","\"109\""})));

        Map<String, List<String[]>> result = groupManager.countRepeats_public();

        Assertions.assertEquals(expectedResult.size(), result.size());
        Assertions.assertTrue(expectedResult.entrySet().stream()
                .allMatch(entry -> result.get(entry.getKey()) != null));
    }*/

    /*@Test
    public void formSinglelineGroups_shouldModifyGroupSet() {
        // Given
        GroupManager groupManager = new GroupManager();
        String[] arr1 = new String[]{"\"123\"", "\"675\"", "\"199\""};
        String[] arr2 = new String[]{"\"444\"", "\"776\"","\"999\""};
        String[] arr3 = new String[]{"\"333\"", "\"909\"", "\"756\""};
        String[] arr4 = new String[]{"\"266\"", "\"621\"", "\"108\""};
        String[] arr5 = new String[]{"\"123\"", "\"653\"", "\"109\""};
        groupManager.setAllLines(new HashSet<>(List.of(arr1, arr2, arr3, arr4, arr5)));
        Set<Group> expectedGroupSet = new HashSet<>(List.of(
                new Group(arr2),
                new Group(arr3),
                new Group(arr4)
        ));

        // When
        groupManager.countRepeats_public();
        groupManager.formSinglelineGroups_public();
        Set<Group> resultGroupSet = groupManager.getGroups();

        // Then
        Assertions.assertIterableEquals(expectedGroupSet, resultGroupSet);
    }*/

    /*@Test
    public void formSinglelineGroups_onlyMultilineGroups_shouldNotModifyGroupSet() {
        // Given
        GroupManager groupManager = new GroupManager();
        Set<String[]> initialLineSet = new HashSet<>(List.of(
                new String[]{"\"123\"","\"675\"","\"199\""},
                new String[]{"\"444\"","\"909\"","\"999\""},
                new String[]{"\"333\"","\"909\"","\"756\""},
                new String[]{"\"266\"","\"621\"","\"756\""},
                new String[]{"\"123\"","\"653\"","\"109\""}
        ));
        groupManager.setAllLines(initialLineSet);

        // When
        groupManager.countRepeats_public();
        groupManager.formSinglelineGroups_public();
        Set<Group> resultGroupSet = groupManager.getGroups();

        // Then
        Assertions.assertTrue(resultGroupSet.isEmpty());
    }*/

    /*@Test
    public void formMultilineGroups_shouldModifyGroupSet() {
        // Given
        GroupManager groupManager = new GroupManager();
        String[] line1 = new String[]{"\"123\"","\"675\"","\"199\""};
        String[] line2 = new String[]{"\"444\"","\"909\"","\"999\""};
        String[] line3 = new String[]{"\"333\"","\"909\"","\"756\""};
        String[] line4 = new String[]{"\"266\"","\"621\"","\"756\""};
        String[] line5 = new String[]{"\"123\"","\"653\"","\"109\""};
        Set<String[]> initialLineSet = new HashSet<>(List.of(line1, line2, line3, line4, line5));
        groupManager.setAllLines(initialLineSet);

        Group group1 = new Group(line1);
        group1.addLine(line5);
        Group group2 = new Group(line2);
        group2.addLine(line3);
        group2.addLine(line4);
        Set<Group> expectedGroupSet = new HashSet<>(List.of(group1, group2));

        Map<String, List<String[]>> repeats = new HashMap<>();
        repeats.put("\"123\"_0", List.of(line1, line5));
        repeats.put("\"909\"_1", List.of(line2, line3));
        repeats.put("\"756\"_2", List.of(line3, line4));

        // When
        groupManager.setRepeats(repeats);
        groupManager.formMultilineGroups_public();
        Set<Group> resultGroupSet = groupManager.getGroups();

        // Then
        Assertions.assertEquals(expectedGroupSet.size(), resultGroupSet.size());
        Assertions.assertIterableEquals(expectedGroupSet, resultGroupSet);
    }*/
}

