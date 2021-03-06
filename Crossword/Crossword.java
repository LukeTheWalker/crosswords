import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import YamlStructure.Coords;
import YamlStructure.Number;
import YamlStructure.PhysicalComposition;


public class Crossword extends Subject{
    private List<String> orizzontali = new ArrayList<>();
    private List<String> verticali = new ArrayList<>();
    private PhysicalComposition physicalComposition;
    private String[][] grid;
    

    Crossword(String orizzontali_filename, String verticali_filename, String physical_composition_filename){
        orizzontali = FileHandler.getSuggestions(orizzontali_filename);
        verticali = FileHandler.getSuggestions(verticali_filename);
        physicalComposition = FileHandler.getPhysicalComposition(physical_composition_filename);
        grid = FileHandler.initializeGrid(physicalComposition);
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
        suggestion.setHorizontalWordCoords(getWordCoords(suggestion.toCoords(), "o"));
        suggestion.setVerticalWordCoords  (getWordCoords(suggestion.toCoords(), "v"));

        return suggestion; 
    }

    private Boolean checkInsertion(Coords coords){
        int width = this.physicalComposition.getSize().getWidth();
        int height = this.physicalComposition.getSize().getHeight();
        if((coords.getX_cord() >=  width) || (coords.getY_cord() >=  height)) return false;

        return !grid[coords.getX_cord()][coords.getY_cord()].equals("-");
    }

    private void insertLetter(Coords coord, char c){
        grid[coord.getX_cord()][coord.getY_cord()] = String.valueOf(c);
    }

    private void insertWord(List<Coords> coords, String word){
        for (int i = 0; i < word.length(); i++)
            insertLetter(coords.get(i), word.charAt(i));
    }

    public void updateGrid(List<Coords> coords, String word){
        insertWord(coords, word);
        setChanged();
        notify_observers(coords);
    }

    public List<Coords> getWordCoords (Coords startingCoords, String direction){
        return Stream
            .iterate(startingCoords, nextCoords -> nextCoords.getNextCoords(direction))
            .takeWhile(this::checkInsertion)
            .collect(Collectors.toList());
    }

    public Boolean validateNumber(int n){
        return getPhysicalComposition().getNumbers().stream().anyMatch(num -> num.getNumber() == n && n != 0);
    }

    public Boolean validateCoords(Coords coords){
        return coords.getX_cord() < getPhysicalComposition().getSize().getWidth()  &&
               coords.getY_cord() < getPhysicalComposition().getSize().getHeight() &&
               coords.getX_cord() >= 0 &&
               coords.getY_cord() >= 0;
    }
    public void notify_observers(List<Coords> coordsList) {
        super.notify(new ObserverData(physicalComposition.getSize().getHeight(), physicalComposition.getSize().getWidth(), coordsList, grid));
    }

    public PhysicalComposition getPhysicalComposition(){
        return this.physicalComposition;
    }

    public void setupGrid(){
        TerminalCursor.clearTerminal();
        printStandardGrid();
        for (Number number : physicalComposition.getNumbers()) {
            if(number.getNumber() != 0)
                insertNumber(number);
        }
        List<Coords> coordsList = new ArrayList<>();
        for(int i = 0; i < physicalComposition.getSize().getWidth(); i++){
            for(int j = 0; j < physicalComposition.getSize().getHeight(); j++){
                    coordsList.add(new Coords(i, j));
            }
        }
        setChanged();
        notify_observers(coordsList);
    }

    private void printStandardGrid(){
        int width = physicalComposition.getSize().getWidth();
        int height = physicalComposition.getSize().getHeight();
        System.out.print("???");
        for (int i = 0; i < width - 1; i++)
            System.out.print("????????????");
        System.out.println("????????????");
        System.out.print("???");
        for (int i = 0; i < width; i++)
            System.out.print("   ???");
        System.out.println();
        for (int k = 0; k < height - 1; k++) {
            System.out.print("???");
            for (int i = 0; i < width - 1; i++)
                System.out.print("????????????");
            System.out.println("????????????");
            System.out.print("???");
            for (int i = 0; i < width; i++)
                System.out.print("   ???");
            System.out.println();
        }
        System.out.print("???");
        for (int i = 0; i < width - 1; i++)
            System.out.print("????????????");
        System.out.println("????????????");
    }

    private void insertNumber(Number n){
        int height = physicalComposition.getSize().getHeight();
        TerminalCursor.cursorUp(height * 2 +1);
        TerminalCursor.cursorDown(n.getY_cord() * 2) ;
        TerminalCursor.cursorRight(1 + n.getX_cord()*4);
        System.out.print(Utils.convertNumberToSubscript(Integer.toString(n.getNumber())));
        TerminalCursor.cursorDown((height - n.getY_cord())*2 +1);
        System.out.print('\r');
    }
}
