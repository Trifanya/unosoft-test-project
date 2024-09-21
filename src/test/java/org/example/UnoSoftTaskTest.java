package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UnoSoftTaskTest {
    public static final String testInputFile1 = "D:\\Программирование на Java\\Тестовые задания\\UnoSoftTestProject\\src\\test\\resources\\test_input_files\\test1_incorrect_lines.txt";
    public static final String testInputFile2 = "D:\\Программирование на Java\\Тестовые задания\\UnoSoftTestProject\\src\\test\\resources\\test_input_files\\test2_10lines.txt";
    public static final String testInputFile3 = "D:\\Программирование на Java\\Тестовые задания\\UnoSoftTestProject\\src\\test\\resources\\test_input_files\\test3_20lines.txt";
    public static final String testInputFile4 = "D:\\Программирование на Java\\Тестовые задания\\UnoSoftTestProject\\src\\test\\resources\\test_input_files\\test4_10nonintersecting_lines.txt";
    public static final String testInputFile5 = "D:\\Программирование на Java\\Тестовые задания\\UnoSoftTestProject\\src\\test\\resources\\test_input_files\\test5_20lines_10unique_lines.txt";
    public static final String testInputFile6 = "D:\\Программирование на Java\\Тестовые задания\\UnoSoftTestProject\\src\\test\\resources\\test_input_files\\test6_20lines.txt";
    public static final String testInputFile7 = "D:\\Программирование на Java\\Тестовые задания\\UnoSoftTestProject\\src\\test\\resources\\test_input_files\\test7_20lines.txt";

    public static final String testOutputFile1 = "D:\\Программирование на Java\\Тестовые задания\\UnoSoftTestProject\\src\\test\\resources\\test_output_files\\test1_result.txt";
    public static final String testOutputFile2 = "D:\\Программирование на Java\\Тестовые задания\\UnoSoftTestProject\\src\\test\\resources\\test_output_files\\test2_result.txt";
    public static final String testOutputFile3 = "D:\\Программирование на Java\\Тестовые задания\\UnoSoftTestProject\\src\\test\\resources\\test_output_files\\test3_result.txt";
    public static final String testOutputFile4 = "D:\\Программирование на Java\\Тестовые задания\\UnoSoftTestProject\\src\\test\\resources\\test_output_files\\test4_result.txt";
    public static final String testOutputFile5 = "D:\\Программирование на Java\\Тестовые задания\\UnoSoftTestProject\\src\\test\\resources\\test_output_files\\test5_result.txt";
    public static final String testOutputFile6 = "D:\\Программирование на Java\\Тестовые задания\\UnoSoftTestProject\\src\\test\\resources\\test_output_files\\test6_result.txt";
    public static final String testOutputFile7 = "D:\\Программирование на Java\\Тестовые задания\\UnoSoftTestProject\\src\\test\\resources\\test_output_files\\test7_result.txt";

    @Test
    public void completeTask_dataSet1() {
        // Given
        UnoSoftTask unoSoftTask = new UnoSoftTask();

        // When
        unoSoftTask.solve(testInputFile1, testOutputFile1);

        // Then
        Assertions.assertEquals(3, unoSoftTask.getGroupCount());
        Assertions.assertEquals(3, unoSoftTask.getSingleLineGroupCount());
        Assertions.assertEquals(1, unoSoftTask.getMaxLinesCount());
    }

    @Test
    public void completeTask_dataSet2() {
        // Given
        UnoSoftTask unoSoftTask = new UnoSoftTask();

        // When
        unoSoftTask.solve(testInputFile2, testOutputFile2);

        // Then
        Assertions.assertEquals(2, unoSoftTask.getGroupCount());
        Assertions.assertEquals(0, unoSoftTask.getSingleLineGroupCount());
        Assertions.assertEquals(8, unoSoftTask.getMaxLinesCount());
    }

    @Test
    public void completeTask_dataSet3() {
        // Given
        UnoSoftTask unoSoftTask = new UnoSoftTask();

        // When
        unoSoftTask.solve(testInputFile3, testOutputFile3);

        // Then
        Assertions.assertEquals(3, unoSoftTask.getGroupCount());
        Assertions.assertEquals(1, unoSoftTask.getSingleLineGroupCount());
        Assertions.assertEquals(16, unoSoftTask.getMaxLinesCount());
    }

    @Test
    public void completeTask_dataSet4() {
        // Given
        UnoSoftTask unoSoftTask = new UnoSoftTask();

        // When
        unoSoftTask.solve(testInputFile4, testOutputFile4);

        // Then
        Assertions.assertEquals(10, unoSoftTask.getGroupCount());
        Assertions.assertEquals(10, unoSoftTask.getSingleLineGroupCount());
        Assertions.assertEquals(1, unoSoftTask.getMaxLinesCount());
    }

    @Test
    public void completeTask_dataSet5() {
        // Given
        UnoSoftTask unoSoftTask = new UnoSoftTask();

        // When
        unoSoftTask.solve(testInputFile5, testOutputFile5);

        // Then
        Assertions.assertEquals(2, unoSoftTask.getGroupCount());
        Assertions.assertEquals(0, unoSoftTask.getSingleLineGroupCount());
        Assertions.assertEquals(8, unoSoftTask.getMaxLinesCount());
    }

    @Test
    public void completeTask_dataSet6() {
        // Given
        UnoSoftTask unoSoftTask = new UnoSoftTask();

        // When
        unoSoftTask.solve(testInputFile6, testOutputFile6);

        // Then
        Assertions.assertEquals(3, unoSoftTask.getGroupCount());
        Assertions.assertEquals(1, unoSoftTask.getSingleLineGroupCount());
        Assertions.assertEquals(16, unoSoftTask.getMaxLinesCount());
    }

    @Test
    public void completeTask_dataSet7() {
        // Given
        UnoSoftTask unoSoftTask = new UnoSoftTask();

        // When
        unoSoftTask.solve(testInputFile7, testOutputFile7);

        // Then
        Assertions.assertEquals(4, unoSoftTask.getGroupCount());
        Assertions.assertEquals(2, unoSoftTask.getSingleLineGroupCount());
        Assertions.assertEquals(15, unoSoftTask.getMaxLinesCount());
    }
}
