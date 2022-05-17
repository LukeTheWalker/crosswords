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

    public Suggestion getSuggestion(String number){
        Suggestion suggestion = new Suggestion();

        suggestion.setHorizontalSuggestion(Utils.getMatchingElement(orizzontali, " " + number + "\\."));
        suggestion.setVerticalSuggestion(Utils.getMatchingElement(verticali, " " + number + "\\.")); 
            
        return suggestion; 
    }

    private boolean insertLetter(Coords coords, char c){
        if(grid[coords.getX_cord()][coords.getY_cord()] == "█") return false;
        else{
            grid[coords.getX_cord()][coords.getY_cord()] = String.valueOf(c);
        }
        return true;
    }

    private boolean insertWord(Coords coords, String direction, String word){
        for(int i = 0; i < word.length(); i++){
            if(!insertLetter(coords, word.charAt(i))) return false;
            if(direction == "horizontal") coords.setY_cord(coords.getY_cord()+1);
            else coords.setX_cord(coords.getX_cord()+1);
        }
        return true;
    }

    public void updateGrid(Coords coords, String direction, String word){
        if(!insertWord(coords, direction, word)){
            System.out.println("error inserting word");
            return;
        };
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
