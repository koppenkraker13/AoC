import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Day16 {

    public static String hexToBin(String hex) {
        String binary = "";
        hex = hex.toUpperCase();
        HashMap<Character, String> hashMap = new HashMap<>();

        // storing the key value pairs
        hashMap.put('0', "0000");
        hashMap.put('1', "0001");
        hashMap.put('2', "0010");
        hashMap.put('3', "0011");
        hashMap.put('4', "0100");
        hashMap.put('5', "0101");
        hashMap.put('6', "0110");
        hashMap.put('7', "0111");
        hashMap.put('8', "1000");
        hashMap.put('9', "1001");
        hashMap.put('A', "1010");
        hashMap.put('B', "1011");
        hashMap.put('C', "1100");
        hashMap.put('D', "1101");
        hashMap.put('E', "1110");
        hashMap.put('F', "1111");

        int i;
        char ch;
        for (i = 0; i < hex.length(); i++) {
            ch = hex.charAt(i);
            if (hashMap.containsKey(ch)) {
                binary += hashMap.get(ch);
            } else {
                binary = "Invalid Hexadecimal String";
                return binary;
            }
        }
        return binary;
    }

    public static void main(String[] args) {
        Packet pakket= new Packet("");
        try (BufferedReader br = new BufferedReader(new FileReader("2021/day16/data/day16.txt"))) {
            String line = br.readLine();
            pakket = new Packet(hexToBin(line));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Sum of version numbers: " + pakket.getAllVersions());
        System.out.println("Total sum: " + pakket.getValue());
    }
}
