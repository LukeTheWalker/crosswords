import java.util.List;

interface MenuState {
    void handle(String action);
    String printMenuOptions();
    String getValidInput(String prompt, List<String> options, String def);
}