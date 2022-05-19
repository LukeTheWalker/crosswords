import java.io.FileNotFoundException;

public class MenuContext {
    private MenuState state = new MainMenuState(this);
    private Crossword c;
    private Coords savedCoords = new Coords(-1, -1);

    MenuContext(String orizzontali_filename, String verticali_filename, String physical_composition_filename) throws FileNotFoundException{
        c = new Crossword(orizzontali_filename, verticali_filename, physical_composition_filename);
        Observer  f = new Frontend();
        c.attach(f);
    }

    public Coords getSavedCoords() {
        return savedCoords;
    }

    public void setSavedCoords(Coords savedCoords) {
        this.savedCoords = savedCoords;
    }

    public void start() {
        while (true){

            String action = state.printMenuOptions();
            state.handle(action);
        
        }
    }

    public Crossword getCrossword() {
        return c;
    }

    public MenuState getState() {
        return state;
    }

    public void setState(MenuState state) {
        this.state = state;
    }

    public Boolean validateNumber(int n){
        return c.getPhysicalComposition().getNumbers().stream().anyMatch(num -> num.getNumber() >= n);
    }

    public Boolean validateCoords(Coords coords){
        return coords.getX_cord() < c.getPhysicalComposition().getSize().getWidth()  &&
               coords.getY_cord() < c.getPhysicalComposition().getSize().getHeight() &&
               coords.getX_cord() >= 0 &&
               coords.getY_cord() >= 0;
    }
}
