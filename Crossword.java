import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.PrimitiveIterator;
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

    public Suggestion getSuggestion(String number){
        Suggestion suggestion = new Suggestion();

        suggestion.setHorizontalSuggestion(Utils.getMatchingElement(orizzontali, " " + number + "\\."));
        suggestion.setVerticalSuggestion(Utils.getMatchingElement(verticali, " " + number + "\\.")); 
            
        return suggestion; 
    }

    private void insertLetter(int x, int y, char c){
        grid[x][y] = String.valueOf(c);
        return;
    }

    private boolean insertWord(Coords coords, String direction, String word){
        int x = coords.getX_cord();
        int y = coords.getY_cord();
        IntStream stream = IntStream.rangeClosed(0, word.length()-1);
        IntStream stream2 = IntStream.rangeClosed(0, word.length()-1);
        PrimitiveIterator.OfInt answer = stream.iterator();
        if(direction == "o"){
            if(x+word.length() <= this.physicalComposition.getSize().getWidth()){
                while(answer.hasNext()){
                    if(grid[x+answer.nextInt()][y] == "█") return false;
                }
            }
            stream2.forEach(i -> {
                insertLetter(x+i, y, word.charAt(i));
            });
        }else{
            if(y+word.length() <= this.physicalComposition.getSize().getHeight()){
               while(answer.hasNext()){
                if(grid[x][y+answer.nextInt()] == "█") return false;
               }
            }
            stream2.forEach(i -> {
                insertLetter(x, y+i, word.charAt(i));
            });
        }
        return true;
    }


    // private boolean insertWord(Coords coords, String direction, String word){
    //     int x = coords.getX_cord();
    //     int y = coords.getY_cord();
    //     if(direction.equals("o")){
    //         if(x+word.length() > this.physicalComposition.getSize().getWidth()) return false;
    //     }else{
    //         if(y+word.length() > this.physicalComposition.getSize().getHeight()) return false;
    //     }
    //     for(int i = 0; i < word.length(); i++){
    //         if(direction.equals("o")){
    //             if(grid[x+i][y] == "█") return false;
    //         }else{
    //             if(grid[x][y+i] == "█") return false;
    //         }
    //     }
    //     for(int i = 0; i < word.length(); i++){
    //         insertLetter(x, y, word.charAt(i));
    //         if(direction.equals("o")) x++;
    //         else y++;
    //     }
    //     return true;
    // }

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
