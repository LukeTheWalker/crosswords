public class TerminalCursor {
    private static final String ESC = "\033[";
    public static void clearLines (Integer n){
        cursorUp(n);

        for (int i = 0; i < n; i++){
            System.out.println(ESC + "K");
        }
        cursorUp(n);
    }

    public static void setCursorPosition(int row, int column){
        System.out.print(ESC+row+";"+column+"f");
    }

    public static void cursorUp(Integer n){
        if(n == 0) return;
        System.out.print("\r");
        System.out.print(ESC + n + "A");
    }

    public static void cursorDown(Integer n){
        if(n == 0) return;
        System.out.print("\r");
        System.out.print(ESC + n + "B");
    }

    public static void cursorRight(Integer n){
        if(n == 0) return;
        System.out.print("\r");
        System.out.print(ESC + n + "C");
    }

    public static void saveCursor(){
        System.out.print("\r");
        System.out.print(ESC + "s");
    }
    
    public static void restoreCursor(){
        System.out.print("\r");
        System.out.print(ESC + "u");
    }
    
    public static void clearTerminal(){
        System.out.print(ESC + "H" + ESC + "2J");
    }
    
    public static void eraseUntilEndOfLine(){
        System.out.print(ESC + "0K");
    }
    
    public static Integer goToSuggestion(Integer height){
        final Integer offset = 4;
        TerminalCursor.cursorUp(height * 2);
        TerminalCursor.cursorDown(offset);
        return height * 2 - offset - 1;
    }
}
