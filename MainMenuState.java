import java.util.List;

public class MainMenuState extends AbstractMenuState{
    public MainMenuState(Coords contextSavedCoords, Crossword crossword) {
        this.contextSavedCoords = contextSavedCoords;
        this.crossword = crossword;
    }

    public String printMenuOptions(){

        numberOfLinesWritten += 4;

        System.out.println("Scegli azione");
        System.out.println("Leggi definizione [s]");
        System.out.println("Inserisci parola  [i]");
        
        String action = getValidInput("action [S/i]: ", List.of("s", "i"), "s");
        
        TerminalCursor.clearLines(numberOfLinesWritten);

        return action;
    }

    public MenuState handle(String action){
        if (action.equals("s")){
            return new SuggestionMenuState(contextSavedCoords, crossword);
        }
        else if (action.equals("i")){
            return new InsertionMenuState(contextSavedCoords, crossword);
        }
        else
            System.err.println("Dang la comparison non funziona");
        return new MainMenuState(contextSavedCoords, crossword);
    }
}