package pl.coderslab;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TaskManager {
    public static void main(String[] args) {


        String[] inputOptions = {"add", "remove", "list", "exit"};
        displayOptions(inputOptions);
        Scanner scan = new Scanner(System.in);
        String input = scan.next();
        try {
            String nameFile = "tasks.csv";
            String[][] inputNameFile = task(nameFile);
            choiceOptions(inputNameFile, input,nameFile);
        } catch (FileNotFoundException exception) {
            System.err.println(exception.getMessage());
        }

    }

    public static void displayOptions(String[] options) {
        System.out.println(ConsoleColors.BLUE + "Please select an option:");
        for (int i = 0; i < options.length; i++) {
            System.out.println(ConsoleColors.RESET + options[i]);
        }
    }

    public static String[][] task(String nameFile) throws FileNotFoundException {
        Path taskPath = Paths.get(nameFile);
        if (!Files.exists(taskPath)) {
            throw new FileNotFoundException("Source file does not exist");
        }
        String[][] tasks = null;
        try {
            List<String> strings = Files.readAllLines(taskPath);
            tasks = new String[strings.size()][strings.get(0).split(",").length];
            for (int i = 0; i < strings.size(); i++) {

                String[] split = strings.get(i).split(",");

                for (int j = 0; j < split.length; j++) {

                    tasks[i][j] = split[j];

                }
            }
        } catch (IOException exception) {
            exception.getMessage();
        }
        return tasks;


    }

    public static void choiceOptions(String[][] file, String choice, String nameFile) {

        switch (choice) {

            case "add":
                addTask(file);
                saveFile(file,nameFile);
                break;
            case "remove":
//                removeTask();
            case "list":
                for (int i = 0; i < file.length; i++) {
                    for (int j = 0; j < file[i].length; j++)
                        System.out.println(file[i][j]);
                }
                break;

            case "exit":
                break;


            default:

                System.out.println("Please select a correct option.");

        }
    }

    public static void addTask(String[][] file) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please add task description:");
        String taskDescription = scan.next();
        System.out.println("Please add task due date: ");
        String taskDate = scan.next();
        System.out.println("Is your task is important : true/false");
        String taskImportant = scan.next();

         file = Arrays.copyOf(file,file.length +1);
        file[file.length-1] = new String[3];
        file[file.length-1][0] = taskDescription;
        file[file.length-1][1] = taskDate;
        file[file.length-1][2] = taskImportant;


    }

    public static void saveFile(String[][] file, String nameFile){
        try {
            PrintWriter printWriter = new PrintWriter(nameFile);
            for (int i = 0; i < file.length; i++) {
                for (int j = 0; j < file[i].length; j++) {
                    printWriter.println(file[i]);
                }
                printWriter.close();
            }
        } catch (FileNotFoundException ex) {
        System.out.println("Błąd zapisu do pliku.");
    }
    }
}

//    public static String[][]task (String nameFile){
//        File file = new File(nameFile);
//        String[][] tasks = null;
//        try (Scanner scan = new Scanner(file)) {
//            while (scan.hasNextLine()) {
//                String line = scan.next();
//                for (int i = 0; i < scan.next().length(); i++) {
//                    String[] lines = line.split(",");
//
//                    for (int j = 0; j < lines.length; j++) {
//
//                        tasks[i][j] = lines[j].split(" ");
//
//                    }
//
//                }
//
//                 tasks = line.split(",");
//            }
//
//        } catch (FileNotFoundException e) {
//            System.err.println("Nie znaleziono pliku");
//
//        }return tasks;
//    }
