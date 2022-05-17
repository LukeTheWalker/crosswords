import java.io.File;  // Import the File class
import java.io.FileInputStream;
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner; // Import the Scanner class to read text files

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

public class Utils {
    static List<String> getSuggestions (String filename){
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

    static PhysicalComposition getPhysicalComposition (String filename) throws FileNotFoundException{
        InputStream inputStream = new FileInputStream(filename);
        Yaml yaml = new Yaml(new Constructor(PhysicalComposition.class));
        PhysicalComposition obj = yaml.load(inputStream);
        return obj;
    }

    static String [][] getGrid(PhysicalComposition physicalComposition){
        String [][] grid = new String [physicalComposition.getSize().getWidth()] [physicalComposition.getSize().getHeight()];
        for (int i = 0; i < physicalComposition.getSize().getWidth(); i++)
            for (int j = 0; j < physicalComposition.getSize().getHeight(); j++)
                grid[i][j] = "   ";
        for (Coords black: physicalComposition.getBlacks())
            grid[black.getX_cord()][black.getY_cord()] = "███";

        // for (Number number: physicalComposition.getNumbers())
        for (int i = 1; i < physicalComposition.getNumbers().size(); i++){
            Number number = physicalComposition.getNumbers().get(i);
            //grid[number.getY_cord()][number.getX_cord()] = String.format("%02d", number.getNumber());
            grid[number.getX_cord()][number.getY_cord()] =  convertNumberToSuperscript(String.format("%2d", number.getNumber())) + " ";
        }
        
        grid[0][0] = grid[0][0].substring(0, grid[0][0].length()) + "S";
        grid[1][0] = grid[0][0].substring(0, grid[0][0].length()) + "T";
        grid[2][0] = grid[0][0].substring(0, grid[0][0].length()) + "R";
        grid[3][0] = grid[0][0].substring(0, grid[0][0].length()) + "A";
        grid[4][0] = grid[0][0].substring(0, grid[0][0].length()) + "P";
        grid[5][0] = grid[0][0].substring(0, grid[0][0].length()) + "P";
        grid[6][0] = grid[0][0].substring(0, grid[0][0].length()) + "O";
        return grid;
    }

    // static Character convertFigureToSuperScript(Character n){
    //     switch (n){
    //         case '1':
    //             return '₁';
    //         case '2':
    //             return '₂';
    //         case '3':
    //             return '₃';
    //         case '4':
    //             return '₄';
    //         case '5':
    //             return '₅';
    //         case '6':
    //             return '₆';
    //         case '7':
    //             return '₇';
    //         case '8':
    //             return '₈';
    //         case '9':
    //             return '₉';
    //         case '0':
    //             return '₀';
    //     }
    //     return 'f';
    // }
    // static String convertNumberToSuperscript(String numberString){
    //     String res = "";
    //     for (Character n : numberString.toCharArray())
    //         res += convertFigureToSuperScript(n);
    //     return res;
    // }
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
}
