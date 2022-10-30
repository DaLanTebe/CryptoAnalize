package Programm;

public class CaesarCipher {
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz" + "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
            "абвгдеёжзийклмнопрстуфхцчшщъыьэюя" + "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ" +
            ".,\":-!? +*/\\@#$%^&(){}[];'|`~=_©«»—" + "0123456789";

    public int alphabetLength (){
        return ALPHABET.length();
    }

    public String encrypt(String message, int key) {
        StringBuilder builder = new StringBuilder();
        for (Character character : message.toCharArray()) {
            int index = ALPHABET.indexOf(character);
            if (index >= 0) {
                int newIndex = (index + key) % ALPHABET.length();
                char newChar = newIndex >= 0 ? ALPHABET.charAt(newIndex) : ALPHABET.charAt(ALPHABET.length() + newIndex);
                builder.append(newChar);
            }
        }
        return builder.toString();
    }

    public String decrypt (String message, int key){
        return encrypt(message, -key);
    }

}
