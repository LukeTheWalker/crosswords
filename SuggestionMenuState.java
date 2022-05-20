public class SuggestionMenuState extends AbstractMenuState{
    public SuggestionMenuState(MenuContext context) {
        this.context = context;
    }

    private String getValidSuggestionNumber(){
        while (true) {
            String response = Utils.sc.nextLine().strip(); 
            if ((Utils.isNumber(response) && context.validateNumber(Integer.parseInt(response))))
                return response;
            else if (response.equals("b") || response.equals(""))
                return "b";
            TerminalCursor.clearLines(1);
        }
    }

    public String printMenuOptions(){

        numberOfLinesWritten += 3;

        System.out.println("Scegli il numero da visualizzare");
        System.out.println("Back [b]");
        
        String action = getValidSuggestionNumber();     

        TerminalCursor.clearLines(numberOfLinesWritten);
        return action;
    }

    public void handle(String action){
        if (Utils.isNumber(action)){
            TerminalCursor.clearLines(2);
            
            Suggestion suggestion = context.getCrossword().getSuggestion(action);
            Number number = suggestion.getNumber();

            context.setSavedCoords(new Coords(number.getX_cord(), number.getY_cord()));
            suggestion.printSuggestion();
        }
        else
            System.err.print("Dang la comparison non funziona");
        context.setState(new InsertionMenuState(context));
    }
}
