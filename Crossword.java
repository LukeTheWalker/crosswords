import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Crossword extends Subject{
    private List<String> orizzontali = new ArrayList<>();
    private List<String> verticali = new ArrayList<>();
    private PhysicalComposition physicalComposition;
    private String[][] grid;

    Crossword(String orizzontali_filename, String verticali_filename, String physical_composition_filename) throws FileNotFoundException{
        orizzontali = Utils.getSuggestions(orizzontali_filename);
        verticali = Utils.getSuggestions(verticali_filename);
        physicalComposition = Utils.getPhysicalComposition(physical_composition_filename);
        grid = Utils.initializeGrid(physicalComposition);
    }

    public Suggestion getSuggestion(String number_string){
        Suggestion suggestion = new Suggestion();
        Number number = this
                    .getPhysicalComposition()
                    .getNumbers()
                    .stream()
                    .filter(n -> n.getNumber() == Integer.parseInt(number_string))
                    .findAny()
                    .get();

        suggestion.setNumber(number);
        suggestion.setHorizontalSuggestion(Utils.getMatchingElement(orizzontali, " " + number_string + "\\."));
        suggestion.setVerticalSuggestion(Utils.getMatchingElement(verticali, " " + number_string + "\\.")); 
            
        return suggestion; 
    }

    public void updateGrid(Coords coords, String direction, String word){
        //TODO: update grid
        setChanged();
        notify_observers();
        return;
    }

    public void notify_observers() {
        super.notify(new ObserverData(physicalComposition, grid));
    }

    public PhysicalComposition getPhysicalComposition(){
        return this.physicalComposition;
    }
}
