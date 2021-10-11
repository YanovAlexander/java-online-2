package ua.goit;

import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        menu();
    }


    public static void menu() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Library. Please, enter help to see available commands:");
        String command = null;
       do {
           command = scanner.nextLine();
           switch (command) {
               case ("help"):
                   System.out.println("Enter help to see available commands.");
                   System.out.println("Enter exit to exit.");
                   break;
               case ("exit"):
                   System.out.println("Good bye!");
                   break;
               default:
                   System.out.println("Please, try again.");
                   break;
           }
       } while (!command.equals("exit"));
       scanner.close();
    }
}
