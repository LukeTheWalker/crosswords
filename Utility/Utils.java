import java.io.File;  // Import the File class
import java.io.FileInputStream;
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner; // Import the Scanner class to read text files

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import YamlStructure.Coords;
import YamlStructure.PhysicalComposition;


public class Utils {
    public static Scanner sc = new Scanner(System.in); 
    public static List<String> getSuggestions (String filename){
        List<String> res = new ArrayList<>();
        try {
            File file = new File(filename);
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                res.add(data);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return res;
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

    public static PhysicalComposition getPhysicalComposition (String filename) throws FileNotFoundException{
        InputStream inputStream = new FileInputStream(filename);
        Yaml yaml = new Yaml(new Constructor(PhysicalComposition.class));
        PhysicalComposition obj = yaml.load(inputStream);
        return obj;
    }

    public static String [][] initializeGrid(PhysicalComposition physicalComposition){
        String [][] grid = new String [physicalComposition.getSize().getWidth()] [physicalComposition.getSize().getHeight()];
        for (int i = 0; i < physicalComposition.getSize().getWidth(); i++)
            for (int j = 0; j < physicalComposition.getSize().getHeight(); j++)
                grid[i][j] = " ";
        for (Coords black: physicalComposition.getBlacks())
            grid[black.getX_cord()][black.getY_cord()] = "black";

        // for (Number number: physicalComposition.getNumbers())
        // for (int i = 1; i < physicalComposition.getNumbers().size(); i++){
        //     Number number = physicalComposition.getNumbers().get(i);
        //     //grid[number.getY_cord()][number.getX_cord()] = String.format("%02d", number.getNumber());
        //     grid[number.getX_cord()][number.getY_cord()] =  convertNumberToSuperscript(String.format("%2d", number.getNumber())) + " ";
        // }
        
        // grid[0][0] = grid[0][0].substring(0, grid[0][0].length()-1) + "S";
        // grid[1][0] = grid[1][0].substring(0, grid[0][0].length()-1) + "T";
        // grid[2][0] = grid[2][0].substring(0, grid[0][0].length()-1) + "R";
        // grid[3][0] = grid[3][0].substring(0, grid[0][0].length()-1) + "A";
        // grid[4][0] = grid[4][0].substring(0, grid[0][0].length()-1) + "P";
        // grid[5][0] = grid[5][0].substring(0, grid[0][0].length()-1) + "P";
        // grid[6][0] = grid[6][0].substring(0, grid[0][0].length()-1) + "O";
        return grid;
    }

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

    public static String getMatchingElement(List<String> lst, String text){
        String pattern = ".*" + text + ".*";
        //System.out.println(pattern);
        
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

    public static void printInputError(String error){
        System.out.println(error + ", riprovare");
        System.out.println("Press Enter to continue...");
        Utils.sc.nextLine();
        TerminalCursor.clearLines(3);
    }
}
