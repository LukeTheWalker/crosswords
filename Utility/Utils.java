import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

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

    public static PhysicalComposition getPhysicalComposition (String filename){
        PhysicalComposition obj = null;
        try (InputStream inputStream = new FileInputStream(filename)) {
            Yaml yaml = new Yaml(new Constructor(PhysicalComposition.class));
            obj = yaml.load(inputStream);
        } catch (IOException e) {
            System.err.println("Impossibile trovare Composition File");
            e.printStackTrace();
            System.exit(1);
        }
        return obj;
    }

    public static String [][] initializeGrid(PhysicalComposition physicalComposition) throws IOException, FileNotFoundException{
        String [][] grid = new String [physicalComposition.getSize().getWidth()] [physicalComposition.getSize().getHeight()];
        File savefile = new File("Data/save.txt");
        RandomAccessFile rac = new RandomAccessFile(savefile, "rw");
        if(rac.length() != 0) initializeFromSave(physicalComposition, grid, rac);
        else initializeFromPC(physicalComposition, grid);
        return grid;
    }

    private static void initializeFromSave(PhysicalComposition physicalComposition, String[][] grid, RandomAccessFile rac) throws IOException{
        rac.seek(0);
        for (int i = 0; i < physicalComposition.getSize().getWidth(); i++){
            for (int j = 0; j < physicalComposition.getSize().getHeight(); j++){
                grid[i][j] = Character.toString((char)rac.read());
            }
        }
        rac.close();
    }

    private static void initializeFromPC(PhysicalComposition physicalComposition, String[][] grid){
        for (int i = 0; i < physicalComposition.getSize().getWidth(); i++)
            for (int j = 0; j < physicalComposition.getSize().getHeight(); j++)
                grid[i][j] = " ";    
        for (Coords black: physicalComposition.getBlacks())
        grid[black.getX_cord()][black.getY_cord()] = "-";
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
        System.out.print("Premi invio per continuare...");
        Utils.sc.nextLine();
        TerminalCursor.clearLines(2);
    }
}
