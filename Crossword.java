import java.util.ArrayList;
import java.util.List;

public class Crossword extends Subject{
    public List<String> orizzontali = new ArrayList<>();
    public List<String> verticali = new ArrayList<>();
    public Integer width;
    public Integer height;
    public List<Coords> blacks = new ArrayList<>();
    public List<Number> numbers = new ArrayList<>();

    Crossword(String orizzontali_filename, String verticali_filename){
        orizzontali = Utils.getSuggestions(orizzontali_filename);
        verticali = Utils.getSuggestions(verticali_filename);
    }
}
