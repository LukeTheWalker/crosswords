public class Number {
    private Coords coords;
    private Integer number;

    Number (Integer number, Integer x, Integer y){
        this.coords = new Coords(x, y);
        this.number = number;
    }

    public Coords getCoords(){
        return this.coords;
    }

    public Integer getNumber(){
        return this.number;
    }
}
