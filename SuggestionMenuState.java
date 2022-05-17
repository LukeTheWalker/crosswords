import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SuggestionMenuState implements MenuState{
    public String printMenuOptions(){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Scegli il numero da visualizzare");
        System.out.println("Back [B/b]");
        
        String action = new String();
        try { action = br.readLine(); } catch (IOException e) { e.printStackTrace(); }
        action = action.toLowerCase().strip();

        TerminalCursor.clearLines(3);
        return action;
    }
    public void handle(MenuContext context, String action){
        if (Utils.isNumber(action)){
            TerminalCursor.clearLines(2);
            context.getCrossword().getSuggestion(action).printSuggestion();
        }
        else
            System.out.print("Dang la comparison non funziona");
        context.setState(new MainMenuState());
    }
}
