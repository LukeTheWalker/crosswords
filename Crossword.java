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
        int x = coords.getX_cord();
        int y = coords.getY_cord();
        if(direction == "o"){
            if(x+word.length() > this.physicalComposition.getSize().getWidth()) return false;
        }else{
            if(y+word.length() > this.physicalComposition.getSize().getHeight()) return false;
        }
        for(int i = 0; i < word.length(); i++){
            if(direction == "o"){
                if(grid[x+i][y] == "█") return false;
            }else{
                if(grid[x][y+i] == "█") return false;
            }
        }
        for(int i = 0; i < word.length(); i++){
            insertLetter(coords, word.charAt(i));
            if(direction == "o") y++;
            else x++;
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
