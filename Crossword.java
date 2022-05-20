import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    private Boolean checkInsertion(Coords coords){
        int width = this.physicalComposition.getSize().getWidth();
        int height = this.physicalComposition.getSize().getHeight();
        if((coords.getX_cord() >=  width) || (coords.getY_cord() >=  height)) return false;

        return !grid[coords.getX_cord()][coords.getY_cord()].equals("█");
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
        notify_observers();
    }

    public List<Coords> getWordCoords (Coords startingCoords, String direction){
        return Stream
            .iterate(startingCoords, nextCoords -> nextCoords.getNextCoords(direction))
            .takeWhile(this::checkInsertion)
            .collect(Collectors.toList());
    }

    public Boolean validateNumber(int n){
        return getPhysicalComposition().getNumbers().stream().anyMatch(num -> num.getNumber() >= n);
    }

    public Boolean validateCoords(Coords coords){
        return coords.getX_cord() < getPhysicalComposition().getSize().getWidth()  &&
               coords.getY_cord() < getPhysicalComposition().getSize().getHeight() &&
               coords.getX_cord() >= 0 &&
               coords.getY_cord() >= 0;
    }
    public void notify_observers() {
        super.notify(new ObserverData(physicalComposition, grid));
    }

    public PhysicalComposition getPhysicalComposition(){
        return this.physicalComposition;
    }

    public void setupGrid(){
        //TerminalCursor.clearTerminal();
        printStandardGrid();
        for (Number n : physicalComposition.getNumbers()) {
            insertNumber(n);
        }
        for (Coords b : physicalComposition.getBlacks()) {
            insertBlack(b);
        }
    }

    private void printStandardGrid(){
        int width = physicalComposition.getSize().getWidth();
        int height = physicalComposition.getSize().getHeight();
        System.out.print("┌");
        for (int i = 0; i < width - 1; i++)
            System.out.print("───┬");
        System.out.println("───┐");
        System.out.print("│");
        for (int i = 0; i < width; i++)
            System.out.print("   │");
        System.out.println();
        for (int k = 0; k < height - 1; k++) {
            System.out.print("├");
            for (int i = 0; i < width - 1; i++)
                System.out.print("───┼");
            System.out.println("───┤");
            System.out.print("│");
            for (int i = 0; i < width; i++)
                System.out.print("   │");
            System.out.println();
        }
        System.out.print("└");
        for (int i = 0; i < width - 1; i++)
            System.out.print("───┴");
        System.out.println("───┘");
    }

    private String convertNumberToSubscript(String numberString) {
        numberString = numberString.replaceAll("0", "₀");
        numberString = numberString.replaceAll("1", "₁");
        numberString = numberString.replaceAll("2", "₂");
        numberString = numberString.replaceAll("3", "₃");
        numberString = numberString.replaceAll("4", "₄");
        numberString = numberString.replaceAll("5", "₅");
        numberString = numberString.replaceAll("6", "₆");
        numberString = numberString.replaceAll("7", "₇");
        numberString = numberString.replaceAll("8", "₈");
        numberString = numberString.replaceAll("9", "₉");         
        return numberString;
    }


    private void insertNumber(Number n){
        int height = physicalComposition.getSize().getHeight();
        TerminalCursor.cursorUp(height * 2 +1);
        TerminalCursor.cursorDown(n.getY_cord() * 2) ;
        TerminalCursor.cursorRight(1 + n.getX_cord()*4);
        System.out.print(convertNumberToSubscript(Integer.toString(n.getNumber())));
        TerminalCursor.cursorDown((height - n.getY_cord())*2 +1);
        System.out.print('\r');
    }

    private void insertBlack(Coords c){
        int height = physicalComposition.getSize().getHeight();
        TerminalCursor.cursorUp(height * 2 +1);
        TerminalCursor.cursorDown(c.getY_cord() * 2 + 1) ;
        TerminalCursor.cursorRight(1 + c.getX_cord()*4);
        System.out.print("███");
        TerminalCursor.cursorDown((height - c.getY_cord())*2 +1);
        System.out.print('\r');
    }
}
