public class Client {
    public static void main(String args[]){
        MenuContext menu = new MenuContext("Data/orizzontali.txt", "Data/verticali.txt", "Data/physical_composition.yaml");
        menu.start();
    }  
}