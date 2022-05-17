public class Coords {
    private Integer x_cord;
    private Integer y_cord;

    Coords () {};
    Coords(Integer x_cord, Integer y_cord) {
        this.x_cord = x_cord;
        this.y_cord = y_cord;
    }
    
    public Integer getX_cord() {
        return x_cord;
    }
    
    public Integer getY_cord() {
        return y_cord;
    }
    
    public void setY_cord(Integer y_cord) {
        this.y_cord = y_cord;
    }
    
    public void setX_cord(Integer x_cord) {
        this.x_cord = x_cord;
    }

    public String toString(){
        return Integer.toString(x_cord) + " " + Integer.toString(y_cord);
    }

}
