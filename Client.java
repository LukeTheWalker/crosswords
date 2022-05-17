import java.io.IOException;

public class Client {
    public static void main(String args[]) throws IOException {
        MenuContext menu = new MenuContext("orizzontali.txt", "verticali.txt", "physical_composition.yaml");
        menu.start();
        menu.getCrossword().setChanged();
        menu.getCrossword().notify_observers();
    }  
}