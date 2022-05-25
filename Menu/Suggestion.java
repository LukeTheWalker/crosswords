import java.util.List;

import YamlStructure.Coords;
import YamlStructure.Number;

public class Suggestion {
    private String verticalSuggestion;
    private String horizontalSuggestion;
    private Number number;
    private List<Coords> horizontalWordCoords;
    private List<Coords> verticalWordCoords;
    private String horizontalPlaceholder;
    private String verticalPlaceholder;

    // Suggestion (String verticalSuggestion, String horizontalSuggestion){
    //     this.setVerticalSuggestion(verticalSuggestion);
    //     this.setHorizontalSuggestion(horizontalSuggestion);
    // }

    public List<Coords> getWordCoords (String direction){
        return direction.equals("v") ? getVerticalWordCoords() : getHorizontalWordCoords();
    }

    public String getverticalPlaceholder() {
        return verticalPlaceholder;
    }

    public void setverticalPlaceholder(String verticalPlaceholder) {
        this.verticalPlaceholder = verticalPlaceholder;
    }

    public String getHorizontalPlaceholder() {
        return horizontalPlaceholder;
    }

    public void setHorizontalPlaceholder(String horizontalPlaceholder) {
        this.horizontalPlaceholder = horizontalPlaceholder;
    }

    public String getHorizontalSuggestion() {
        return horizontalSuggestion;
    }

    public List<Coords> getVerticalWordCoords() {
        return verticalWordCoords;
    }

    public void setVerticalWordCoords(List<Coords> verticalWordCoords) {
        this.verticalWordCoords = verticalWordCoords;
    }

    public List<Coords> getHorizontalWordCoords() {
        return horizontalWordCoords;
    }

    public void setHorizontalWordCoords(List<Coords> horizontalWordCoords) {
        this.horizontalWordCoords = horizontalWordCoords;
    }

    public Number getNumber() {
        return number;
    }

    public Coords toCoords() {
        return new Coords(number.getX_cord(), number.getY_cord());
    }

    public void setNumber(Number number) {
        this.number = number;
    }

    public void setHorizontalSuggestion(String horizontalSuggestion) {
        this.horizontalSuggestion = horizontalSuggestion;
    }

    public String getVerticalSuggestion() {
        return verticalSuggestion;
    }

    public void setVerticalSuggestion(String verticalSuggestion) {
        this.verticalSuggestion = verticalSuggestion;
    }

    public void printSuggestion(Integer rightOffset){
        TerminalCursor.cursorRight(rightOffset);
        TerminalCursor.eraseUntilEndOfLine();

        System.out.print("Horizontal suggestion -> ");
        System.out.print(horizontalSuggestion != null ? horizontalSuggestion + "[" + horizontalWordCoords.size() + "]: " + horizontalPlaceholder : "N/A" );

        TerminalCursor.cursorDown(1);
        TerminalCursor.cursorRight(rightOffset);
        TerminalCursor.eraseUntilEndOfLine();
        
        System.out.print("Vertical suggestion ->  ");
        System.out.print(verticalSuggestion != null ? verticalSuggestion  + "[" + verticalWordCoords.size() + "]: " + verticalPlaceholder : "N/A" );

    }
}
