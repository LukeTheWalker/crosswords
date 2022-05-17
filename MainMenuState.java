public class MainMenuState implements MenuState{
    public String printMenuOptions(){

        System.out.println("Scegli azione");
        System.out.println("Leggi definizione [S/s]");
        System.out.println("Inserisci parola [I/i]");
        
        String action = new String();
        action = Utils.sc.nextLine().toLowerCase().strip(); 

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
            context.setState(new InsertionMenuState(new Coords()));
        }
        else
            System.err.println("Dang la comparison non funziona");
    }
}