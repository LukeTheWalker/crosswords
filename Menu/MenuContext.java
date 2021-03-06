public class MenuContext {
    private MenuState state;
    private Crossword c;

    MenuContext(String orizzontali_filename, String verticali_filename, String physical_composition_filename) {
        c = new Crossword(orizzontali_filename, verticali_filename, physical_composition_filename);
        state = new MainMenuState(new Suggestion(), c);
        Observer f = new Frontend();
        Observer s = new Saver("Data/save.txt");
        c.attach(f);
        c.attach(s);
    }

    public void start() {
        c.setupGrid();
        while (true){
            String action = state.printMenuOptions();
            if (action.equals("q")) return;
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
