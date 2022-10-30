package Programm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;

public class Decryption {

    private final CaesarCipher cipher = new CaesarCipher();
    private final BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

    public void decryption() throws IOException {

        System.out.println("Выберите файл для расшифровки");
        String pathEncrypted = console.readLine();

        System.out.println("Введите ключ");
        int key = Integer.parseInt(console.readLine());

        System.out.println("Введите адресс куда нужно сохранить расшифрованный файл");
        String pathNotEncrypted = console.readLine();

        try (BufferedReader reader = Files.newBufferedReader(Path.of(pathEncrypted));
             BufferedWriter writer = Files.newBufferedWriter(Path.of(pathNotEncrypted))) {
            while (reader.ready()){
                String string = reader.readLine();
                String decrypt = cipher.decrypt(string, key);
                writer.write(decrypt + System.lineSeparator());
            }
        }
        System.out.println("Содержимое файла расшифровано");
    }
}
