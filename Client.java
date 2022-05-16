import java.io.FileNotFoundException;

public class Client {
    public static void main(String args[]) throws FileNotFoundException {
        PhysicalComposition obj = Utils.getPhysicalComposition("physical_composition.yaml");
        System.out.println(obj.getN_blacks());
        //System.out.println(obj.get("size"));
    }
    
}