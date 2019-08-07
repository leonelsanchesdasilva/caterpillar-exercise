package caterpillar.exercise;

import caterpillar.exercise.services.Service;
import caterpillar.exercise.services.ServiceImpl;

import java.util.Scanner;

public class App {
    public void printInstructions() {

        System.out.println("Caterpillar Sample Exercise");
        System.out.println("---------------------------------------------");
        System.out.println("1. Print these instructions;");
        System.out.println("2. Read element from cache;");
        System.out.println("3. Write element in cache;");
        System.out.println("0. Exit app.");
        System.out.println("---------------------------------------------");
        System.out.println("Option > ");
    }

    public static void main(String[] args) {
        int command = 1;
        Service service = new ServiceImpl();
        Scanner reader = new Scanner(System.in);

        do {
            System.out.println("---------------------------------------------");
            switch (command) {
                case 2:
                    System.out.println("Element key to read > ");
                    System.out.println(service.get(reader.nextLine()));
                    break;
                case 3:
                    System.out.print("Element key to write > ");
                    String key = reader.next();
                    System.out.print("Element value to write > ");
                    String value = reader.next();
                    service.put(key, value);
                    break;
                default:
                    new App().printInstructions();
                    break;
            }

            command = reader.nextInt();
        } while (command > 0);
    }
}
