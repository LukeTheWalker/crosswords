public class Frontend implements Observer {

    // public void showGrid(String [][] grid, PhysicalComposition physicalComposition){
    //     for (int j = 0; j < physicalComposition.getSize().getHeight(); j++){
    //         for (int i = 0; i < physicalComposition.getSize().getWidth(); i++){
    //             System.out.print(grid[i][j]);
    //         }
    //         System.out.println();
    //     }
    //     System.out.println();
    // }
    
    private void showGrid(String[][] grid, PhysicalComposition physicalComposition){
        for (int j = 0; j < physicalComposition.getSize().getHeight(); j++){
            for (int i = 0; i < physicalComposition.getSize().getWidth(); i++){
                showCell(grid[i][j], i, j, physicalComposition);
            }
        }
    }

    private void showCell(String s, int x, int y, PhysicalComposition physicalComposition){
        int height = physicalComposition.getSize().getHeight();
        TerminalCursor.cursorUp(height * 2 +1);
        TerminalCursor.cursorDown(y * 2 + 1) ;
        TerminalCursor.cursorRight(1 +x*4);
        if(!s.equals("▐█▌"))
        s = " " + s;
        System.out.print(s);
        TerminalCursor.cursorDown((height - y)*2);
        System.out.print('\r');
    }



    @Override
    public void update(Subject s, Object o){
        ObserverData data = (ObserverData) o;
        showGrid(data.getGrid(), data.getPhysicalComposition());
    }
}
