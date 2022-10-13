package Programm;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainMenu {
    public static void main(String[] args) {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("выберите: 1 2 3");
             int a = scanner.nextInt();
            if (a == 1) {
                try {
                System.out.println("Выберите файл");
                Path path = Path.of(scanner.next());
                    List<String> strings = Files.readAllLines(path);
                    Encryption.encrypt(strings);
                }catch (IOException e){
                    System.out.println("error");
                }
            } else if (a == 3) {
                System.out.println("Завершение работы");
                break;
            }
        }
    }
}
