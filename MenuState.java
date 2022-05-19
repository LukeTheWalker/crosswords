import java.util.List;

interface MenuState {
    void handle(String action);
    String printMenuOptions();
    default String getValidInput(String prompt, List<String> options, String def){
        System.out.print(prompt);
        while (true){                
            String action = Utils.sc.nextLine().toLowerCase().strip();
            if ( options.stream().anyMatch(option -> option.equals(action)) )
                return action;
            else if (action.equals(""))
                return def;
            TerminalCursor.clearLines(1);
            System.out.print(prompt);
        }
    }
}