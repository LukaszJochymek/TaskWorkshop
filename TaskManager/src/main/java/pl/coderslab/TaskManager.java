package pl.coderslab;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TaskManager {
    public static void main(String[] args){
        File file = new File("tasks.csv");
        try (Scanner scan = new Scanner(file)) {
            while (scan.hasNextLine()) {
                System.out.println(scan.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.err.println("Nie znaleziono pliku");
        }
        String[] inputOptions = {"add","remove","list","exit"};
        displayOptions(inputOptions);
    }

    public static void displayOptions(String[] options){
        System.out.println(ConsoleColors.BLUE + "Please select an option:");
        for(int i = 0; i<options.length; i++){
                System.out.println(ConsoleColors.RESET + options[i]);
        }
    }


}
