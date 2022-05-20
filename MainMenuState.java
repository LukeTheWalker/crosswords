import java.util.List;

public class MainMenuState extends AbstractMenuState{
    public MainMenuState(MenuContext context) {
        this.context = context;
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

    public void handle(String action){
        if (action.equals("s")){
            context.setState(new SuggestionMenuState(context));
            // System.out.println("Scegli il numero da visualizzare");
            // System.out.println("Back [B/b]");

        }
        else if (action.equals("i")){
            context.setState(new InsertionMenuState(context));
        }
        else
            System.err.println("Dang la comparison non funziona");
    }
}