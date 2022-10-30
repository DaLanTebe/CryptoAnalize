package Programm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class StatAnalise {

    private final BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
    private final Map<Character, Integer> mapEncrypted = new HashMap<>();
    private final Map<Character, Integer> mapStatistic = new HashMap<>();
    private final Map<Character, Character> mapDecryption = new HashMap<>();

    public void statAnalise() throws IOException {

        System.out.println("Введите зашифрованный файл");
        String encrypted = console.readLine();

        System.out.println("Введите файл для набора статистики");
        String statistic = console.readLine();

        System.out.println("Введите файл для расшифрованного текста");
        String decryption = console.readLine();

        List<Map.Entry<Character, Integer>> listEncrypted = mapToList(fillMapValues(mapEncrypted, encrypted));
        List<Map.Entry<Character, Integer>> listStatistic = mapToList(fillMapValues(mapStatistic, statistic));

        if(listEncrypted.size() <= listStatistic.size()) {
            for (int i = 0; i < listEncrypted.size(); i++) {
                mapDecryption.put(listEncrypted.get(i).getKey(), listStatistic.get(i).getKey());
            }
            try (BufferedReader reader = Files.newBufferedReader(Path.of(encrypted));
                 BufferedWriter writer = Files.newBufferedWriter(Path.of(decryption));
            ) {
                StringBuilder builder = new StringBuilder();
                while (reader.ready()){
                    String string = reader.readLine();
                    for (char aChar : string.toCharArray()) {
                        Character value = mapDecryption.get(aChar);
                        builder.append(value);
                    }
                    writer.write(builder + System.lineSeparator());
                }
            }
            System.out.println("Содержимое расшифрованно методом стат. анализа");
        }else System.out.println("Размер файла статистики не подходит для расшифровки, необходим файл большей длины чем зашифрованный");

    }

    private Map<Character, Integer> fillMapValues(Map<Character, Integer> map, String path) throws IOException {

        try (BufferedReader reader = Files.newBufferedReader(Path.of(path))) {
            StringBuilder builder = new StringBuilder();
            while (reader.ready()) {
                String string = reader.readLine();
                builder.append(string);
            }
            for (char aChar : builder.toString().toCharArray()) {
                if (!map.containsKey(aChar)) {
                    map.put(aChar, 1);
                } else map.put(aChar, map.get(aChar) + 1);
            }
        }
        return map;
    }

    private List<Map.Entry<Character, Integer>> mapToList(Map<Character, Integer> map) {
        List<Map.Entry<Character,Integer>> list = new ArrayList<>(map.entrySet());
        list.sort((o1, o2) -> o2.getValue() - o1.getValue());
       // List<Map.Entry<Character, Integer>> list1 = map.entrySet().stream().sorted(Map.Entry.<Character, Integer>comparingByValue().reversed()).toList();
        return list;
    }
}
