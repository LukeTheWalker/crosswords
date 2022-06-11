import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Utils {
    public static Scanner sc = new Scanner(System.in); 

    public static String convertNumberToSuperscript(String numberString) {
        numberString = numberString.replaceAll("0", "⁰");
        numberString = numberString.replaceAll("1", "¹");
        numberString = numberString.replaceAll("2", "²");
        numberString = numberString.replaceAll("3", "³");
        numberString = numberString.replaceAll("4", "⁴");
        numberString = numberString.replaceAll("5", "⁵");
        numberString = numberString.replaceAll("6", "⁶");
        numberString = numberString.replaceAll("7", "⁷");
        numberString = numberString.replaceAll("8", "⁸");
        numberString = numberString.replaceAll("9", "⁹");         
        return numberString;
    }

    public static String convertNumberToSubscript(String numberString) {
        numberString = numberString.replaceAll("0", "₀");
        numberString = numberString.replaceAll("1", "₁");
        numberString = numberString.replaceAll("2", "₂");
        numberString = numberString.replaceAll("3", "₃");
        numberString = numberString.replaceAll("4", "₄");
        numberString = numberString.replaceAll("5", "₅");
        numberString = numberString.replaceAll("6", "₆");
        numberString = numberString.replaceAll("7", "₇");
        numberString = numberString.replaceAll("8", "₈");
        numberString = numberString.replaceAll("9", "₉");         
        return numberString;
    }

    public static String getMatchingElement(List<String> lst, String text){
        String pattern = ".*" + text + ".*";        
        try {
            return lst
                .stream()
                .filter(str -> str.matches(pattern))
                .findAny()
                .get();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public static Boolean isNumber(String number){
        try {  
            Integer.parseInt(number);  
            return true;
          } catch(NumberFormatException e){  
            return false;  
          }
    }
}
