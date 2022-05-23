import YamlStructure.Coords;
import java.util.List;

public class ObserverData{
    private Integer pcHeight;
    private List<Coords> coordsList;
    private String[][] grid;
    ObserverData(Integer pcHeight, List<Coords> coordsList, String[][] grid){
        this.pcHeight = pcHeight;
        this.coordsList = coordsList;
        this.grid = grid;
    }
    public Integer getPcHeight() {
        return pcHeight;
    }
    public List<Coords> getCoordsList() {
        return coordsList;
    }
    public String[][] getGrid(){
        return grid;
    }
    public void setCoordsList(List<Coords> coordsList) {
        this.coordsList = coordsList;
    }
    public void setPcHeight(Integer pcHeight) {
        this.pcHeight = pcHeight;
    }
    public void setGrid(String[][] grid){
        this.grid = grid;
    }
    
}