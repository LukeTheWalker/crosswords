public class SuggestionMenuState implements MenuState{
    public String printMenuOptions(){

        System.out.println("Scegli il numero da visualizzare");
        System.out.println("Back [B/b]");
        
        String action = new String();
        action = Utils.sc.nextLine().toLowerCase().strip();;     

        TerminalCursor.clearLines(3);
        return action;
    }
    public void handle(MenuContext context, String action){
        if (Utils.isNumber(action)){
            TerminalCursor.clearLines(2);
            context.getCrossword().getSuggestion(action).printSuggestion();
        }
        else
            System.err.print("Dang la comparison non funziona");
        context.setState(new MainMenuState());
    }
}
