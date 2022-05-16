import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Crossword extends Subject{
    public List<String> orizzontali = new ArrayList<>();
    public List<String> verticali = new ArrayList<>();
    public PhysicalComposition physicalComposition;

    Crossword(String orizzontali_filename, String verticali_filename, String physical_composition_filename) throws FileNotFoundException{
        orizzontali = Utils.getSuggestions(orizzontali_filename);
        verticali = Utils.getSuggestions(verticali_filename);
        physicalComposition = Utils.getPhysicalComposition(physical_composition_filename);
    }
}
