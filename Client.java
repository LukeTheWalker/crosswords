import java.io.FileNotFoundException;

public class Client {
    public static void main(String args[]) throws FileNotFoundException {
        Crossword c = new Crossword("orizzontali.txt", "verticali.txt", "physical_composition.yaml");
        c.showGrid();
    }
    
}