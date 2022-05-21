public class SuggestionMenuState extends AbstractMenuState{
    public SuggestionMenuState(Suggestion contextSavedSuggestion, Crossword crossword) {
        this.contextSavedSuggestion = contextSavedSuggestion;
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
        Integer width = crossword.getPhysicalComposition().getSize().getWidth();
        Integer height = crossword.getPhysicalComposition().getSize().getHeight();

        Integer linesJumped = TerminalCursor.goToSuggestion(height);
            
        contextSavedSuggestion = crossword.getSuggestion(stringNumber);

        contextSavedSuggestion.printSuggestion(width * 5);

        TerminalCursor.cursorDown(linesJumped);
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
        return new InsertionMenuState(contextSavedSuggestion, crossword);
    }
}
