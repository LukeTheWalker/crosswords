import YamlStructure.Coords;
import YamlStructure.Number;

public class Suggestion {
    private String verticalSuggestion;
    private String horizontalSuggestion;
    private Number number;

    // Suggestion (String verticalSuggestion, String horizontalSuggestion){
    //     this.setVerticalSuggestion(verticalSuggestion);
    //     this.setHorizontalSuggestion(horizontalSuggestion);
    // }

    public String getHorizontalSuggestion() {
        return horizontalSuggestion;
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

        System.out.print("Horizontal suggestion: ");
        System.out.print(horizontalSuggestion != null ? horizontalSuggestion : "N/A" );

        TerminalCursor.cursorDown(1);
        TerminalCursor.cursorRight(rightOffset);
        TerminalCursor.eraseUntilEndOfLine();
        
        System.out.print("Vertical suggestion: ");
        System.out.print(verticalSuggestion != null ? verticalSuggestion : "N/A" );

    }
}
