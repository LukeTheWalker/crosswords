import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Crossword extends Subject{
    public List<String> orizzontali = new ArrayList<>();
    public List<String> verticali = new ArrayList<>();
    public PhysicalComposition physicalComposition;
    public String[][] grid;

    Crossword(String orizzontali_filename, String verticali_filename, String physical_composition_filename) throws FileNotFoundException{
        orizzontali = Utils.getSuggestions(orizzontali_filename);
        verticali = Utils.getSuggestions(verticali_filename);
        physicalComposition = Utils.getPhysicalComposition(physical_composition_filename);
        grid = Utils.getGrid(physicalComposition);
    }

    public void showGrid(){
        for (int j = 0; j < physicalComposition.getSize().getHeight(); j++){
            for (int i = 0; i < physicalComposition.getSize().getWidth(); i++){
                //if (grid[i][j] == null) System.out.print("   " + " ");
                System.out.print(grid[i][j] +  " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
