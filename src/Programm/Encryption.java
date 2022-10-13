package Programm;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Encryption {

    private static final String en = "abcdefghijklmnopqrstuvwxyz";
    private static final String ru = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";
    private static final int shift = 1;

    public static ArrayList<String> encrypt(List<String> list) throws IOException {

        ArrayList<String> arrayList = new ArrayList<>();

        for (String a : list) {
            for (int j = 0; j < a.length(); j++) {
                char chars = a.charAt(j);
                for (int k = 0; k < en.length(); k++) {
                    if (chars == en.charAt(k) && en.indexOf(en.charAt(k)) != en.length() - 1) {
                        chars = en.charAt(k + shift);
                    }
                }
                for (int k = 0; k < ru.length(); k++) {
                    if (chars == ru.charAt(k) && ru.indexOf(en.charAt(k)) != ru.length() - 1) {
                        chars = ru.charAt(k + shift);
                    }
                }
            }
        }
        return null;
    }

}
