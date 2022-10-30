package Programm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Random;

public class BruteForce {

    private final CaesarCipher cipher = new CaesarCipher();
    private final BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

    public void bruteForce() throws IOException {

        System.out.println("Выберите файл для расшифровки");
        String pathEncrypted = console.readLine();

        System.out.println("Введите адресс куда нужно сохранить расшифрованный файл");
        String pathNotEncrypted = console.readLine();

        try (BufferedReader reader = Files.newBufferedReader(Path.of(pathEncrypted));
             BufferedWriter writer = Files.newBufferedWriter(Path.of(pathNotEncrypted))) {
            StringBuilder builder = new StringBuilder();
            ArrayList<String> list = new ArrayList<>();
            while (reader.ready()) {
                String str = reader.readLine();
                list.add(str);
                builder.append(str);
            }
            for (int i = 0; i < cipher.alphabetLength(); i++) {
                String decrypt = cipher.decrypt(builder.toString(), i);
                if (isValidateText(decrypt)){
                       for (String str : list){
                           String encrypt = cipher.decrypt(str, i);
                           writer.write(encrypt + System.lineSeparator());
                       }
                    System.out.println("Содержимое файла расшифровано ключ расшифровки key = " + i);
                    break;
                }
            }
        }
    }

    private boolean isValidateText (String text) throws IOException{
        boolean isValidate = false;
        for (String word : text.split(" ")) {
            if (word.length() > 25){// почему больше 24
                return false;
            }
        }
        if (text.contains(". ") || text.contains("! ") || text.contains(", ") || text.contains("? ")){
            isValidate = true;
        }
        int indexStart = new Random().nextInt(text.length() / 2);
        int indexEnd = indexStart + (int)Math.sqrt(text.length());
        String substring = text.substring(indexStart, indexEnd);
        while (isValidate) {
            System.out.println("Правильный ли текст? Y/N " + "" + substring);
            String answer = console.readLine();
            if (answer.equalsIgnoreCase("Y")) {
                return true;
            } else if (answer.equalsIgnoreCase("N")) {
                isValidate = false;
            } else {
                System.out.println("Выберите Y или N");
            }
        }
        return false;
    }
}
