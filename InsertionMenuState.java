import java.util.List;

public class InsertionMenuState implements MenuState{
    private MenuContext context;
    public InsertionMenuState(MenuContext context) {
        this.context = context;
    }

    private Boolean handleSavedCoords(){       
        if (context.getSavedCoords().getX_cord() != -1){    
            while (true){                
                System.out.print("Sono state trovate delle coordinate precedentemente salvate " + context.getSavedCoords().toString() + ", si desidera utilizzarle? [Y/n]: ");
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
            System.out.println(context.getSavedCoords().getX_cord());
        else 
            while (true) {
                String response = Utils.sc.nextLine().strip(); 
                if (Utils.isNumber(response))
                    return Integer.parseInt(response);
                TerminalCursor.clearLines(1);
                System.out.print("Inserisci coordinata x: ");
            }
        return context.getSavedCoords().getX_cord();
    }

    private Integer getYCoordinate(Boolean saved_coords_found){
        System.out.print("Inserisci coordinata y: ");
        if (saved_coords_found)
            System.out.println(context.getSavedCoords().getY_cord());
        else 
            while (true) {
                String response = Utils.sc.nextLine().strip(); 
                if (Utils.isNumber(response))
                    return Integer.parseInt(response);
                TerminalCursor.clearLines(1);
                System.out.print("Inserisci coordinata y: ");
            }
        return context.getSavedCoords().getY_cord();
    }

    private Coords getNewCoords(Boolean saved_coords_found){
        Integer new_x, new_y;
        while (true){
            new_x = getXCoordinate(saved_coords_found);
            new_y = getYCoordinate(saved_coords_found);
            Coords newCoords = new Coords(new_x, new_y);
            if (context.validateCoords(newCoords))
                return newCoords;
            System.out.println("Coordinate inserite non valide, riprovare");
            System.out.println("Press Enter to continue...");
            Utils.sc.nextLine();
            TerminalCursor.clearLines(5);
        }

    }

    private Boolean askForSuggestion(){
        String wantSuggestion = getValidInput("Vorresti leggere una suggestion? [Y/n]: ", List.of("y", "n"), "y");
        return wantSuggestion.equals("y");
    }

    public String printMenuOptions(){

        System.out.println("Inserisci i dati richiesti per procedere all'inserimento");
        Boolean saved_coords_found = false;
        String typeOfWord;
        String word;
        
        saved_coords_found = handleSavedCoords();

        if (!saved_coords_found && askForSuggestion()){
            TerminalCursor.clearLines(3);
            return "s";
        }

        context.setSavedCoords(getNewCoords(saved_coords_found));

        // Integer new_x = getXCoordinate(saved_coords_found);
        // context.getSavedCoords().setX_cord(new_x); 

        // Integer new_y = getYCoordinate(saved_coords_found);
        // context.getSavedCoords().setY_cord(new_y); 

        typeOfWord = getValidInput("Vuoi inserire una parola Verticale o Orizzontale? [V/o]: ", List.of("v", "o"), "v");

        System.out.print("Inserisci la parola da scrivere: ");
        word = Utils.sc.nextLine().strip();

        //System.out.println(context.getSavedCoords().toString() + ":" + typeOfWord + ":" + word);

        TerminalCursor.clearLines(6);

        return context.getSavedCoords().toString() + ":" + typeOfWord + ":" + word;
    }
    public void handle(String insertion){
        if (insertion.equals("s")){
            context.setState(new SuggestionMenuState(context));
        }
        else {
            String[] arguments = insertion.split(":");
            int x_cord = Integer.parseInt(arguments[0].split(" ")[0]);
            int y_cord = Integer.parseInt(arguments[0].split(" ")[1]);
            String direction = arguments[1];
            String word = arguments[2];
            Coords coords = new Coords(x_cord, y_cord);
            //context.getCrossword().updateGrid(coords, direction, word);
            context.setState(new MainMenuState(context));
        }
    }
}
