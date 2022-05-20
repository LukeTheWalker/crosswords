import YamlStructure.PhysicalComposition;

public class ObserverData{
    private PhysicalComposition physicalComposition;
    private String[][] grid;
    ObserverData(PhysicalComposition physicalComposition, String[][] grid){
        this.physicalComposition = physicalComposition;
        this.grid = grid;
    }
    public PhysicalComposition getPhysicalComposition() {
        return physicalComposition;
    }
    public String[][] getGrid() {
        return grid;
    }
    public void setGrid(String[][] grid) {
        this.grid = grid;
    }
    public void setPhysicalComposition(PhysicalComposition physicalComposition) {
        this.physicalComposition = physicalComposition;
    }
    
}