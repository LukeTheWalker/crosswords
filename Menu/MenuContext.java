import java.io.FileNotFoundException;

public class MenuContext {
    private MenuState state;
    private Crossword c;

    MenuContext(String orizzontali_filename, String verticali_filename, String physical_composition_filename) throws FileNotFoundException{
        c = new Crossword(orizzontali_filename, verticali_filename, physical_composition_filename);
        state = new MainMenuState(new Suggestion(), c);
        Observer f = new Frontend();
        Observer s = new Saver();
        c.attach(f);
        c.attach(s);
    }

    public void start() {
        c.setupGrid();
        while (true){
            String action = state.printMenuOptions();
            setState(state.handle(action));
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
}
