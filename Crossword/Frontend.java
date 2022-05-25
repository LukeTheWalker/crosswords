import java.util.List;
import YamlStructure.Coords;

public class Frontend implements Observer {

    private void showGridUpdate(Integer height, List<Coords> coordsList, String[][] grid){
        coordsList.stream()
                  .forEach((coords) -> 
                   showCell(grid[coords.getX_cord()][coords.getY_cord()], coords.getX_cord(), coords.getY_cord(), height));

        for(int i = 0; i < 14; i++){
            for(int j = 0; j < 14; j++){
                //System.out.println("XX"+grid[i][j]+"XX");
            }
        }
    }

    private void showCell(String s, int x, int y, Integer height){
        TerminalCursor.cursorUp(height * 2 + 1);
        TerminalCursor.cursorDown(y * 2 + 1) ;
        TerminalCursor.cursorRight(1 + x * 4);
        if(s.equals("-")) System.out.print("▐█▌");
        else System.out.print(" " + s);
        TerminalCursor.cursorDown((height - y) * 2);
        System.out.print('\r');
        //System.out.print(s.getClass().getSimpleName());
    }



    @Override
    public void update(Subject s, Object o){
        ObserverData data = (ObserverData) o;
        showGridUpdate(data.getPcHeight(), data.getCoordsList(), data.getGrid());
    }
}
