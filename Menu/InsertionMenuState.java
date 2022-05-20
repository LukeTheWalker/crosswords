import java.util.List;

import YamlStructure.Coords;

public class InsertionMenuState extends AbstractMenuState{
    public InsertionMenuState(Coords contextSavedCoords, Crossword crossword) {
        this.contextSavedCoords = contextSavedCoords;
        this.crossword = crossword;
    }

    private Boolean handleSavedCoords(){      
        numberOfLinesWritten++; 
        if (contextSavedCoords.getX_cord() != -1){    
            while (true){                
                System.out.print("Sono state trovate delle coordinate precedentemente salvate " + contextSavedCoords.toString() + ", si desidera utilizzarle? [Y/n]: ");
                String answer = Utils.sc.nextLine().toLowerCase().strip();
                if (answer.equals("y") || answer.equals("")) 
                    return true;
                else if (answer.equals("n"))
                    return false;
                TerminalCursor.clearLines(1);
            }
        }
        else
            System.out.println("Non sono state trovate coordinate salvate");
        return false;
    } 

    private Integer getXCoordinate(Boolean saved_coords_found){
        System.out.print("Inserisci coordinata x: ");
        if (saved_coords_found)
            System.out.println(contextSavedCoords.getX_cord());
        else 
            while (true) {
                String response = Utils.sc.nextLine().strip(); 
                if (Utils.isNumber(response))
                    return Integer.parseInt(response);
                TerminalCursor.clearLines(1);
                System.out.print("Inserisci coordinata x: ");
            }
        return contextSavedCoords.getX_cord();
    }

    private Integer getYCoordinate(Boolean saved_coords_found){
        System.out.print("Inserisci coordinata y: ");
        if (saved_coords_found)
            System.out.println(contextSavedCoords.getY_cord());
        else 
            while (true) {
                String response = Utils.sc.nextLine().strip(); 
                if (Utils.isNumber(response))
                    return Integer.parseInt(response);
                TerminalCursor.clearLines(1);
                System.out.print("Inserisci coordinata y: ");
            }
        return contextSavedCoords.getY_cord();
    }

    private Coords getNewCoords(Boolean saved_coords_found){
        numberOfLinesWritten += 2;
        while (true){
            Integer new_x = getXCoordinate(saved_coords_found);
            Integer new_y = getYCoordinate(saved_coords_found);
            Coords newCoords = new Coords(new_x, new_y);
            if (crossword.validateCoords(newCoords))
                return newCoords;
            Utils.printInputError("Coordinate inserite non valide");
            TerminalCursor.clearLines(2);
        }

    }

    private Boolean askForSuggestion(){
        numberOfLinesWritten++;
        String wantSuggestion = getValidInput("Vorresti leggere una suggestion? [Y/n]: ", List.of("y", "n"), "y");
        return wantSuggestion.equals("y");
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
        
        Boolean saved_coords_found = handleSavedCoords();

        if (!saved_coords_found && askForSuggestion()){
            TerminalCursor.clearLines(3);
            return "s";
        }

        contextSavedCoords = getNewCoords(saved_coords_found);

        numberOfLinesWritten++;
        String direction = getValidInput("Vuoi inserire una parola Orizzontale o Verticale? [O/v/q]: ", List.of("o", "v", "q"), "o");
        if (direction.equals("q")){
            TerminalCursor.clearLines(numberOfLinesWritten);
            return "q";
        }

        List<Coords> wordCoords = crossword.getWordCoords(contextSavedCoords, direction);

        String word = getBoundedWord(direction, wordCoords);

        //System.out.println(contextSavedCoords.toString() + ":" + typeOfWord + ":" + word);

        TerminalCursor.clearLines(numberOfLinesWritten);

        if (!word.equals("BACK"))
            crossword.updateGrid(wordCoords, word);

        return contextSavedCoords.toString() + ":" + direction + ":" + word;
    }
    public MenuState handle(String insertion){
        if (insertion.equals("s")){
            return new SuggestionMenuState(contextSavedCoords, crossword);
        }
        else {
            return new MainMenuState(contextSavedCoords, crossword);
        }
    }
}
