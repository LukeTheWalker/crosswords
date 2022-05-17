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


    public String printMenuOptions(){

        System.out.println("Inserisci i dati richiesti per procedere all'inserimento");
        Boolean saved_coords_found = false;
        String typeOfWord;
        String word, answer;
        
        if (context.getSavedCoords().getX_cord() != null){
            System.out.print("Sono state trovate delle coordinate precedentemente salvate " + context.getSavedCoords().toString() + ", si desidera utilizzarle? [y/n]: ");
            answer = Utils.sc.nextLine().toLowerCase().strip();
            if (answer.toLowerCase().equals("y"))
                saved_coords_found = true;
        }
        else 
            System.out.println("Non sono state trovate coordinate salvate");

        System.out.print("Inserisci coordinata x: ");
        if (saved_coords_found)
            System.out.println(context.getSavedCoords().getX_cord());
        else 
            { String response = Utils.sc.nextLine(); context.getSavedCoords().setX_cord(Integer.parseInt(response)); }

        System.out.print("Inserisci coordinata y: ");
        if (saved_coords_found)
            System.out.println(context.getSavedCoords().getY_cord());
        else 
            { String response = Utils.sc.nextLine(); context.getSavedCoords().setY_cord(Integer.parseInt(response)); }

        System.out.print("Vuoi inserire una parola Verticale o Orizzontale? [v/o]: ");

        String response = Utils.sc.nextLine();
        typeOfWord = response.toLowerCase();

        System.out.print("Inserisci la parola la scrivere: ");

        word = Utils.sc.nextLine();

        //System.out.println(context.getSavedCoords().toString() + ":" + typeOfWord + ":" + word);

        TerminalCursor.clearLines(6);

        return context.getSavedCoords().toString() + ":" + typeOfWord + ":" + word;
    }
    public void handle(String action){
        String[] arguments = action.split(":");
        int x_cord = Integer.parseInt(arguments[0].split(" ")[0]);
        int y_cord = Integer.parseInt(arguments[0].split(" ")[1]);
        String direction = arguments[1];
        String word = arguments[2];
        Coords coords = new Coords(x_cord, y_cord);
        //context.getCrossword().updateGrid(coords, direction, word);
        context.setState(new MainMenuState(context));
    }
}