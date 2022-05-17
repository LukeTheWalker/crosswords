public class Frontend implements Observer {

    public void showGrid(String [][] grid, PhysicalComposition physicalComposition){
        for (int j = 0; j < physicalComposition.getSize().getHeight(); j++){
            for (int i = 0; i < physicalComposition.getSize().getWidth(); i++){
                System.out.print(grid[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    @Override
    public void update(Subject s, Object o){
        ObserverData data = (ObserverData) o;
        showGrid(data.getGrid(), data.getPhysicalComposition());
    }
}
