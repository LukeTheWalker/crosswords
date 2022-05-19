public class SuggestionMenuState implements MenuState{
    private MenuContext context;
    
    public SuggestionMenuState(MenuContext context) {
        this.context = context;
    }

    private String getValidSuggestionNumber(){
        while (true) {
            String response = Utils.sc.nextLine().strip(); 
            if ((Utils.isNumber(response) && Integer.parseInt(response) > 0))
                return response;
            else if (response.equals("b") || response.equals(""))
                return "b";
            TerminalCursor.clearLines(1);
        }
    }
    public String printMenuOptions(){

        System.out.println("Scegli il numero da visualizzare");
        System.out.println("Back [b]");
        
        String action = getValidSuggestionNumber();     

        TerminalCursor.clearLines(3);
        return action;
    }

    public void handle(String action){
        if (Utils.isNumber(action)){
            TerminalCursor.clearLines(2);
            Number number = context.getCrossword()
                .getPhysicalComposition()
                .getNumbers()
                .stream()
                .filter(n -> n.getNumber() == Integer.parseInt(action))
                .findAny()
                .get();
            context.setSavedCoords(new Coords(number.getX_cord(), number.getY_cord()));
            context.getCrossword().getSuggestion(action).printSuggestion();
        }
        else
            System.err.print("Dang la comparison non funziona");
        context.setState(new MainMenuState(context));
    }
}
