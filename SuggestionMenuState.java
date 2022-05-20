public class SuggestionMenuState extends AbstractMenuState{
    public SuggestionMenuState(Coords contextSavedCoords, Crossword crossword) {
        this.contextSavedCoords = contextSavedCoords;
        this.crossword = crossword;
    }

    private String getValidSuggestionNumber(){
        while (true) {
            String response = Utils.sc.nextLine().strip(); 
            if ((Utils.isNumber(response) && crossword.validateNumber(Integer.parseInt(response))))
                return response;
            else if (response.equals("b") || response.equals(""))
                return "b";
            TerminalCursor.clearLines(1);
        }
    }

    private void printSuggestion(String stringNumber){
        TerminalCursor.clearLines(2);
            
        Suggestion suggestion = crossword.getSuggestion(stringNumber);
        Number number = suggestion.getNumber();

        contextSavedCoords = new Coords(number.getX_cord(), number.getY_cord());
        suggestion.printSuggestion();
    }

    public String printMenuOptions(){

        numberOfLinesWritten += 3;

        System.out.println("Scegli il numero da visualizzare");
        System.out.println("Back [b]");
        
        String action = getValidSuggestionNumber();

        TerminalCursor.clearLines(numberOfLinesWritten);
        
        if (Utils.isNumber(action))
            printSuggestion(action);

        return action;
    }

    public MenuState handle(String action){
        return new InsertionMenuState(contextSavedCoords, crossword);
    }
}
