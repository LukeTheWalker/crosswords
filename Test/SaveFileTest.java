import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;

import YamlStructure.Coords;

public class SaveFileTest{


    public void executeTest() throws IOException {
        Subject subj = new Subject();
        Observer saver = new Saver("Test/saveFile.txt");
        RandomAccessFile rac;
        rac  = new RandomAccessFile("Test/saveFile.txt", "rw");
        for(int i = 0; i < 7*7; i++){
            rac.write(" ".getBytes());
        }
        subj.attach(saver);
        String[][] grid = new String[7][7];
        for(int i = 0; i < 7; i++){
            for(int j = 0; j < 7; j++){
                grid[i][j] = "";
            }
        }
        List<Coords> coordlist = List.of(new Coords(0, 0), new Coords(3, 3));
        for(Coords coords : coordlist){
            grid[coords.getX_cord()][coords.getY_cord()] = "x";
        }
        ObserverData obsdata = new ObserverData(
            7, 
            7, 
            coordlist, 
            grid
        );
        subj.setChanged();
        subj.notify(obsdata);
        rac.seek(0);
        String toCompare = rac.readLine();
        String comparatorString = "x                       x                        ";
        assertTrue(toCompare.equals(comparatorString), "Scrittura file di salvataggio");
        rac.close();
    }

    public void assertTrue(boolean result, String test){
        if(result) System.out.print("OK ");
        else System.out.print("FAIL ");
        System.out.println(test);
    }



    public static void main(String[] args) {
        SaveFileTest saveFileTest = new SaveFileTest();
        try {
            saveFileTest.executeTest();
        } catch (Exception e) {
            System.out.println("FAIL Exception");
            e.printStackTrace();
            System.exit(1);
        }
    }
}