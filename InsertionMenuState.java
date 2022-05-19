import java.util.List;

public class InsertionMenuState implements MenuState{
    private MenuContext context;
    public InsertionMenuState(MenuContext context) {
        this.context = context;
    }

    // public String printMenuOptions(){
    //     BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    //     System.out.println("Inserisci i dati richiesti per procedere all'inserimento");
    //     Boolean saved_coords_found = false;
    //     Character answer = '\0';
    //     Character typeOfWord = 'v';
    //     String word = "";
        
    //     if (context.getSavedCoords().getX_cord() != null){
    //         System.out.print("Sono state trovate delle coordinate precedentemente salvate " + context.getSavedCoords().toString() + ", si desidera utilizzarle? [y/n]: ");
    //         try { answer = (char) br.read(); } catch (IOException e) { e.printStackTrace(); }
    //         if (Character.toLowerCase(answer) == 'y')
    //             saved_coords_found = true;
    //     }
    //     else 
    //         System.out.println("Non sono state trovate coordinate salvate");

    //     System.out.print("Inserisci coordinata x: ");
    //     if (saved_coords_found)
    //         System.out.println(context.getSavedCoords().getX_cord());
    //     else 
    //         try { String response = br.readLine(); context.getSavedCoords().setX_cord(Integer.parseInt(response)); } catch (IOException e) { e.printStackTrace(); }

    //     System.out.print("Inserisci coordinata y: ");
    //     if (saved_coords_found)
    //         System.out.println(context.getSavedCoords().getY_cord());
    //     else 
    //         try { String response = br.readLine(); context.getSavedCoords().setY_cord(Integer.parseInt(response)); } catch (IOException e) { e.printStackTrace(); }

    //     System.out.print("Vuoi inserire una parola Verticale o Orizzontale? [v/o]: ");

    //     try { Character response = (char) br.read(); br.skip(1); typeOfWord = Character.toLowerCase(response); } catch (IOException e) { e.printStackTrace(); }

    //     System.out.print("Inserisci la parola la scrivere: ");

    
    //     try { br.skip(1); word = br.readLine(); } catch (IOException e) { e.printStackTrace(); }

    //     System.out.println(saved_coords_found.toString() + ":" + typeOfWord + ":" + word);
    //     //TerminalCursor.clearLines(4);

    //     return context.getSavedCoords().toString() + ":" + typeOfWord + ":" + word;
    // }

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
                if (Utils.isNumber(response) && Integer.parseInt(response) >= 0)
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
                if (Utils.isNumber(response) && Integer.parseInt(response) >= 0)
                    return Integer.parseInt(response);
                TerminalCursor.clearLines(1);
                System.out.print("Inserisci coordinata y: ");
            }
        return context.getSavedCoords().getY_cord();
    }

    public String printMenuOptions(){

        System.out.println("Inserisci i dati richiesti per procedere all'inserimento");
        Boolean saved_coords_found = false;
        String typeOfWord;
        String word;
        
        saved_coords_found = handleSavedCoords();

        Integer new_x = getXCoordinate(saved_coords_found);
        context.getSavedCoords().setX_cord(new_x); 

        Integer new_y = getYCoordinate(saved_coords_found);
        context.getSavedCoords().setY_cord(new_y); 

        typeOfWord = getValidInput("Vuoi inserire una parola Verticale o Orizzontale? [V/o]: ", List.of("v", "o"), "v");

        System.out.print("Inserisci la parola da scrivere: ");
        word = Utils.sc.nextLine().strip();

        //System.out.println(context.getSavedCoords().toString() + ":" + typeOfWord + ":" + word);

        TerminalCursor.clearLines(6);

        return context.getSavedCoords().toString() + ":" + typeOfWord + ":" + word;
    }
    public void handle(String insertion){
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
