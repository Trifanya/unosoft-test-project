package dev.trifanya;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class UnoSoftTaskApp {
    public static void main(String[] args) {
        long startTimeMillis = System.currentTimeMillis();
        System.out.println("Program started.");

        String propFileName = "src/main/resources/application.properties";
        String outputFile = null;

        try (FileInputStream fis = new FileInputStream(propFileName)) {
            Properties appProps = new Properties();
            appProps.load(fis);
            outputFile = appProps.getProperty("result-file-name");
        } catch (IOException e) {
            System.err.println("ОШИБКА: файл свойств не найден!");
        }

        Task.solve(args[0], outputFile);

        System.out.println(("Program ended.\n" +
                "Program running time: " + (System.currentTimeMillis() - startTimeMillis) / 1000d + " seconds."));
    }
}
