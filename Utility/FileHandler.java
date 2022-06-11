import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import YamlStructure.Coords;
import YamlStructure.PhysicalComposition;

class FileHandler{
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

    public static String [][] initializeGrid(PhysicalComposition physicalComposition){
        String [][] grid = new String [physicalComposition.getSize().getWidth()] [physicalComposition.getSize().getHeight()];
        File savefile = new File("Data/save.txt");
        try (RandomAccessFile rac = new RandomAccessFile(savefile, "rw")) {
            if(rac.length() != 0) initializeFromSave(physicalComposition, grid, rac);
            else initializeFromPC(physicalComposition, grid);
        } catch (IOException e) {
            System.err.println("Impossibile interagire con il File di salvataggio");
            e.printStackTrace();
            System.exit(1);
        }
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
}