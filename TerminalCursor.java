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
        System.out.print("\r");
        System.out.print(ESC + Integer.toString(n) + "A");
    }
}
