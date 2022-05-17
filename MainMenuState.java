import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainMenuState implements MenuState{
    public String printMenuOptions(){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Scegli azione");
        System.out.println("Leggi definizione [S/s]");
        System.out.println("Inserisci parola [I/i]");
        
        String action = new String();
        try { action = br.readLine(); } catch (IOException e) { e.printStackTrace(); }
        action = action.toLowerCase().strip();

        TerminalCursor.clearLines(4);

        return action;
    }

    public void handle(MenuContext context, String action){
        if (action.equals("s")){
            context.setState(new SuggestionMenuState());
            // System.out.println("Scegli il numero da visualizzare");
            // System.out.println("Back [B/b]");

        }
        else if (action.equals("i")){
            System.out.println("Hello world");
        }
        else
            System.out.println("Dang la comparison non funziona");
    }
}