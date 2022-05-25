import YamlStructure.Coords;
import java.util.List;

public class ObserverData{
    private Integer pcHeight;
    private Integer pcWidth;
    private List<Coords> coordsList;
    private String[][] grid;
    ObserverData(Integer pcHeight, Integer pcWidth, List<Coords> coordsList, String[][] grid){
        this.pcHeight = pcHeight;
        this.pcWidth = pcWidth;
        this.coordsList = coordsList;
        this.grid = grid;
    }
    public Integer getPcHeight() {
        return pcHeight;
    }
    public Integer getPcWidth() {
        return pcWidth;
    }
    public List<Coords> getCoordsList() {
        return coordsList;
    }
    public String[][] getGrid(){
        return grid;
    }
}