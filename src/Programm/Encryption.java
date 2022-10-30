package Programm;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;

public class Encryption {

    private final CaesarCipher cipher = new CaesarCipher();
    private final BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

    public void encryption() throws IOException {

        System.out.println("Выберите файл");
        String pathNotEncrypted = console.readLine();

        System.out.println("Введите ключ");
        int key = Integer.parseInt(console.readLine());

        System.out.println("Введите адресс куда нужно сохранить файл");
        String pathEncrypted = console.readLine();

        try (BufferedReader reader = Files.newBufferedReader(Path.of(pathNotEncrypted));
                BufferedWriter writer = Files.newBufferedWriter(Path.of(pathEncrypted))) {
           while (reader.ready()){
               String string = reader.readLine();
               String encrypt = cipher.encrypt(string, key);
               writer.write(encrypt + System.lineSeparator());
           }
        }
        System.out.println("Содержимое файла зашифровано");
    }

}
