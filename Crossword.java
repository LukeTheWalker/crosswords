import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import java.util.stream.IntStream;

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

    private void insertLetter(Coords coord, char c){
        grid[coord.getX_cord()][coord.getY_cord()] = String.valueOf(c);
    }

    private Boolean checkInsertion(Coords coords){
        int width = this.physicalComposition.getSize().getWidth();
        int height = this.physicalComposition.getSize().getHeight();
        if((coords.getX_cord() >=  width) || (coords.getY_cord() >=  height)) return false;

        return !grid[coords.getX_cord()][coords.getY_cord()].equals("█");
    }

    private Boolean insertWord(Coords coords, String direction, String word){
        int x = coords.getX_cord();
        int y = coords.getY_cord();
        int isOrizzontale = direction.equals("o") ? 1 : 0;
        Boolean isValid = IntStream.range(0, word.length())
                                    .mapToObj(i -> new Coords(x + i * isOrizzontale, y + i * (1 - isOrizzontale) ))
                                    .allMatch(coord -> (checkInsertion(coords)));

        if (isValid)
            for (int i = 0; i < word.length(); i++)
                insertLetter(new Coords(x + i * isOrizzontale, y + i * (1 - isOrizzontale)), word.charAt(i));

        return isValid;
    }

    public void updateGrid(Coords coords, String direction, String word){
        if(insertWord(coords, direction, word)){
            setChanged();
            notify_observers();
        }
        else 
            System.out.println("error inserting word");
    }

    public void notify_observers() {
        super.notify(new ObserverData(physicalComposition, grid));
    }

    public PhysicalComposition getPhysicalComposition(){
        return this.physicalComposition;
    }
}
