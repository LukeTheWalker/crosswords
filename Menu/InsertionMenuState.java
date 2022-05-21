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
        System.out.print("Vuoi inserire una parola Orizzontale o Verticale? [O/v/q]: ");
        String direction;
        if (contextSavedSuggestion.getHorizontalSuggestion() == null){
            direction = "v";
            System.out.println("v");
        }
        else if (contextSavedSuggestion.getVerticalSuggestion() == null){
            direction = "o";
            System.out.println("o");
        }
        else 
            direction = getValidInput("", List.of("o", "v", "q"), "o");
        return direction;
    }

    private String getBoundedWord(String direction, List<Coords> wordCoords){
        numberOfLinesWritten++;
        while (true){
            System.out.print("Inserisci la parola da scrivere [" + wordCoords.size() + "]: ");
            String word = Utils.sc.nextLine().strip();
            if ((word.length() == wordCoords.size()) || word.equals("BACK"))
                return word;
            Utils.printInputError("La parola inserita non è della lunghezza corretta");
            TerminalCursor.clearLines(1);
        }
    }

    public String printMenuOptions(){
        numberOfLinesWritten++;
        System.out.println("Inserisci i dati richiesti per procedere all'inserimento");
        
        //Boolean saved_coords_found = handleSavedCoords();

        if (/*!saved_coords_found &&*/ askForSuggestion() || contextSavedSuggestion.getNumber() == null){
            TerminalCursor.clearLines(numberOfLinesWritten);
            return "s";
        }

        String direction = getValidDirection();
        if (direction.equals("q")){
            TerminalCursor.clearLines(numberOfLinesWritten);
            return "q";
        }

        List<Coords> wordCoords = crossword.getWordCoords(contextSavedSuggestion.toCoords(), direction);

        String word = getBoundedWord(direction, wordCoords);

        //System.out.println(contextSavedSuggestion.toString() + ":" + typeOfWord + ":" + word);

        TerminalCursor.clearLines(numberOfLinesWritten);

        if (!word.equals("BACK"))
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
