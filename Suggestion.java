public class Suggestion {
    private String verticalSuggestion;
    private String horizontalSuggestion;

    // Suggestion (String verticalSuggestion, String horizontalSuggestion){
    //     this.setVerticalSuggestion(verticalSuggestion);
    //     this.setHorizontalSuggestion(horizontalSuggestion);
    // }

    public String getHorizontalSuggestion() {
        return horizontalSuggestion;
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

    public void printSuggestion(){
        System.out.print("Vertical suggestion: ");
        System.out.println(verticalSuggestion != null ? verticalSuggestion : "N/A" );
       
        System.out.print("Horizontal suggestion: ");
        System.out.println(horizontalSuggestion != null ? horizontalSuggestion : "N/A" );
    }
}
