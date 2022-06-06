import java.util.List;

public class MainMenuState extends AbstractMenuState{
    public MainMenuState(Suggestion contextSavedSuggestion, Crossword crossword) {
        this.contextSavedSuggestion = contextSavedSuggestion;
        this.crossword = crossword;
    }

    public String printMenuOptions(){

        numberOfLinesWritten += 5;

        System.out.println("Scegli azione");
        System.out.println("Leggi definizione [s]");
        System.out.println("Inserisci parola  [i]");
        System.out.println("Esci              [q]");
        
        String action = getValidInput("action [S/i/q]: ", List.of("s", "i", "q"), "s");
        
        TerminalCursor.clearLines(numberOfLinesWritten);

        return action;
    }

    public MenuState handle(String action){
        if (action.equals("s")){
            return new SuggestionMenuState(contextSavedSuggestion, crossword);
        }
        else if (action.equals("i")){
            return new InsertionMenuState(contextSavedSuggestion, crossword);
        }
        else
            System.err.println("Errore nella funzione di comparazione");
        return new MainMenuState(contextSavedSuggestion, crossword);
    }
}