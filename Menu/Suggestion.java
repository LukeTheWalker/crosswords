import java.util.List;
import java.util.stream.Collectors;

import java.util.stream.Stream;

import YamlStructure.Coords;
import YamlStructure.Number;

public class Suggestion {
    private String verticalSuggestion;
    private String horizontalSuggestion;
    private Number number;
    private List<Coords> horizontalWordCoords;
    private List<Coords> verticalWordCoords;

    // Suggestion (String verticalSuggestion, String horizontalSuggestion){
    //     this.setVerticalSuggestion(verticalSuggestion);
    //     this.setHorizontalSuggestion(horizontalSuggestion);
    // }

    public List<Coords> getWordCoords (String direction){
        return direction.equals("v") ? getVerticalWordCoords() : getHorizontalWordCoords();
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
        String horizontalPrompt = horizontalSuggestion != null ? horizontalSuggestion + "[" + horizontalWordCoords.size() + "] ": "N/A" ;
        String verticalPrompt = verticalSuggestion != null ? verticalSuggestion  + "[" + verticalWordCoords.size() + "] ": "N/A" ;

        Integer lengthDifference = Math.abs(horizontalPrompt.length() - verticalPrompt.length());

        String padding = Stream.generate(() -> " ").limit(lengthDifference).collect(Collectors.joining());

        if (horizontalSuggestion != null && verticalSuggestion != null){
            if (horizontalPrompt.length() < verticalPrompt.length()){
                Integer pivot = horizontalPrompt.indexOf("[");
                horizontalPrompt = horizontalPrompt.substring(0, pivot) + padding + horizontalPrompt.substring(pivot, horizontalPrompt.length());
            }
            else {
                Integer pivot = verticalPrompt.indexOf("[");
                verticalPrompt = verticalPrompt.substring(0, pivot) + padding + verticalPrompt.substring(pivot, verticalPrompt.length());
            }        
        }   

        TerminalCursor.cursorRight(rightOffset);
        TerminalCursor.eraseUntilEndOfLine();

        System.out.print("Horizontal suggestion -> ");
        System.out.print(horizontalPrompt);

        TerminalCursor.cursorDown(1);
        TerminalCursor.cursorRight(rightOffset);
        TerminalCursor.eraseUntilEndOfLine();
        
        System.out.print("Vertical suggestion   -> ");
        System.out.print(verticalPrompt);

    }
}
