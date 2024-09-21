package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GrouperTest {
    private Set<Line> testLineSet1 = new HashSet<>(List.of(
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
    public void formSingleLineGroups() {

    }

    @Test
    public void countRepeats() {

    }

    @Test
    public void formNewGroup_whenTestDataSet1_should() {

    }
}
