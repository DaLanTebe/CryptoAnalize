package Programm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Menu {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {

            System.out.println("выберите действие введя его номер:" +
                    "1.Зашифровать" +
                    "2.Расшифровать" +
                    "3.Подобрать ключ" +
                    "4.Расшифровать используя стат. анализ" +
                    "5.Выход");
            String answer = reader.readLine();
            switch (answer) {
                case ("1") -> new Encryption().encryption();
                case ("2") -> new Decryption().decryption();
                case ("3") -> new BruteForce().bruteForce();
                case ("4") -> new StatAnalise().statAnalise();
                case ("5") -> {return;}
            }
        }
    }
}
