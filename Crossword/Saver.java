import java.util.List;
import YamlStructure.Coords;

import java.io.EOFException;
import java.io.IOException;
import java.io.RandomAccessFile;
public class Saver implements Observer{
    RandomAccessFile rac;
    Saver(){
        try {
        rac = new RandomAccessFile("Data/save.txt", "rws");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeSaves(Coords coords, String s) throws IOException{
        rac.seek(0);
        String string = "";
        String[] parts;
        Integer cx = -1; Integer cy = -1;
        Integer x = coords.getX_cord();
        Integer y = coords.getY_cord();
        try {
            string = rac.readUTF();
            while((cx < x || (cx == x && cy < y))  && string != null){
                parts = string.split(" ");
                cx = Integer.parseInt(parts[0]);
                cy = Integer.parseInt(parts[1]);
                string = rac.readUTF();
            }
        } catch (EOFException e) {

        }
        rac.writeUTF(x + " " + y + " " + s + "\n");    
    }

    private void saveGridUpdate(List<Coords> coordsList, String[][] grid){
        coordsList.stream()
                  .forEach((coords) -> {
                    try {
                        this.writeSaves(coords, grid[coords.getX_cord()][coords.getY_cord()]);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });     
    }
    
    
    @Override
    public void update(Subject s, Object o) {
        ObserverData data = (ObserverData) o;
        saveGridUpdate(data.getCoordsList(), data.getGrid());
    }
}
