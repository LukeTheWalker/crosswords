import java.util.List;

import YamlStructure.Coords;

public class InsertionMenuState extends AbstractMenuState{
    public InsertionMenuState(Suggestion contextSavedSuggestion, Crossword crossword) {
        this.contextSavedSuggestion = contextSavedSuggestion;
        this.crossword = crossword;
    }

    private Boolean askForSuggestion(){
        numberOfLinesWritten++;
        String wantSuggestion = getValidInput("Vorresti leggere una suggestion? [y/N]: ", List.of("y", "n"), "n");
        return wantSuggestion.equals("y");
    }

    public String getValidDirection(){
        numberOfLinesWritten++;
        String prompt = "Vuoi inserire una parola Orizzontale o Verticale? [O/v/b]: ";
        String direction;
        if (contextSavedSuggestion.getHorizontalSuggestion() == null){
            direction = "v";
            System.out.println(prompt + "v");
        }
        else if (contextSavedSuggestion.getVerticalSuggestion() == null){
            direction = "o";
            System.out.println(prompt + "o");
        }
        else 
            direction = getValidInput(prompt, List.of("o", "v", "b"), "o");
        return direction;
    }

    private void printInputError(String error){
        System.out.println(error + ", riprovare");
        System.out.print("Premi invio per continuare...");
        Utils.sc.nextLine();
        TerminalCursor.clearLines(2);
    }

    private String getBoundedWord(List<Coords> wordCoords){
        numberOfLinesWritten++;
        while (true){
            System.out.print("Inserisci la parola da scrivere [" + wordCoords.size() + "]: ");
            String word = Utils.sc.nextLine().strip();
            if ((word.length() == wordCoords.size()) || word.equals("BACK") || word.equals(""))
                return word;
            printInputError("La parola inserita non è della lunghezza corretta");
            TerminalCursor.clearLines(1);
        }
    }

    public String printMenuOptions(){
        numberOfLinesWritten++;
        System.out.println("Inserisci i dati richiesti per procedere all'inserimento");
        
        if (askForSuggestion() || contextSavedSuggestion.getNumber() == null){
            TerminalCursor.clearLines(numberOfLinesWritten);
            return "s";
        }

        String direction = getValidDirection();
        if (direction.equals("b")){
            TerminalCursor.clearLines(numberOfLinesWritten);
            return "b";
        }

        List<Coords> wordCoords = contextSavedSuggestion.getWordCoords(direction);

        String word = getBoundedWord(wordCoords);

        TerminalCursor.clearLines(numberOfLinesWritten);

        if (!word.equals("BACK") && !word.equals(""))
            crossword.updateGrid(wordCoords, word);

        return contextSavedSuggestion.toString() + ":" + direction + ":" + word;
    }
    public MenuState handle(String insertion){
        if (insertion.equals("s")){
            return new SuggestionMenuState(contextSavedSuggestion, crossword);
        }
        else {
            return new MainMenuState(contextSavedSuggestion, crossword);
        }
    }
}
