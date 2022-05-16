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
        for (Coords black: physicalComposition.getBlacks())
            grid[black.getY_cord()][black.getX_cord()] = "11";

        // for (Number number: physicalComposition.getNumbers())
        for (int i = 1; i < physicalComposition.getNumbers().size(); i++){
            Number number = physicalComposition.getNumbers().get(i);
            grid[number.getY_cord()][number.getX_cord()] = String.format("%02d", number.getNumber());
        }
        
        return grid;
    }
}
