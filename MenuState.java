import java.util.List;

interface MenuState {
    MenuState handle(String action);
    String printMenuOptions();
    String getValidInput(String prompt, List<String> options, String def);
}