import java.io.File;  // Import the File class
import java.io.FileInputStream;
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
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

    public static String [][] initializeGrid(PhysicalComposition physicalComposition) throws IOException{
        String [][] grid = new String [physicalComposition.getSize().getWidth()] [physicalComposition.getSize().getHeight()];
        File savefile = new File("Data/save.txt");
        try (RandomAccessFile rac = new RandomAccessFile(savefile, "rw")) {
            if(rac.length() != 0){
                rac.seek(0);
                for (int i = 0; i < physicalComposition.getSize().getWidth(); i++){
                    for (int j = 0; j < physicalComposition.getSize().getHeight(); j++){
                        grid[i][j] = Character.toString((char)rac.read());
                    }
                }
            }else{
                for (int i = 0; i < physicalComposition.getSize().getWidth(); i++)
                    for (int j = 0; j < physicalComposition.getSize().getHeight(); j++)
                        grid[i][j] = " ";    
                for (Coords black: physicalComposition.getBlacks())
                grid[black.getX_cord()][black.getY_cord()] = "-";
            }
        } catch (FileNotFoundException e) {
        }

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
