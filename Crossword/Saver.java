import java.util.List;
import YamlStructure.Coords;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
public class Saver implements Observer{
    File savefile = new File("Data/save.txt");
    RandomAccessFile rac;
    Saver(){
        try {
            rac = new RandomAccessFile(savefile, "rw");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void writeSaves(Integer height, Integer width, Coords coords, String s) {
        try {
            rac.seek(coords.getY_cord()+coords.getX_cord()*height);
            rac.write((s).getBytes());
        } catch (IOException e) {
            System.err.println("Impossibile interagire con il File di salvataggio");
            e.printStackTrace();
            System.exit(1);
        }
    }

    private void saveGridUpdate(Integer height, Integer width, List<Coords> coordsList, String[][] grid){
        coordsList.stream()
                  .forEach((coords) -> 
                   writeSaves(height, width, coords, grid[coords.getX_cord()][coords.getY_cord()]));
                     
    }
    
    @Override
    public void update(Subject s, Object o) {
        ObserverData data = (ObserverData) o;
        saveGridUpdate(data.getPcHeight(), data.getPcWidth(), data.getCoordsList(), data.getGrid());
    }
}
