package dev.trifanya;

public class UnoSoftTaskApp {
    public static void main(String[] args) {
        String outputFile = "src/main/resources/result.txt";
        Task.solve(args[0], outputFile);
    }
}
